package com.example.newhoyoo.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;
import com.huyoo.utils.DateUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsListAdapter extends BaseAdapter {
	private List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
	private LayoutInflater mInflater;

	public NewsListAdapter(Context context) {
		this.mInflater = LayoutInflater.from(context);
	}

	public void setNewsList(List<Map<String, Object>> list) {
		if (list != null) {
			this.newsList = list;
		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.newsList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.newsList.get(position);
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
		// ViewHolder viewHolder = new ViewHolder();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_hoyoo, null);
			// viewHolder.icon = (ImageView) convertView
			// .findViewById(R.id.imageview_item);
			// viewHolder.title = (TextView) convertView
			// .findViewById(R.id.textview_item);
			// viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			// viewHolder.time = (TextView) convertView.findViewById(R.id.time);
			// viewHolder.readers = (TextView) convertView
			// .findViewById(R.id.readersnum);
			// convertView.setTag(viewHolder);
		}
		aq = new AQuery(convertView);
		// else{
		// viewHolder=(ViewHolder)convertView.getTag();
		// }
		final Map<String, Object> item = (Map<String, Object>) getItem(position);
		aq.id(R.id.imageview_item).image(item.get("picture").toString());
		aq.id(R.id.textview_item).text(item.get("title").toString());
		aq.id(R.id.name).text(item.get("name").toString());
		long seconds=new Date().getTime()- Long.valueOf(item.get("time").toString());
		 
		aq.id(R.id.time).text(DateUtil.getTimeBeforeNow(seconds));
		aq.id(R.id.readersnum).text(item.get("readers").toString());

		// viewHolder.title.setText(item.get("title")==null?"":item.get("title").toString());
		// viewHolder.name.setText(item.get("name")==null?"":item.get("name").toString());
		// String time=new SimpleDateFormat("yyyy-MM-dd").format(new Date(new
		// Date().getTime()-Long.valueOf(item.get("time").toString())));
		// viewHolder.time.setText(time);
		// viewHolder.readers.setText(item.get("readers")==null?"":item.get("readers").toString());
		// viewHolder.icon.
		return convertView;
	}
	

	// public final class ViewHolder {
	// public ImageView icon;
	// public TextView title;
	// public TextView name;
	// public TextView time;
	// public TextView readers;
	// }

}
