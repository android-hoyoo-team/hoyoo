package com.example.newhoyoo.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;

public class CustomListViewAdapter extends BaseAdapter{

	private LayoutInflater inflater = null;
	private List<Map<String,Object>> data;
	private String[] from;
	private int[] to;
	private String[] operation;
	private int resource;

	public CustomListViewAdapter(Context context,
			List<Map<String, Object>> d, int res,
			String[] f, int[] t,String[] op) {
//		super(context, d, res, f, t);
		this.data=d;
		from = f;
		to = t;
		operation = op;
		resource = res;
		inflater = LayoutInflater.from(context);
	}
	public void setData(List<Map<String, Object>> d)
	{
		this.data=d;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v= inflater.inflate(resource, null);
		AQuery aq = new AQuery(v);
		Map<String,Object> d = (Map<String, Object>) getItem(position);
		for(int i = 0 ; i < to.length ; i++){
			switch(operation[i])
			{
			case "image":
				aq.id(to[i]).image(d.get(from[i]).toString());
				break;
			case "imageLocal":
				aq.id(to[i]).image((Integer)d.get(from[i]));
				break;
			case "check":
				aq.id(to[i]).checked((boolean)d.get(from[i]));
				break;
			case "text":
				aq.id(to[i]).text(d.get(from[i]).toString());
				break;
			}
		}
		return v;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(data!=null)
			return this.data.size();
		return 0;
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if(this.data.size()>0)
			return this.data.get(position);
		return null;
	}
}