package com.exp.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
		//initNoUnion();
		initInvited();
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
		long total = 12*60*60*1000;
		new CountDownTimer(total,1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				long left = millisUntilFinished/1000;
				long second = left%60;
				long minute = (left/60)%60;
				long hour = left/3600;
				if(getActivity()!=null)
				((TextView)getActivity().findViewById(R.id.apply_timer_textview)).setText(String.format("%02d:%02d:%02d", hour,minute,second));
			}
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				aq.id(R.id.apply_timer_textview).text("时间到了！");
			}
		}.start();
	}
	//没有工会，有邀请
	public void initInvited(){
		this.aq.id(R.id.applying_linearlayout).visibility(View.GONE);
		this.aq.id(R.id.no_union_linearlayout).visibility(View.GONE);
		this.aq.id(R.id.invited_linearlayout).visibility(View.VISIBLE);
		CustomActionbar actionbar = (CustomActionbar)getActivity().findViewById(R.id.main_actionbar);
		actionbar.setTitleVisibility(View.VISIBLE);
		actionbar.setTitle("您的邀请");
		List<EUnion> unions = Application.getUnionService().getUnionsByPersonId(Application.getLoginInfo().getPerson().getId());
		final List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		for(int i =0;i<unions.size();i++){
			HashMap<String,Object> map = new HashMap<String, Object>();
			EUnion union = unions.get(i);
			map.put("pName", Application.getPersonService().getEPersonById(union.getChairmanId()).getName());
			map.put("uName", union.getName());
			map.put("status", union.getStatus());
			map.put("id", union.getId());
			list.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(getActivity(), list,
				R.layout.item_union_invitation,
				new String[]{"pName","uName","status"}, 
				new int[]{R.id.name_textview,R.id.union_textview,R.id.status_textview}){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if(convertView == null){
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_union_invitation, null);
				}
				convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.union_invitation_height)));
				final HashMap<String, Object> selectedUnion = list.get(position);
				convertView.findViewById(R.id.agree_textview).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						AlertDialog dialog = new AlertDialog.Builder(getActivity())//.setView(layout)
						.setTitle("您确定加入公会:"+selectedUnion.get("uName")+"?")
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
						})
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								CustomActionbar actionbar = (CustomActionbar)getActivity().findViewById(R.id.main_actionbar);
								actionbar.setTitleVisibility(View.GONE);

								//EUnion union = Application.getUnionService().getEUnionByID(Integer.parseInt(selectedUnion.get("id").toString()));
								//switch (union.getStatus()) {
								switch (selectedUnion.get("status").toString()) {
								case "normal":
									//如果加入的公会已经审批通过，则直接进入公会界面
									Main main = (Main)getActivity();
									if(main.getUnionFragment()==null){
										main.setUnionFragment(new UnionFragment());
										main.getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, main.getUnionFragment()).commit();
									}else{
										main.getSupportFragmentManager().beginTransaction().show(main.getUnionFragment()).commit();
									}
									break;
								case "approvalling":
									//如果加入的公会还在审批中，进入等待审批页面
									initApplying();
									break;
								case "unpassed":
								case "dismissed":
									//如果审批未通过，弹出对话框告知，并刷新邀请列表,如果没有邀请，进入查找和创建公会页面。
									AlertDialog _dialog = new AlertDialog.Builder(getActivity())
									.setTitle("该工会审批未通过或者已经被解散")
									.setPositiveButton("确定", new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											initInvited();
										}
									}).create();
									_dialog.setCanceledOnTouchOutside(false);
									_dialog.show();
									break;
//								case "dismissed":
//									//如果邀请的公会已经解散了，弹出对话框告知，并刷新邀请列表,如果没有邀请，进入查找和创建公会页面。
								default:
									break;
								}
								dialog.cancel();
							}
						}).create();
						dialog.setCanceledOnTouchOutside(false);
						dialog.show();
					}
				});
				convertView.findViewById(R.id.reject_textview).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						AlertDialog dialog = new AlertDialog.Builder(getActivity())
						.setTitle("你确定拒绝公会： "+selectedUnion.get("uName")+" 的邀请？")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								initInvited();
							}
						}).create();
						dialog.setCanceledOnTouchOutside(false);
						dialog.show();
					}
				});
				return super.getView(position, convertView, parent);
			}
		};
		this.aq.id(R.id.invited_listview).adapter(adapter);
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
