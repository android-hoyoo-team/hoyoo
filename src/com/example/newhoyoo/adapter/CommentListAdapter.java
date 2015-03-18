package com.example.newhoyoo.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.androidquery.AQuery;
import com.example.newhoyoo.Comment;
import com.example.newhoyoo.R;
import com.example.newhoyoo.R.layout;
import com.example.newhoyoo.YaoqingActivity;
import com.huyoo.utils.DateUtil;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CommentListAdapter extends BaseAdapter {
	private List<Map<String, Object>> adapterList=new ArrayList<Map<String,Object>>();
	private LayoutInflater mInflater;
	public CommentListAdapter(Context context){
		this.mInflater=LayoutInflater.from(context);
	}
	public void setCommentList(List<Map<String, Object>> list){
		this.adapterList=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return adapterList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return adapterList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.comment_list_item, null);
		}
		AQuery aq=new AQuery(convertView);
		final Map<String, Object> map=(Map<String, Object>)getItem(position);
		aq.id(R.id.comment_from_person_icon).image(map.get("personUrl").toString());
		aq.id(R.id.comment_from_person_name).text(map.get("personName").toString());
		aq.id(R.id.comment_content).text(map.get("content").toString());
		String commentDate=DateUtil.date2Str(new Date(Long.parseLong(map.get("commentTime").toString())), "yyyy-MM-dd HH:mm:ss");
		aq.id(R.id.comment_time).text(commentDate);
//		aq.id(R.id.comment_button).clicked(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent();
//				intent.setClass(YaoqingActivity.this, Comment.class);
//			}
//		});
		return convertView;
	}

}
