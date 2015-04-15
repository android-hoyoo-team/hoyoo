package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.newhoyoo.adapter.InvitationListAdapter;
import com.huyoo.global.Application;
import com.viewflow.xlistviewfresh.XListView;
import com.viewflow.xlistviewfresh.XListView.IXListViewListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UnionInviteList extends Activity{

	private XListView mine_list;
	private TextView no_invite_textview;
	private InvitationListAdapter mAdapter;
	private List<Map<String, Object>> list_data=new ArrayList<Map<String, Object>>();
	private Handler mHandler;
	private ImageButton leftImageButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invite_union_list);
		leftImageButton=(ImageButton)findViewById(R.id.image_button_left);
		leftImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		unionId = getIntent().getIntExtra("unionId", 0);
		if(unionId==0)
		{
			Toast.makeText(this, "该公会不存在", Toast.LENGTH_LONG).show();
			return;
		}
		TextView invite_list_title=(TextView) findViewById(R.id.invite_list_title);
		invite_list_title.setText("公会邀请");
		mine_list = (XListView) findViewById(R.id.mine_list);
		no_invite_textview = (TextView)findViewById(R.id.you_no_invite_textview);
		if(true)
		{
			no_invite_textview.setVisibility(View.GONE);
			mine_list.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
			mine_list.setPullRefreshEnable(false);// 设置让它下拉，FALSE为不让下拉，便不加载更多数据
			mAdapter = new InvitationListAdapter(this);
			loadMoreData(count);
			mAdapter.setInvitationList(list_data);
			mine_list.setAdapter(mAdapter);
			mHandler = new Handler();
			mine_list.setXListViewListener(new IXListViewListener() {
				
				@Override
				public void onRefresh() {
				}
				
				@Override
				public void onLoadMore() {
					mHandler.postDelayed(new Runnable() {

						@Override
						public void run() {
							loadMoreData(count);
							mAdapter.setInvitationList(list_data);
//							mAdapter1.notifyDataSetChanged();
							mAdapter.notifyDataSetChanged();
							mine_list.stopRefresh();
							mine_list.stopLoadMore();
							mine_list.setRefreshTime("刚刚");
						}

					}, 2000);
					
				}
			});
		}
		else
		{
			
		}
	}
	private void loadMoreData(int count) {
		list_data = Application.getInvitationService().getInvitationsMapByUnionId(unionId,0,list_data.size()+count,"issueTime");
		listIndex=list_data.size();
	}
	int listIndex=0;
	int count=5;
	private int unionId;


}
