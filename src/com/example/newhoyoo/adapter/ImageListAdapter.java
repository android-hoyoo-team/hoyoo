package com.example.newhoyoo.adapter;

import java.util.List;
import java.util.Map;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ImageListAdapter extends BaseAdapter {
	private List<String> iconList;
	private LayoutInflater mInflater;
	public ImageListAdapter(Context context){
		this.mInflater=LayoutInflater.from(context);
	}
	public void setMapList(List<String> list){
		if(list!=null){
			this.iconList=list;
		}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return iconList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return iconList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView( final int position, View convertView, ViewGroup parent) {
		if(convertView==null)
		{
			
			// TODO Auto-generated method stub
			convertView=mInflater.inflate(R.layout.invitation_image_item, null);
			AQuery aq=new AQuery(convertView);
			String icon=(String)getItem(position);
//		Map<String, Object> map=(Map<String, Object>)getItem(position);
			aq.id(R.id.image_item).image(icon).clicked(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					DispatchEvent.dispatchEvent(new DEvent<Integer>("ImageListAdapterClick", position));
				}
			});
			if(position==0){
				aq.id(R.id.image_select).visibility(View.VISIBLE);
			}
		}
		return convertView;
	}

}
