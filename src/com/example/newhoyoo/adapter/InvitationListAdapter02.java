package com.example.newhoyoo.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;
import com.huyoo.utils.DateUtil;
import com.huyoo.utils.GsonUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 *用于主页中 热门邀请的 列表
 */
public class InvitationListAdapter02 extends BaseAdapter {
	private List<Map<String,Object>> invitationList=new ArrayList<Map<String,Object>>();
	private LayoutInflater mInflater;
	public InvitationListAdapter02(Context context){
		mInflater=LayoutInflater.from(context);
	} 
	public void setInvitationList(List<Map<String, Object>> list){
		if (list != null) {
			this.invitationList = list;
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return invitationList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return invitationList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		AQuery aq;
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.item_hoyoo, null);
			
		}
		aq=new AQuery(convertView);
		final Map<String, Object> item=(Map<String, Object>)getItem(position);
		if(item.get("icons")!=null){
			//解析图片
			List<String> image=(List<String>)GsonUtil.jsonToList(item.get("icons").toString());
			aq.id(R.id.imageview_item).image(image.get(0));
		}
		aq.id(R.id.textview_item).text(item.get("content").toString());
		aq.id(R.id.name).text(item.get("personName").toString());
		long seconds=new Date().getTime()- Long.valueOf(item.get("issueTime").toString());		 
		aq.id(R.id.time).text(DateUtil.getTimeBeforeNow(seconds));
		aq.id(R.id.readersnum).text(item.get("hits").toString());
		return convertView;
	}

}
