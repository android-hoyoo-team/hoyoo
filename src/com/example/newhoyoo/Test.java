package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.HashMap;

import com.androidquery.AQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Test extends Activity {
    
	AQuery aq;
	/************************************************************/
	/** Called when the activity is first created. */   
    ListView myListView1; 
    HashMap<String, Object> pMap_1=new HashMap<String,Object>(); 
    HashMap<String, Object> pMap1_1=new HashMap<String,Object>(); 
    HashMap<String, Object> pMap2_1=new HashMap<String,Object>(); 
    HashMap<String, Object> pMap3_1=new HashMap<String,Object>();
    /************************************************************/
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_union);
        this.aq = new AQuery(this);
        
        ListView mylist;
        mylist = (ListView)findViewById(R.id.list_achieve);
        mylist.setDividerHeight(0);
        /******************************11111111111111111111111******************************/
	      //生成ListView对象   
	    myListView1=(ListView)findViewById(R.id.list_achieve);
	    ArrayList<HashMap<String,Object>> programeList=new ArrayList<HashMap<String,Object>>();  
	    for(int i=0;i<5;i++) 
	    { 
	        	switch(i){
	        	case 0:
	        		
	            	pMap_1.put("Pname","give me your name!"); 
	            	pMap_1.put("Pachieve", "WTF!"); 
	            	programeList.add(pMap_1);
	            	break;
	        	case 1:
	        		
	            	pMap1_1.put("Pname","give me your name!"); 
	            	pMap1_1.put("Pachieve", "WTF!");  
	            	programeList.add(pMap1_1);
	            	break;
	        	case 2:
	        		
	            	pMap2_1.put("Pname","give me your name!"); 
	            	pMap2_1.put("Pachieve", "WTF!"); 
	            	programeList.add(pMap2_1);
	            	break;
	        	case 3:
	        		
	            	pMap3_1.put("Pname","give me your name!"); 
	            	pMap3_1.put("Pachieve", "WTF!");  
	            	programeList.add(pMap3_1);
	            	break;
	            }
	        } 
	           
	        //生成SimpleAdapter适配器对象   
	        final SimpleAdapter mySimpleAdapter=new SimpleAdapter(this,   
	        		programeList,
	        		//myArrayList,//数据源   
	                R.layout.item_union,//ListView内部数据展示形式的布局文件listitem.xml   
	                new String[]{"Pname","Pachieve" },//HashMap中的两个key值 itemTitle和itemContent   ,"itemContent"
	                new int[]{R.id.Pname_item,R.id.Pachieve_item });
	        /*布局文件listitem.xml中组件的id    ,R.id.itemContent布局文件的各组件分别映射到HashMap的各元素上，完成适配*/   
	           
	        myListView1.setAdapter(mySimpleAdapter);      
	    /************************************************************/
    }
    
    private void skip_shezhi()//返回主界面
    {
    	startActivity(new Intent(this,MainActivity.class));  
        finish();  
    }
}
