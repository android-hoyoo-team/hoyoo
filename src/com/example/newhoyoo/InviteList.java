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

/**
 *主页邀请列表，目前获取的是工会的邀请，和工会页面中的工会邀请获取的信息一致，后期应根据具体需求进行更改
 * 此类在使用时需要 通过intent 传入"personId"这个参数
 */
public class InviteList extends Activity{

	private XListView mine_list;
	//如果用户没有加入任何工会的时候会显示 这个text
	private TextView no_invite_textview;
	private InvitationListAdapter mAdapter;
	private List<Map<String, Object>> list_data=new ArrayList<Map<String, Object>>();
	private Handler mHandler;
	//返回按钮
	private ImageButton leftImageButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invite_list);
		leftImageButton=(ImageButton)findViewById(R.id.image_button_left);
		//返回，结束当前activity
		leftImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		personId = getIntent().getIntExtra("personId", 0);
		if(personId==0)
		{
			Toast.makeText(this, "该用户不存在", Toast.LENGTH_LONG).show();
			return;
		}
		TextView invite_list_title=(TextView) findViewById(R.id.invite_list_title);
		invite_list_title.setText("我的邀请");
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
	/**
	 * 延迟加载更多邀请
	 * @param count
	 */
	private void loadMoreData(int count) {
		list_data = Application.getInvitationService().getInvitationMapsByPersonId(personId,0,list_data.size()+count,"issueTime");
		listIndex=list_data.size();
	}
	int listIndex=0;
	int count=5;
	private int personId;


}
