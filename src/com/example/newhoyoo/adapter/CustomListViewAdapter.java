package com.example.newhoyoo.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.androidquery.AQuery;

public class CustomListViewAdapter extends SimpleAdapter{

	private LayoutInflater inflater = null;
	private List<HashMap<String,Object>> data;
	private String[] from;
	private int[] to;
	private String[] operation;
	private int resource;

	public CustomListViewAdapter(Context context,
			List<HashMap<String, Object>> d, int res,
			String[] f, int[] t,String[] op) {
		super(context, d, res, f, t);
		data = d;
		from = f;
		to = t;
		operation = op;
		resource = res;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
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
		HashMap<String,Object> d = new HashMap<String, Object>();
		d = data.get(position);
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
}