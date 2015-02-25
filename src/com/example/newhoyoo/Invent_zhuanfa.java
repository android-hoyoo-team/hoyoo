package com.example.newhoyoo;

import com.androidquery.AQuery;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.os.Build;

public class Invent_zhuanfa extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.invent_zhuanfa);
		AQuery aq = new AQuery(this);
		aq.id(R.id.invent_back).clicked(this, "back");
		aq.id(R.id.fabu_button).clicked(this, "fabu");
	}
	public void back(){
		this.finish();
	}
	public void fabu(){
		
	}
}
