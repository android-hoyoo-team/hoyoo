package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.newhoyoo.adapter.CustomListViewAdapter;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.CustomActionbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 好友列表界面.
 * @author XF
 *
 */
public class FriendsList extends Activity {

	EPerson person;
	//存放CustomListViewAdapter中的数据
	List<Map<String,Object>> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_list);
		//actionbar
		CustomActionbar actionbar = (CustomActionbar)findViewById(R.id.friends_list_actionbar);
		actionbar.setImageResource(R.drawable.bt_14_selector);
		actionbar.setTitleVisibility(View.VISIBLE);
		actionbar.setTitle("好友列表");
		actionbar.setButtonVisibility(View.GONE);
		//返回按钮
		actionbar.findViewById(R.id.actionbar_left).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FriendsList.this.finish();
			}
		});
		init();
	}

	public void init(){
		person = Application.getLoginInfo().getPerson();
		//通过id获取其好友列表，满足好友的条件是，既被他关注，也关注了他的。
		List<EPerson> friends = Application.getPersonService().getFriends(person.getId());
		ListView friends_listview  = (ListView)findViewById(R.id.friends_listview);
		TextView no_friends_textview = (TextView)findViewById(R.id.no_friends_textview);
		data = new ArrayList<Map<String,Object>>();
		if(friends!=null&&friends.size()>0){
			no_friends_textview.setVisibility(View.GONE);
			friends_listview.setVisibility(View.VISIBLE);
			for (EPerson person : friends) {
				HashMap<String,Object> map = new HashMap<String, Object>();
				//获取用户所在工会
				EUnion union = Application.getUnionService().getUnionByPersonId(person.getId());
				map.put("id",person.getId());
				map.put("icon", person.getIcon());
				map.put("name", person.getName());
				if(union!=null&&"normal".equals(union.getStatus()))map.put("uName",union.getName());
				else map.put("uName", "暂无公会");
				//根据id获取等级信息
				map.put("title", Application.getLevelService().getELevelByID(person.getLevelId()).getName());
				//获取粉丝
				map.put("fans",Application.getPersonService().getFansCount(person.getId()));
				data.add(map);
			}
			CustomListViewAdapter adapter = new CustomListViewAdapter(this, data, R.layout.union_member_item, 
					new String[]{"icon","name","id","uName","title","fans"}, 
					new int[]{R.id.union_member_imageview,R.id.name_textview,R.id.id_textview,R.id.position_textview,R.id.title_textview,R.id.fans_num_textview},
					new String[]{"image","text","text","text","text","text"}){

				@Override
				public View getView(int position, View convertView,
						ViewGroup parent) {
					// TODO Auto-generated method stub
					final View view = super.getView(position, convertView, parent);
					final int targetId =Integer.parseInt(((TextView)view.findViewById(R.id.id_textview)).getText().toString());
					final String targetName = ((TextView)view.findViewById(R.id.name_textview)).getText().toString();
					view.findViewById(R.id.optionmenu_imageview).setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final ImageView v0 = (ImageView)v;
							v0.setImageResource(R.drawable.bt_32_press);
							PopupMenu pop = new PopupMenu(FriendsList.this, v0);
							pop.getMenuInflater().inflate(R.menu.friend_operation_menu, pop.getMenu());
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
										Toast.makeText(getApplicationContext(), "前往发消息界面，尚未完成，敬请期待", Toast.LENGTH_LONG).show();
										break;
									case R.id.item2:
										new AlertDialog.Builder(FriendsList.this).setTitle("提示:")
										.setMessage("您确定将 "+targetName+" 踢出移出好友列表？").setPositiveButton("确定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
												Application.getPersonService().removeFriend(targetId);
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
									return false;
								}
							});
							pop.show();

						}
					});
					return view;
				}
			};
			friends_listview.setAdapter(adapter);
		}else{
			friends_listview.setVisibility(View.GONE);
			no_friends_textview.setVisibility(View.VISIBLE);
		}
	}
}
