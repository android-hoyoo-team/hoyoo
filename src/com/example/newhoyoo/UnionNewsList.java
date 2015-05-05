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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 公会新闻列表
 * @author CL
 *
 */
public class UnionNewsList extends Activity {
	//最新新闻Adapter
	NewsListAdapter mAdapter;
	//新闻列表View
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
		//添加点击事件
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
		
	}
	/**
	 *返回按钮事件，结束当前activity
	 */
	class BackListener implements android.view.View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}	
	}
}
