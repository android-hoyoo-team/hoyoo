package com.ryg.expandable.ui;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CustomActionbar extends FragmentActivity {
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.custom_actionbar_01, container, false);
		return rootView;
	}
	
//	public void setActionbar(int img)
//	{
//		AQuery aq = new AQuery(this);
//		aq.id()
//	}
}
