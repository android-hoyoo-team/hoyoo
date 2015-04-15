package com.exp.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.example.newhoyoo.CreateUnionCondition;
import com.example.newhoyoo.Main;
import com.example.newhoyoo.R;
import com.huyoo.entity.EUnion;
import com.huyoo.entity.RUnionPerson;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.CustomActionbar;

public class NoUnionFragment extends Fragment {
	AQuery aq;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_no_union, container, false);
		aq = new AQuery(getActivity(),rootView);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		if(Application.getLoginInfo().getUnion()!=null){
			initApplying();
		}else{
			initNoUnion();
		}
	}

	//没有公会，没有申请，没有邀请
	public void initNoUnion(){
		this.aq.id(R.id.applying_linearlayout).visibility(View.GONE);
		this.aq.id(R.id.invited_linearlayout).visibility(View.GONE);
		this.aq.id(R.id.no_union_linearlayout).visibility(View.VISIBLE);
		this.aq.id(R.id.search_union_button).clicked(this, "searchUnion");
		this.aq.id(R.id.create_union_button).clicked(this, "createUnion");
	}
	//没有工会，正在申请中
	public void initApplying(){
		this.aq.id(R.id.no_union_linearlayout).visibility(View.GONE);
		this.aq.id(R.id.invited_linearlayout).visibility(View.GONE);
		this.aq.id(R.id.applying_linearlayout).visibility(View.VISIBLE);
		long startTime = Application.getLoginInfo().getUnion().getTime();
		long totalLeft = new Date().getTime() - startTime - 86400000;
		long total = 12*60*60*1000;
		new CountDownTimer(10000,1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				long left = millisUntilFinished/1000;
				long second = left%60;
				long minute = (left/60)%60;
				long hour = left/3600;
				if(getActivity()!=null)
					aq.id(R.id.apply_timer_textview).text(String.format("%02d:%02d:%02d", hour,minute,second));
			}
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				Application.getLoginInfo().setStatus("pass");
				//申请成功
				if(Application.getLoginInfo().getUnion()!=null){
					DispatchEvent.dispatchEvent(new DEvent("unionStatusChanged","message"));
					Main main = (Main)getActivity();
					if("itemAssociation".equals(Main.currentFragment)){
						if(main.getUnionFragment()==null){
							main.setUnionFragment(new UnionFragment());
							main.getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, main.getUnionFragment()).commit();
						}else{
							main.getSupportFragmentManager().beginTransaction().show(main.getUnionFragment()).commit();
						}
					}
				}else{
					initNoUnion();
				}
			}
		}.start();
	}

	public void initList(){

	}
	public void searchUnion(){

	}
	public void createUnion(){
		Intent intent = new Intent();
		intent.setClass(getActivity(), CreateUnionCondition.class);
		getActivity().startActivity(intent);
	}
}
