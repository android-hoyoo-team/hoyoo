package com.exp.demo;

import java.util.ArrayList;
import java.util.HashMap;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;
import com.example.newhoyoo.UnionNewsList;
import com.example.newhoyoo.unionInfo;
import com.huyoo.utils.StringHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class NeaerFragment extends Fragment {
    
	AQuery aq;
	/************************************************************/
	/** Called when the activity is first created. */   
    ListView myListView1; 
    HashMap<String, Object> pMap_1=new HashMap<String,Object>(); 
    HashMap<String, Object> pMap1_1=new HashMap<String,Object>(); 
    HashMap<String, Object> pMap2_1=new HashMap<String,Object>(); 
    HashMap<String, Object> pMap3_1=new HashMap<String,Object>();
    /************************************************************/
   
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_near, container, false);
		this.aq = new AQuery(this.getActivity(),rootView);
		return rootView;
	}
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	  super.onViewCreated(view, savedInstanceState);
        
        ListView mylist;
        mylist = (ListView)getActivity().findViewById(R.id.list_achieve);
        mylist.setDividerHeight(0);
        
        this.aq.id(R.id.union_invite).background(R.drawable.buttoneffect);
        this.aq.id(R.id.union_invite).clicked(this, "unionInvite");
        this.aq.id(R.id.union_info).background(R.drawable.buttoneffect);
        this.aq.id(R.id.union_info).clicked(this, "unionInfo");
        this.aq.id(R.id.union_news).background(R.drawable.buttoneffect);
  	  	this.aq.id(R.id.union_news).clicked(this, "unionNews");
  	  	this.aq.id(R.id.union_member).background(R.drawable.buttoneffect);
  	  	this.aq.id(R.id.union_member).clicked(this, "unionMember");
  	  	this.aq.id(R.id.union_manager).background(R.drawable.buttoneffect);
  	  	this.aq.id(R.id.union_manager).clicked(this, "unionManager");
        /******************************11111111111111111111111******************************/
	      //生成ListView对象   
	    myListView1=(ListView)getActivity().findViewById(R.id.list_achieve);
	    ArrayList<HashMap<String,Object>> programeList=new ArrayList<HashMap<String,Object>>();  
	    for(int i=0;i<5;i++) 
	    { 
	        	switch(i){
	        	case 0:
	        		
	            	pMap_1.put("Pname","李汇泽"); 
	            	pMap_1.put("Pachieve", StringHelper.FormatAchievement("24缺1,来的密")); 
	            	programeList.add(pMap_1);
	            	break;
	        	case 1:
	        		
	            	pMap1_1.put("Pname","李汇泽"); 
	            	pMap1_1.put("Pachieve",  StringHelper.FormatAchievement("24缺1,来的密"));  
	            	programeList.add(pMap1_1);
	            	break;
	        	case 2:
	        		
	            	pMap2_1.put("Pname","李汇泽"); 
	            	pMap2_1.put("Pachieve",  StringHelper.FormatAchievement("24缺1,来的密")); 
	            	programeList.add(pMap2_1);
	            	break;
	        	case 3:
	        		
	            	pMap3_1.put("Pname","李汇泽"); 
	            	pMap3_1.put("Pachieve",  StringHelper.FormatAchievement("24缺1,来的密"));  
	            	programeList.add(pMap3_1);
	            	break;
	            }
	        } 
	           
	        //生成SimpleAdapter适配器对象   
	        final SimpleAdapter mySimpleAdapter=new SimpleAdapter(this.getActivity(),   
	        		programeList,
	        		//myArrayList,//数据源   
	                R.layout.item_union,//ListView内部数据展示形式的布局文件listitem.xml   
	                new String[]{"Pname","Pachieve" },//HashMap中的两个key值 itemTitle和itemContent   ,"itemContent"
	                new int[]{R.id.Pname_item,R.id.Pachieve_item });
	        /*布局文件listitem.xml中组件的id    ,R.id.itemContent布局文件的各组件分别映射到HashMap的各元素上，完成适配*/   
	           
	        myListView1.setAdapter(mySimpleAdapter);      
	    /************************************************************/
    }
    
    public void unionInvite()
    {
    	Toast.makeText(getActivity().getApplicationContext(),    
                "你选择了第"+5+"个Item，itemTitle的值是："+5+"itemContent的值是:"+5,   
                Toast.LENGTH_SHORT).show(); 
    }
    public void unionInfo()
    {
    	Intent intent = getActivity().getIntent();
    	intent.setClass(getActivity(),unionInfo.class);
    	getActivity().startActivity(intent);
    }
    public void unionNews()
    {
    	Intent intent = getActivity().getIntent();
    	intent.setClass(getActivity(), UnionNewsList.class);
    	getActivity().startActivity(intent);
    }
    public void unionMember()//返回主界面
    {
    	Toast.makeText(getActivity().getApplicationContext(),    
                "你选择了第"+5+"个Item，itemTitle的值是："+5+"itemContent的值是:"+5,   
                Toast.LENGTH_SHORT).show(); 
    }
    public void unionManager()//返回主界面
    {
    	Toast.makeText(getActivity().getApplicationContext(),    
                "你选择了第"+5+"个Item，itemTitle的值是："+5+"itemContent的值是:"+5,   
                Toast.LENGTH_SHORT).show(); 
    }
    private void skip_shezhi()//返回主界面
    {
    	 
    }
}
