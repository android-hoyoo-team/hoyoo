package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.androidquery.AQuery;
import com.example.newhoyoo.adapter.NewsListAdapter;
import com.example.newhoyoo.util.SystemUiHider;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.CustomActionbar;

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
	NewsListAdapter mAdapter;
	ListView mListView;
	AQuery aq=new AQuery(this);
	CustomActionbar actionbar;
	HashMap<String,Object> map1 = new HashMap<String, Object>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.union_news_list);
		//设置actionbar
		actionbar=(CustomActionbar)findViewById(R.id.newslist_actionbar);
		actionbar.setButtonVisibility(View.GONE);
		aq.id(R.id.actionbar_left).clicked(new BackListener());
		mListView=(ListView)findViewById(R.id.union_news_listview);
		mAdapter=new NewsListAdapter(this);
		List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
		mapList=Application.getArticleSercice().convertFromList(Application.getArticleSercice().getUnionNews(Application.getLoginInfo().getUnion().getId()));
		mAdapter.setNewsList(mapList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Map<String,Object> map=new HashMap<String, Object>();
				map=(Map<String, Object>)mListView.getItemAtPosition(position);
				Intent intent=new Intent();
				intent.putExtra("type", "公会新闻");
				intent.putExtra("id", map.get("id").toString());
				intent.setClass(UnionNewsList.this, UnionNewsActivity.class);
				UnionNewsList.this.startActivity(intent);
			}
			
		});
		
//		initUnionNewsList();
//		CustomActionbar actionbar = (CustomActionbar)findViewById(R.id.newslist_actionbar);
//		actionbar.setTitle("公会新闻");
//		actionbar.setButtonVisibility(View.GONE);
//		AQuery aq = new AQuery(this);
//		aq.id(R.id.actionbar_left).clicked(this,"back");
	}
	class BackListener implements android.view.View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}	
	}
//	public void initUnionNewsList(){
//		ArrayList<HashMap<String,Object>> unionNewsList = new ArrayList<HashMap<String,Object>>();
//		ListView newsListView = (ListView)findViewById(R.id.union_news_listview);
//		for(int i=0;i<10;i++)
//		{
//			map1.put("picture", R.drawable.picture1);
//			map1.put("title", "TOWERS举办了2014年第一次宣讲会,各大团队斥资13亿只为情怀。——专访TOWERS第一次宣讲会。");
//			map1.put("name", "Enzo");
//			map1.put("time", "9小时前");
//			map1.put("readersnum", "998");
//			unionNewsList.add(map1);
//		}
//		final SimpleAdapter mySimpleAdapter=new SimpleAdapter(this,   
//				unionNewsList,
//				//myArrayList,//数据源   
//				R.layout.item_hoyoo,//ListView内部数据展示形式的布局文件listitem.xml   
//				new String[]{"picture","title","name","time","readersnum" },//HashMap中的两个key值 itemTitle和itemContent   ,"itemContent"
//				new int[]{R.id.imageview_item,R.id.textview_item,R.id.name,R.id.time,R.id.readersnum });
//		/*布局文件listitem.xml中组件的id    ,R.id.itemContent布局文件的各组件分别映射到HashMap的各元素上，完成适配*/   
//
//		newsListView.setAdapter(mySimpleAdapter); 
//
//		newsListView.setOnItemClickListener(new OnItemClickListener(){
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				Intent intent = getIntent();
//				intent.setClass(UnionNewsList.this,UnionNewsActivity.class);
//				UnionNewsList.this.startActivity(intent);
//			}});
//	}
//	public void back(){
//		this.finish();		
//	}
}
