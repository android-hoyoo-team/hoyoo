package com.exp.demo;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;
import com.example.newhoyoo.personInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MineFragment extends Fragment {
	SeekBar seekbar1;
	SeekBar seekbar2;
    AQuery aq;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_mine, container, false);
		this.aq = new AQuery(this.getActivity(),rootView);
		return rootView;
	}
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	  super.onViewCreated(view, savedInstanceState);
        
    	  seekbar1=(SeekBar)getActivity().findViewById(R.id.seekBar1);
    	  seekbar1.setEnabled(false);
    	  seekbar2=(SeekBar)getActivity().findViewById(R.id.seekBar2);
    	  seekbar2.setEnabled(false);
    	  
    	  seekbar1.setMax(3000);
    	  seekbar1.setProgress(2730);
    	  
    	  seekbar2.setMax(150);
    	  seekbar2.setProgress(40);
        
    	  this.aq.id(R.id.button1).background(R.drawable.image1);
    	  this.aq.id(R.id.button1).clicked(this,"myinvited");
    	  this.aq.id(R.id.button2).background(R.drawable.image2);
    	  this.aq.id(R.id.button2).clicked(this,"respond");
    	  this.aq.id(R.id.button3).background(R.drawable.image3);
    	  this.aq.id(R.id.button3).clicked(this,"comment");
    	  this.aq.id(R.id.button4).background(R.drawable.image4);
    	  this.aq.id(R.id.button4).clicked(this,"good");
    	  this.aq.id(R.id.button5).background(R.drawable.image5);
    	  this.aq.id(R.id.button5).clicked(this,"letter");
    	  
    	  this.aq.id(R.id.focus).clicked(this, "skiptofocus");
    	  this.aq.id(R.id.fans).clicked(this, "skiptofans");
    	  this.aq.id(R.id.achieve).clicked(this, "skiptoachieve");
    	  
    	  this.aq.id(R.id.imageView3).clicked(this,"skiptodetail");
    }
    
    public void skiptodetail(){
    	this.aq.id(R.id.imageView3).image(R.drawable.bt_17_press_01);
    	Intent intent = new Intent();
    	intent.setClass(getActivity(),personInfo.class);
    	this.getActivity().startActivity(intent);	
    }
    
    public void skiptofocus()
    {
    	this.aq.id(R.id.focus).background(R.drawable.buttoneffect);
    	Toast.makeText(getActivity().getApplicationContext(),    
                "你选择了第"+5+"个Item，itemTitle的值是："+5+"itemContent的值是:"+5,   
                Toast.LENGTH_SHORT).show(); 
    }
    public void skiptofans()
    {
    	this.aq.id(R.id.fans).background(R.drawable.buttoneffect);
    	Toast.makeText(getActivity().getApplicationContext(),    
                "你选择了第"+5+"个Item，itemTitle的值是："+5+"itemContent的值是:"+5,   
                Toast.LENGTH_SHORT).show(); 
    }
    public void skiptoachieve()
    {
    	this.aq.id(R.id.achieve).background(R.drawable.buttoneffect);
    	Toast.makeText(getActivity().getApplicationContext(),    
                "你选择了第"+5+"个Item，itemTitle的值是："+5+"itemContent的值是:"+5,   
                Toast.LENGTH_SHORT).show(); 
    }
    
    
    public void myinvited()//返回主界面
    {
    }
    public void respond()//返回主界面
    {
    	 
    }
    public void comment()//返回主界面
    {
    	
    }
    public void good()//返回主界面
    {
    	
    }
    public void letter()//返回主界面
    {
    	 
    }
}
