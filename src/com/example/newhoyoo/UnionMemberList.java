package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.example.newhoyoo.adapter.CustomListViewAdapter;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.entity.RAttention;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.CustomActionbar;

public class UnionMemberList extends Activity{
	AQuery aq;
	EUnion union;
	EPerson person;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_union_member_list);
		CustomActionbar actionbar = (CustomActionbar)findViewById(R.id.actionbar);
		actionbar.setImageResource(R.drawable.bt_15_01_selector);
		actionbar.setTitle("公会成员");
		actionbar.setButtonVisibility(View.GONE);
		this.aq = new AQuery(this);
		this.aq.id(R.id.actionbar_left).clicked(this, "back");
		init();
	}

	public void init(){
		person = Application.getLoginInfo().getPerson();
		union = Application.getLoginInfo().getUnion();
		List<EPerson> members = Application.getPersonService().getEPersonsByUnionId(union.getId());
		List<HashMap<String,Object>> membersList = new ArrayList<HashMap<String,Object>>();
		for(int i = 0 ;i < members.size();i++){
			HashMap<String,Object> map = new HashMap<String,Object>();
			EPerson person = members.get(i);
			map.put("icon",person.getIcon());
			map.put("name", person.getName());
			map.put("id", person.getId());
			map.put("position", person.getId() == union.getChairmanId() ? "会长" : "会员");
			map.put("title",Application.getLevelService().getELevelByID(person.getLevelId()).getName());
			map.put("fans", Application.getPersonService().getFansCount(person.getId()));
			membersList.add(map);
		}
		CustomListViewAdapter adapter = new CustomListViewAdapter(this,
				membersList,
				R.layout.union_member_item, 
				new String[]{"icon","name","id","position","title","fans"},
				new int[]{R.id.union_member_imageview,R.id.name_textview,R.id.id_textview,R.id.position_textview,R.id.title_textview,R.id.fans_num_textview},
				new String[]{"image","text","text","text","text","text"}){
			@Override
			public View getView(int position, View convertView,
					ViewGroup parent) {
				// TODO Auto-generated method stub
				final View view = super.getView(position, convertView, parent);
				final int targetId =Integer.parseInt(((TextView)view.findViewById(R.id.id_textview)).getText().toString());
				final String targetName = ((TextView)view.findViewById(R.id.name_textview)).getText().toString();
				final List<RAttention> attentions = Application.getPersonService().getAttentionFrom(person.getId());
				view.findViewById(R.id.optionmenu_imageview).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						final ImageView v0 = (ImageView)v;
						v0.setImageResource(R.drawable.bt_32_press);
						PopupMenu pop = new PopupMenu(UnionMemberList.this, v0);
						pop.getMenuInflater().inflate(R.menu.union_member_operation, pop.getMenu());
						if(attentions!=null &&attentions.size()>0){
							for (RAttention attention : attentions) {
								if(attention.getPersonIdTo() == targetId) {
									pop.getMenu().getItem(0).setVisible(false);
								}
							}
						}
						if(person.getId() != union.getChairmanId()){
							pop.getMenu().getItem(2).setVisible(false);
							pop.getMenu().getItem(3).setVisible(false);
						}else{
							pop.getMenu().getItem(2).setVisible(true);
							pop.getMenu().getItem(3).setVisible(true);
						}
						pop.setOnDismissListener(new PopupMenu.OnDismissListener() {
							
							@Override
							public void onDismiss(PopupMenu arg0) {
								// TODO Auto-generated method stub
								v0.setImageResource(R.drawable.bt_32_nor);
							}
						});
						pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
							
							@Override
							public boolean onMenuItemClick(MenuItem arg0) {
								// TODO Auto-generated method stub
								switch (arg0.getItemId()) {
								case R.id.item1:
									RAttention attention = new RAttention();
									attention.setPersonIdFrom(person.getId());
									attention.setPersonIdTo(targetId);
									attention.setTime(new Date().getTime());
									Application.getPersonService().saveAttention(attention);
									break;
								case R.id.item2:
									Toast.makeText(getApplicationContext(), "前往发消息界面，尚未完成，敬请期待", Toast.LENGTH_LONG).show();
									break;
								case R.id.item3:
									Toast.makeText(getApplicationContext(), "前往改等级界面，尚未完成，敬请期待", Toast.LENGTH_LONG).show();
									break;
								case R.id.item4:
									new AlertDialog.Builder(UnionMemberList.this).setTitle("提示:")
									.setMessage("您确定将 "+targetName+" 踢出公会？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											Application.getUnionService().removePersonFromUnion(targetId);
											init();
											dialog.cancel();
										}
									}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											dialog.cancel();
										}
									}).show();
									
									break;
								default:
									break;
								}
							//	v0.setImageResource(R.drawable.bt_32_nor);
								return false;
							}
						});
						pop.show();
					
					}
				});
				return view;
			}
		};
		ListView memberListView = (ListView)findViewById(R.id.union_member_listview);
		memberListView.setAdapter(adapter);
	}
	public void back(){
		this.finish();
	}
}
