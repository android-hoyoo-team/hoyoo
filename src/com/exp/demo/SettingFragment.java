package com.exp.demo;

import java.util.HashMap;

import com.example.newhoyoo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SettingFragment extends Fragment {
	/***************************1111111111*********************************/
    ListView myListView_main1; 
    HashMap<String, Object> pMap_main1=new HashMap<String,Object>();  
	/************************************************************/
    
	/*****************************2222222222222*******************************/
	/** Called when the activity is first created. */   
    ListView myListView_main2; 
    HashMap<String, Object> pMap_main2_0=new HashMap<String,Object>(); 
    HashMap<String, Object> pMap_main2_1=new HashMap<String,Object>(); 
    HashMap<String, Object> pMap_main2_2=new HashMap<String,Object>();
    /************************************************************/
    
    /***************************33333333333*********************************/
    ListView myListView_main3; 
    HashMap<String, Object> pMap_main3_0=new HashMap<String,Object>();  
    HashMap<String, Object> pMap_main3_1=new HashMap<String,Object>();
	/************************************************************/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
		return rootView;
	}

}
