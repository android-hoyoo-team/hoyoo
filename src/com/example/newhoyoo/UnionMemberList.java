package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.androidquery.AQuery;
import com.example.newhoyoo.adapter.CustomListViewAdapter;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.CustomActionbar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class UnionMemberList extends Activity{
	AQuery aq;
	EUnion union;

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
		this.aq.id(R.id.optionmenu_imageview).clicked(this, "openOptionMenu");
		init();
	}

	public void init(){
		union = Application.getLoginInfo().getUnion();
		List<EPerson> members = Application.getPersonService().getEPersonsByUnionId(union.getId());
		List<HashMap<String,Object>> membersList = new ArrayList<HashMap<String,Object>>();
		for(int i = 0 ;i < members.size();i++){
			HashMap<String,Object> map = new HashMap<String,Object>();
			EPerson person = members.get(i);
			map.put("icon",person.getIcon());
			map.put("name", person.getName());
			map.put("position", person.getId() == union.getChairmanId() ? "会长" : "会员");
			map.put("title",Application.getLevelService().getELevelByID(person.getLevelId()).getName());
			map.put("fans", Application.getPersonService().getFansCount(person.getId()));
			membersList.add(map);
		}
		CustomListViewAdapter adapter = new CustomListViewAdapter(this,
				membersList,
				R.layout.union_member_item, 
				new String[]{"icon","name","position","title","fans"},
				new int[]{R.id.union_member_imageview,R.id.name_textview,R.id.position_textview,R.id.title_textview,R.id.fans_num_textview},
				new String[]{"image","text","text","text","text"}){
			@Override
			public View getView(int position, View convertView,
					ViewGroup parent) {
				// TODO Auto-generated method stub
				View v = super.getView(position, convertView, parent);
				Toast.makeText(getApplicationContext(), v.getId()+"", 1).show();
				
				return v;
			}
		};
		ListView memberListView = (ListView)findViewById(R.id.union_member_listview);
		memberListView.setAdapter(adapter);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//return super.onCreateOptionsMenu(menu);
		return false;
	}

	@Override
	public void openOptionsMenu() {
		// TODO Auto-generated method stub
		super.openOptionsMenu();
	}

	public void back(){
		this.finish();
	}
}
