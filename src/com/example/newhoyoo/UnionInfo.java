package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.HashMap;

import com.androidquery.AQuery;
import com.exp.demo.AchieveFragment;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.os.Build;

public class UnionInfo extends Activity {
	 HashMap<String, Object> map1=new HashMap<String,Object>(); 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.union_info);
		initBenifitList();
		AQuery aq = new AQuery(this);
		aq.id(R.id.unioninfo_back).clicked(this, "back");
	}
	
	public void initBenifitList(){
		
		ArrayList<HashMap<String, Object>> benifitList = new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<10;i++)
		{
			map1.put("name", "大置顶术");
			map1.put("discription", "公会成员的邀请在发布之后会在粉丝的邀请界面置顶1个小时。");
			benifitList.add(map1);
		}
		final SimpleAdapter mySimpleAdapter=new SimpleAdapter(this,   
				benifitList,
        		//myArrayList,//数据源   
                R.layout.unionbenifit_item,//ListView内部数据展示形式的布局文件listitem.xml   
                new String[]{"name","discription" },//HashMap中的两个key值 itemTitle和itemContent   ,"itemContent"
                new int[]{R.id.benifit_name,R.id.benifit_discription });
        /*布局文件listitem.xml中组件的id    ,R.id.itemContent布局文件的各组件分别映射到HashMap的各元素上，完成适配*/   
           
		ListView benifitListView = (ListView)findViewById(R.id.benifit_list);
		benifitListView.setAdapter(mySimpleAdapter);   
	}
	public void back(){
		this.finish();
	}
}
