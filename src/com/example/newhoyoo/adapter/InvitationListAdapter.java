package com.example.newhoyoo.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import main.java.com.sefford.circularprogressdrawable.sample.CircularProgressDrawable;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newhoyoo.R;
import com.huyoo.utils.DateUtil;

public class InvitationListAdapter extends BaseAdapter {

	private List<Map<String,Object>> invitationList=new ArrayList<Map<String,Object>>();
	private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局

	public InvitationListAdapter(Context context) {
		this.mInflater = LayoutInflater.from(context);
	}
	public void setInvitationList(List<Map<String,Object>> list)
	{
		if(list!=null)
			invitationList=list;
	}
	@Override
	public int getCount() {
		return invitationList.size();
	}

	@Override
	public Object getItem(int position) {
		return invitationList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.invitation_item,null);
			/**得到各个控件的对象*/                    
			holder.personName = (TextView) convertView.findViewById(R.id.text_us_name);
			holder.personLevel = (TextView) convertView.findViewById(R.id.cheng_jiu);
			holder.issueTime = (TextView) convertView.findViewById(R.id.text_fabu_date);
			holder.content = (TextView) convertView.findViewById(R.id.text_yaoq_info);
			holder.activityTime_date = (TextView) convertView.findViewById(R.id.text_yaoq_date);
			holder.activityTime_time = (TextView) convertView.findViewById(R.id.text_yaoq_time);
			holder.address = (TextView) convertView.findViewById(R.id.text_yaoq_address);
			holder.info1 = (TextView) convertView.findViewById(R.id.textView15);
			holder.info2 = (TextView) convertView.findViewById(R.id.textView16);
			holder.progress = (ImageView) convertView.findViewById(R.id.canjia_progress);
			convertView.setTag(holder);//绑定ViewHolder对象                   
		}
		else{
			holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象                  
		}
		final Map<String,Object> item = (Map<String,Object>)getItem(position);
		/*
		personName
		personLevel
		issueTime
		activityTime
		content
		address
		currentNum
		maxNum
		isJoin 0:1(join)
		 * */
		holder.personName.setText(item.get("personName")==null?"":item.get("personName").toString());
		holder.personLevel.setText(item.get("personLevel")==null?"":item.get("personLevel").toString());
		holder.address.setText(item.get("address")==null?"":item.get("address").toString());
		Object  _issueTime = item.get("issueTime");
		if(_issueTime==null)
		{
			_issueTime="";
		}
		else
		{
			_issueTime = DateUtil.date2Str(new Date(Long.parseLong(_issueTime.toString())));
		}
		holder.issueTime.setText(_issueTime.toString());
		holder.content.setText(item.get("content")==null?"":item.get("content").toString());
		Object  _activityTime = item.get("activityTime");
		String _activityTime_date,_activityTime_time;
		if(_activityTime==null)
		{
			_activityTime_date="";
			_activityTime_time="";
		}
		else
		{
			_activityTime_date = DateUtil.date2Str(new Date(Long.parseLong(_activityTime.toString())),"yyyy年MM月dd");
			_activityTime_time = DateUtil.date2Str(new Date(Long.parseLong(_activityTime.toString())),"HH:mm:ss");
		}
		holder.activityTime_date.setText(_activityTime_date);
		holder.activityTime_time.setText(_activityTime_time);
		int isJoin = item.get("isJoin")==null?0:Integer.parseInt(item.get("isJoin").toString());

		final int currentNum=item.get("currentNum")==null?0:Integer.parseInt(item.get("currentNum").toString());
		final int maxNum=item.get("maxNum")==null?0:Integer.parseInt(item.get("maxNum").toString());
		if(isJoin==1)
		{
			holder.info1.setText("已参与");
		}
		else if(currentNum==maxNum)
		{
			holder.info1.setText("人数已满");
		}
		else if(currentNum==maxNum)
		{
			holder.info1.setText("点击参与");
		}
		holder.info2.setText(currentNum+"/"+maxNum);


		final CircularProgressDrawable  drawable = new CircularProgressDrawable(convertView.getResources().getDimensionPixelSize(R.dimen.drawable_ring_size),
				convertView.getResources().getColor(android.R.color.darker_gray),
				convertView.getResources().getColor(android.R.color.holo_red_dark),
				convertView.getResources().getColor(android.R.color.holo_red_dark));
		float p=(float) (currentNum/(maxNum+0.0));
		drawable.setProgress(p);
		drawable.setCircleScale(p);
//		final Map<String,Integer> tag=new HashMap<String, Integer>();
//		tag.put("progress", drawable);
//		tag.put("isJoin", isJoin);
//		holder.progress.setTag(tag);
		holder.progress.setImageDrawable(drawable);
		final ViewHolder _holder=holder;
		if(currentNum<maxNum)
		{
			holder.progress.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					int vid=v.getId();
					if(vid==_holder.content.getId())
					{
						
					}
					if(vid==_holder.progress.getId())
					{
//					Map<String,Object> tag = (Map<String,Object>) holder.progress.getTag();
						int jo=item.get("isJoin")==null?0:Integer.parseInt(item.get("isJoin").toString());
						if(jo==1)
						{
							item.put("isJoin", 0);
							item.put("currentNum", currentNum-1);
							drawable.setCircleScale_(drawable.getCircleScale()-0.1f);
							drawable.setProgress_(drawable.getProgress()-0.1f);
							_holder.info1.setText("点击参与");
							_holder.info2.setText(currentNum-1+"/"+maxNum);
						}
						else
						{
							drawable.setCircleScale_(drawable.getCircleScale()+0.1f);
							drawable.setProgress_(drawable.getProgress()+0.1f);
							item.put("isJoin", 1);
							item.put("currentNum", currentNum+1);
							_holder.info1.setText("已参与");
							_holder.info2.setText(currentNum+1+"/"+maxNum);
						}
					}
				}
			});
		}
		/**为Button添加点击事件*/             
		//        holder.bt.setOnClickListener(new OnClickListener() {
		//            @Override
		//            publicvoid onClick(View v) {
		//                Log.v("MyListViewBase", "你点击了按钮" + position);//打印Button的点击信息                    
		//            }
		//        });
		return convertView;
	}
	private void getInfo1(Object info1)
	{

	}
	public final class ViewHolder{
		public TextView personName;
		public TextView personLevel;
		public TextView issueTime;
		public TextView content;
		public TextView activityTime_date;
		public TextView activityTime_time;
		public TextView address;
		public TextView info1;
		public TextView info2;
		public ImageView progress;

	}

}
