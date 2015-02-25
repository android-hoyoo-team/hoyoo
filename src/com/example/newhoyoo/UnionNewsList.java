package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.HashMap;

import com.androidquery.AQuery;
import com.example.newhoyoo.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class UnionNewsList extends Activity {
	HashMap<String,Object> map1 = new HashMap<String, Object>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.union_news_list);
		initUnionNewsList();
		AQuery aq = new AQuery(this);
		aq.id(R.id.unionnewslist_back).clicked(this,"back");
	}
	public void initUnionNewsList(){
		ArrayList<HashMap<String,Object>> unionNewsList = new ArrayList<HashMap<String,Object>>();
		ListView newsListView = (ListView)findViewById(R.id.union_news_list);
		for(int i=0;i<10;i++)
		{
			map1.put("picture", R.drawable.picture1);
			map1.put("title", "TOWERS举办了2014年第一次宣讲会,各大团队斥资13亿只为情怀。——专访TOWERS第一次宣讲会。");
			map1.put("name", "Enzo");
			map1.put("time", "9小时前");
			map1.put("readersnum", "998");
			unionNewsList.add(map1);
		}
		final SimpleAdapter mySimpleAdapter=new SimpleAdapter(this,   
				unionNewsList,
				//myArrayList,//数据源   
				R.layout.item_hoyoo,//ListView内部数据展示形式的布局文件listitem.xml   
				new String[]{"picture","title","name","time","readersnum" },//HashMap中的两个key值 itemTitle和itemContent   ,"itemContent"
				new int[]{R.id.imageview_item,R.id.textview_item,R.id.name,R.id.time,R.id.readersnum });
		/*布局文件listitem.xml中组件的id    ,R.id.itemContent布局文件的各组件分别映射到HashMap的各元素上，完成适配*/   

		newsListView.setAdapter(mySimpleAdapter); 

		newsListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = getIntent();
				intent.setClass(UnionNewsList.this,UnionNewsActivity.class);
				UnionNewsList.this.startActivity(intent);
			}});
	}
	public void back(){
		this.finish();		
	}
}
