package com.exp.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;
import com.example.newhoyoo.UnionNewsActivity;
import com.example.newhoyoo.UnionNewsDetail;
import com.example.newhoyoo.YaoqingActivity;
import com.example.newhoyoo.adapter.NewsListAdapter;
import com.huyoo.entity.EArticle;
import com.huyoo.global.Application;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HooyoFragment extends Fragment {
	AQuery aq=new AQuery(getActivity());
	NewsListAdapter mAdapter1;
	NewsListAdapter mAdapter3;
//	/************************************************************/
//	/** Called when the activity is first created. */   
//	ListView myListView1; 
//	HashMap<String, Object> pMap_1=new HashMap<String,Object>(); 
//	HashMap<String, Object> pMap1_1=new HashMap<String,Object>(); 
//	HashMap<String, Object> pMap2_1=new HashMap<String,Object>(); 
//	HashMap<String, Object> pMap3_1=new HashMap<String,Object>();
//	/************************************************************/
//	/************************************************************/
	/** Called when the activity is first created. */   
	ListView myListView2; 
	HashMap<String, Object> pMap_2=new HashMap<String,Object>(); 
	HashMap<String, Object> pMap1_2=new HashMap<String,Object>(); 
	HashMap<String, Object> pMap2_2=new HashMap<String,Object>(); 
	HashMap<String, Object> pMap3_2=new HashMap<String,Object>();
	/************************************************************/
	/************************************************************/
	/** Called when the activity is first created. */   
	//澹版槑ListView瀵硅薄   
	ListView myListView3; 
	HashMap<String, Object> pMap_3=new HashMap<String,Object>(); 
	HashMap<String, Object> pMap1_3=new HashMap<String,Object>(); 
	HashMap<String, Object> pMap2_3=new HashMap<String,Object>(); 
	HashMap<String, Object> pMap3_3=new HashMap<String,Object>();
	/************************************************************/
	ListView myListView1;
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_hooyo, container, false);
		return rootView;
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		//ActionBar action=getActivity().getActionBar();
		//action.hide();    	  
		/******************************11111111111111111111111******************************/
		//生成ListView对象   
		myListView1=(ListView)getActivity().findViewById(R.id.listView_news);
		List<Map<String,Object>> programeList=new ArrayList<Map<String,Object>>();  
		List<EArticle> list1=Application.getArticleSercice().getTopUnionNews(4, 0);
		programeList=Application.getArticleSercice().convertFromList(list1);
		mAdapter1=new NewsListAdapter(this.getActivity());
		mAdapter1.setNewsList(programeList);
		myListView1.setAdapter(mAdapter1);
		
		
//		for(int i=0;i<5;i++) 
//		{ 
//			switch(i){
//			case 0:
//
//				pMap_1.put("picture",R.drawable.picture1); 
//				pMap_1.put("title", "男版阿黛尔萨姆-史密斯获四奖。\n——格莱美颁奖");
//				pMap_1.put("name", "Candy"); 
//				pMap_1.put("time", "9小时前");
//				pMap_1.put("readers", "998");
//				programeList.add(pMap_1);
//				break;
//			case 1:
//
//				pMap1_1.put("picture",R.drawable.picture2); 
//				pMap1_1.put("title", "2月10日下午，羊年春晚进行第三次彩排，佟丽娅、秦岚、王丽坤、马苏的《四大美人》造型曝光，华服精美，身段妖娆，非常养眼。“春晚四美”惊艳古装造型大pk，谁最迷人？");
//				pMap1_1.put("name", "Yonono");  
//				pMap1_1.put("time", "12小时前");
//				pMap1_1.put("readers", "1.2万");
//				programeList.add(pMap1_1);
//				break;
//			case 2:
//
//				pMap2_1.put("picture",R.drawable.picture3); 
//				pMap2_1.put("title","有史来最大改变 Android 5.0十大新特性");
//				pMap2_1.put("name", "Mike");  
//				pMap2_1.put("time", "1天前");
//				pMap2_1.put("readers", "700");
//				programeList.add(pMap2_1);
//				break;
//			case 3:
//
//				pMap3_1.put("picture",R.drawable.picture4); 
//				pMap3_1.put("title", "我的信息我我的信息我的信息我的信息我的信息我的信息我的信息我的信息我的信息我的信息我的信息我的信息我的信息我的信息的信息");
//				pMap3_1.put("name", "Baine");  
//				pMap3_1.put("time", "2天前");
//				pMap3_1.put("readers", "998");
//				programeList.add(pMap3_1);
//				break;
//			}
//		} 

		//生成SimpleAdapter适配器对象   
//		final SimpleAdapter mySimpleAdapter=new SimpleAdapter(this.getActivity(),   
//				programeList,
//				//myArrayList,//数据源   
//				R.layout.item_hoyoo,//ListView内部数据展示形式的布局文件listitem.xml   
//				new String[]{"picture","title","name","time","readers" },//HashMap中的两个key值 itemTitle和itemContent   ,"itemContent"
//				new int[]{R.id.imageview_item,R.id.textview_item,R.id.name,R.id.time,R.id.readersnum });
//		/*布局文件listitem.xml中组件的id    ,R.id.itemContent布局文件的各组件分别映射到HashMap的各元素上，完成适配*/   
//
//		myListView1.setAdapter(mySimpleAdapter);  
		
		
		//添加点击事件   
		myListView1.setOnItemClickListener(new OnItemClickListener(){   
			@Override   
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,   
					long arg3) {  
				Map<String, Object> currentMap=(Map<String, Object>)myListView1.getItemAtPosition(arg2);
				Intent intent=new Intent();
				intent.putExtra("type", "公会新闻");
				intent.putExtra("id", currentMap.get("id").toString());
				intent.setClass(getActivity(), UnionNewsActivity.class);
				getActivity().startActivity(intent);
				//获得选中项的HashMap对象   
//				HashMap<String,String> map=(HashMap<String,String>)myListView1.getItemAtPosition(arg2);   
//				String title=map.get("itemTitle");   
//				String content=map.get("itemContent");  
//				Intent intent;
//				switch(arg2){
//				case 0:
//					intent = hooyoFragment.this.getActivity().getIntent();
//					intent.setClass(getActivity(),UnionNewsDetail.class);
//					getActivity().startActivity(intent);
//					break;
//				case 1:
//					intent = hooyoFragment.this.getActivity().getIntent();
//					intent.setClass(getActivity(),UnionNewsDetail.class);
//					getActivity().startActivity(intent);
//					break;
//				case 2:
//					intent = hooyoFragment.this.getActivity().getIntent();
//					intent.setClass(getActivity(),UnionNewsDetail.class);
//					getActivity().startActivity(intent);
//					break;
//				case 3:
//					intent = hooyoFragment.this.getActivity().getIntent();
//					intent.setClass(getActivity(),UnionNewsDetail.class);
//					getActivity().startActivity(intent);
//					break;
//				}
//				switch(arg2){
//	        	case 0:
//	        		
//	        	case 1:
//	        		
//	        	case 2:
//	        		
//	        	case 3:
//	        		Intent intent=new Intent();
//	        		intent.putExtra("title", "公会新闻");
//	        		intent.setClass(getActivity(), UnionNewsActivity.class);
//	        		getActivity().startActivity(intent);
//	        		break;
//	        	}

			}   

		});   
		/************************************************************/
		/******************************222222222222222222222222222******************************/
		//生成ListView对象   
		myListView2=(ListView)getActivity().findViewById(R.id.listView_invite);
		ArrayList<HashMap<String,Object>> programeList2=new ArrayList<HashMap<String,Object>>();  
		for(int i=0;i<5;i++) 
		{ 
			switch(i){
			case 0:

				pMap_2.put("picture",R.drawable.d05); 
				pMap_2.put("name", "全是邀请");  
				pMap_2.put("time", "发布于今天");
				pMap_2.put("readers", "没人看");
				programeList2.add(pMap_2);
				break;
			case 1:

				pMap1_2.put("picture",R.drawable.d06); 
				pMap1_2.put("name", "我的公会");  
				pMap1_2.put("time", "发布于今天");
				pMap1_2.put("readers", "没人看");
				programeList2.add(pMap1_2);
				break;
			case 2:

				pMap2_2.put("picture",R.drawable.d07); 
				pMap2_2.put("name", "我的成就");  
				pMap2_2.put("time", "发布于今天");
				pMap2_2.put("readers", "没人看");
				programeList2.add(pMap2_2);
				break;
			case 3:

				pMap3_2.put("picture",R.drawable.d08); 
				pMap3_2.put("name", "我的信息");  
				pMap3_2.put("time", "发布于今天");
				pMap3_2.put("readers", "没人看");
				programeList2.add(pMap3_2);
				break;
			}
		} 

		//生成SimpleAdapter适配器对象   
		final SimpleAdapter mySimpleAdapter2=new SimpleAdapter(this.getActivity(),   
				programeList2,
				//myArrayList,//数据源   
				R.layout.item_hoyoo,//ListView内部数据展示形式的布局文件listitem.xml   
				new String[]{"picture","title","name","time","readers" },//HashMap中的两个key值 itemTitle和itemContent   ,"itemContent"
				new int[]{R.id.imageview_item,R.id.textview_item,R.id.name,R.id.time,R.id.readersnum });
		/*布局文件listitem.xml中组件的id    ,R.id.itemContent布局文件的各组件分别映射到HashMap的各元素上，完成适配*/   

		myListView2.setAdapter(mySimpleAdapter2);   
		//添加点击事件   
		myListView2.setOnItemClickListener(new OnItemClickListener(){   
			@Override   
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,   
					long arg3) {   
				//获得选中项的HashMap对象   
				HashMap<String,String> map=(HashMap<String,String>)myListView2.getItemAtPosition(arg2);   
				String title=map.get("itemTitle");   
				String content=map.get("itemContent");  

//				switch(arg2){
//				case 0:
//					Toast.makeText(getActivity().getApplicationContext(),    
//							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
//							Toast.LENGTH_SHORT).show();	        		
//					break;
//				case 1:
//					Toast.makeText(getActivity().getApplicationContext(),    
//							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
//							Toast.LENGTH_SHORT).show();
//					break;
//				case 2:
//					Toast.makeText(getActivity().getApplicationContext(),    
//							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
//							Toast.LENGTH_SHORT).show(); 
//					break;
//				case 3:
//					Toast.makeText(getActivity().getApplicationContext(),    
//							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
//							Toast.LENGTH_SHORT).show(); 
//					break;
//				}
				switch(arg2){
				case 0:
					
				case 1:
					
				case 2:
					
				case 3:
					Intent intent=new Intent();
	        		intent.setClass(getActivity(),YaoqingActivity.class);
	        		getActivity().startActivity(intent);
	        		break;
				}

			}   

		});   
		/************************************************************/
		/******************************3333333333333333333333333******************************/
		//生成ListView对象   
		myListView3=(ListView)getActivity().findViewById(R.id.listView_success);
		List<Map<String,Object>> programeList3=new ArrayList<Map<String,Object>>();  
		List<EArticle> list3=Application.getArticleSercice().getTopTips(4, 0);
		programeList3=Application.getArticleSercice().convertFromList(list3);
		mAdapter3=new NewsListAdapter(this.getActivity());
		mAdapter3.setNewsList(programeList3);
		myListView3.setAdapter(mAdapter3);
//		myListView3=(ListView)getActivity().findViewById(R.id.listView_success);
//		ArrayList<HashMap<String,Object>> programeList3=new ArrayList<HashMap<String,Object>>();  
//		for(int i=0;i<5;i++) 
//		{ 
//			switch(i){
//			case 0:
//
//				pMap_3.put("picture",R.drawable.d05); 
//				pMap_3.put("name", "全是邀请");  
//				pMap_3.put("time", "发布于今天");
//				pMap_3.put("readers", "没人看");
//				programeList3.add(pMap_3);
//				break;
//			case 1:
//
//				pMap1_3.put("picture",R.drawable.d06); 
//				pMap1_3.put("name", "我的公会");  
//				pMap1_3.put("time", "发布于今天");
//				pMap1_3.put("readers", "没人看");
//				programeList3.add(pMap1_3);
//				break;
//			case 2:
//
//				pMap2_3.put("picture",R.drawable.d07); 
//				pMap2_3.put("name", "我的成就");  
//				pMap2_3.put("time", "发布于今天");
//				pMap2_3.put("readers", "没人看");
//				programeList3.add(pMap2_3);
//				break;
//			case 3:
//
//				pMap3_3.put("picture",R.drawable.d08); 
//				pMap3_3.put("name", "我的信息");  
//				pMap3_3.put("time", "发布于今天");
//				pMap3_3.put("readers", "没人看");
//				programeList3.add(pMap3_3);
//				break;
//			}
//		} 

//		//生成SimpleAdapter适配器对象   
//		final SimpleAdapter mySimpleAdapter3=new SimpleAdapter(this.getActivity(),   
//				programeList3,
//				//myArrayList,//数据源   
//				R.layout.item_hoyoo,//ListView内部数据展示形式的布局文件listitem.xml   
//				new String[]{"picture","title","name","time","readers" },//HashMap中的两个key值 itemTitle和itemContent   ,"itemContent"
//				new int[]{R.id.imageview_item,R.id.textview_item,R.id.name,R.id.time,R.id.readersnum });
//		/*布局文件listitem.xml中组件的id    ,R.id.itemContent布局文件的各组件分别映射到HashMap的各元素上，完成适配*/   
//
//		myListView3.setAdapter(mySimpleAdapter3);   
		//添加点击事件   
		myListView3.setOnItemClickListener(new OnItemClickListener(){   
			@Override   
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,   
					long arg3) {   
				Map<String,Object> currentMap=(Map<String, Object>)myListView3.getItemAtPosition(arg2);
				Intent intent=new Intent();
				intent.putExtra("title", "成功秘笈");
				intent.putExtra("id", currentMap.get("id").toString());
				intent.setClass(getActivity(), UnionNewsActivity.class);
				getActivity().startActivity(intent);
				//获得选中项的HashMap对象   
//				HashMap<String,String> map=(HashMap<String,String>)myListView3.getItemAtPosition(arg2);   
//				String title=map.get("itemTitle");   
//				String content=map.get("itemContent");  

//				switch(arg2){
//				case 0:
//					Toast.makeText(getActivity().getApplicationContext(),    
//							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
//							Toast.LENGTH_SHORT).show();	        		
//					break;
//				case 1:
//					Toast.makeText(getActivity().getApplicationContext(),    
//							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
//							Toast.LENGTH_SHORT).show();
//					break;
//				case 2:
//					Toast.makeText(getActivity().getApplicationContext(),    
//							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
//							Toast.LENGTH_SHORT).show(); 
//					break;
//				case 3:
//					Toast.makeText(getActivity().getApplicationContext(),    
//							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
//							Toast.LENGTH_SHORT).show(); 
//					break;
//				}
//				switch(arg2){
//				case 0:
//					
//				case 1:
//					
//				case 2:
//					
//				case 3:
//					Intent intent=new Intent();
//					intent.putExtra("title", "成就秘笈");
//	        		intent.setClass(getActivity(),UnionNewsActivity.class);
//	        		getActivity().startActivity(intent);
//	        		break;
//				}

			}   

		});   
		/************************************************************/      
	}

	public void sendphonenumClick(View view){

	}

}
