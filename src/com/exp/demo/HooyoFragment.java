package com.exp.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;
import com.example.newhoyoo.UnionNewsActivity;
import com.example.newhoyoo.UnionNewsDetail;
import com.example.newhoyoo.YaoqingActivity;
import com.example.newhoyoo.adapter.CustomListViewAdapter;
import com.example.newhoyoo.adapter.InvitationListAdapter02;
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
	InvitationListAdapter02 mAdapter02;
	NewsListAdapter mAdapter3;
	InvitationListAdapter02 mAdapter02;
	/** Called when the activity is first created. */   

	ListView myListView1;
	ListView myListView2; 
	/************************************************************/
	/************************************************************/
	/** Called when the activity is first created. */   
	ListView myListView3; 
	ListView myListView1;


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_hooyo, container, false);

		DispatchEvent.addEventListener("unionStatusChanged", new IMethod<String>() {

			@Override
			public void excute(DEvent<String> event) {
				// TODO Auto-generated method stub
					init();
			}
		});
		return rootView;
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		/******************************11111111111111111111111******************************/
		init();
	}

	public void init(){
		//生成ListView对象   
		myListView1=(ListView)getActivity().findViewById(R.id.listView_news);
		TextView no_union_textview_01 = (TextView)getActivity().findViewById(R.id.no_union_textview_01);
		myListView2=(ListView)getActivity().findViewById(R.id.listView_invite);

		TextView no_union_textview_02 = (TextView)getActivity().findViewById(R.id.no_union_textview_02);
		myListView3=(ListView)getActivity().findViewById(R.id.listView_success);
		TextView no_union_textview_03 = (TextView)getActivity().findViewById(R.id.no_union_textview_03);
		if(Application.getLoginInfo().getUnion() != null &&"normal".equals(Application.getLoginInfo().getUnion().getStatus())){
			no_union_textview_01.setVisibility(View.GONE);
			myListView1.setVisibility(View.VISIBLE);
			List<Map<String,Object>> programeList=new ArrayList<Map<String,Object>>();  
			List<EArticle> list1=Application.getArticleSercice().getTopUnionNews(4, Application.getLoginInfo().getUnion().getId());
			programeList=Application.getArticleSercice().convertFromList(list1);
			mAdapter1=new NewsListAdapter(this.getActivity());
			mAdapter1.setNewsList(programeList);
			myListView1.setAdapter(mAdapter1);
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
				}  
			});
			

			no_union_textview_02.setVisibility(View.GONE);
			myListView2.setVisibility(View.VISIBLE);
			List<Map<String, Object>> programeList2=new ArrayList<Map<String,Object>>();
			programeList2=Application.getInvitationService().getTopInvitation(4, Application.getLoginInfo().getUnion().getId());
			final List<Map<String, Object>> programeList2 = Application.getInvitationService().getTopInvitation(4, Application.getLoginInfo().getUnion().getId());
			mAdapter02=new InvitationListAdapter02(this.getActivity());
			mAdapter02.setInvitationList(programeList2);
			myListView2.setAdapter(mAdapter02);
			myListView2.setOnItemClickListener(new OnItemClickListener(){   
				@Override   
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,   
						long arg3) {   
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

					Intent intent=new Intent();
					HashMap<String,Object> item = (HashMap<String, Object>)myListView2.getItemAtPosition(arg2);
					intent.setClass(getActivity(),YaoqingActivity.class);
					intent.putExtra("item", item);
					getActivity().startActivity(intent);
				}
			});   
			//生成ListView对象  
			no_union_textview_03.setVisibility(View.GONE);
			myListView3.setVisibility(View.VISIBLE);	
			myListView3=(ListView)getActivity().findViewById(R.id.listView_success);
			List<Map<String,Object>> programeList3=new ArrayList<Map<String,Object>>();  
			List<EArticle> list3=Application.getArticleSercice().getTopTips(4, Application.getLoginInfo().getUnion().getId());
			programeList3=Application.getArticleSercice().convertFromList(list3);
			mAdapter3=new NewsListAdapter(this.getActivity());
			mAdapter3.setNewsList(programeList3);
			myListView3.setAdapter(mAdapter3);
			//添加点击事件   
			myListView3.setOnItemClickListener(new OnItemClickListener(){   
				@Override   
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,   
						long arg3) {   
					Map<String,Object> currentMap=(Map<String, Object>)myListView3.getItemAtPosition(arg2);
					Intent intent=new Intent();
					intent.putExtra("type", "成功秘笈");
					intent.putExtra("id", currentMap.get("id").toString());
					intent.setClass(getActivity(), UnionNewsActivity.class);		
		
				}
			});
		}else{
			myListView1.setVisibility(View.GONE);
			no_union_textview_01.setVisibility(View.VISIBLE);
			myListView2.setVisibility(View.GONE);
			no_union_textview_02.setVisibility(View.VISIBLE);
			myListView3.setVisibility(View.GONE);
			no_union_textview_03.setVisibility(View.VISIBLE);
			

		}

	}

	public void sendphonenumClick(View view){

	}

}
