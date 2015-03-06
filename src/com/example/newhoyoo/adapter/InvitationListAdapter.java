package com.example.newhoyoo.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import main.java.com.sefford.circularprogressdrawable.sample.CircularProgressDrawable;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.example.newhoyoo.R;
import com.huyoo.global.Application;
import com.huyoo.utils.DateUtil;

public class InvitationListAdapter extends BaseAdapter{

	private List<Map<String,Object>> invitationList=new ArrayList<Map<String,Object>>();
	private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
	private Object mFilter;
	final private Context context;
	private AQuery aq;

	public InvitationListAdapter(Context context) {
		this.context=context;
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
			holder.image_us_photo = (ImageView) convertView.findViewById(R.id.image_us_photo);
			convertView.setTag(holder);//绑定ViewHolder对象                   
		}
		else{
			holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象                  
		}
		final ViewHolder _holder=holder;
		final Map<String,Object> item = (Map<String,Object>)getItem(position);
		/*
		 personUrl
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
		aq = new AQuery(context);
		Object personUrl = item.get("personUrl");
		if(personUrl!=null&&!personUrl.toString().trim().equals(""))
		{
			String url = personUrl.toString();
			aq.id(R.id.image_us_photo).image(url);
//			aq.ajax(url, Bitmap.class, new AjaxCallback<Bitmap>() {
//				
//				@Override
//				public void callback(String url, Bitmap object, com.androidquery.callback.AjaxStatus status) {
//					Drawable drawable = new BitmapDrawable(object);  
//					_holder.image_us_photo.setImageDrawable(drawable);
//				}
//			});
		}
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
			_issueTime = DateUtil.date2Str(new Date(Long.parseLong(_issueTime.toString())),"yyyy年MM月dd日 HH:mm:ss");
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
			_activityTime_date = DateUtil.date2Str(new Date(Long.parseLong(_activityTime.toString())),"yyyy年MM月dd日");
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
		holder.content.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int vid=v.getId();
				if(vid==_holder.content.getId())
				{
					DispatchEvent.dispatchEvent(new DEvent<Map<String,Object>>("invitationListContentClick", item));
				}
			}
		});
		if(currentNum<maxNum)
		{
//			holder.progress.setOnHoverListener(l);
			holder.progress.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					int vid=v.getId();
					if(vid==_holder.progress.getId())
					{
//					Map<String,Object> tag = (Map<String,Object>) holder.progress.getTag();
						int jo=item.get("isJoin")==null?0:Integer.parseInt(item.get("isJoin").toString());
						if(jo==1)
						{
							new AlertDialog.Builder(context).setTitle("提示").setMessage("是否取消参与？")
							.setNegativeButton("取消",new DialogInterface.OnClickListener(){
								@Override
								public void onClick(DialogInterface dialog, int which) {
									//不变
								}
							})
							.setPositiveButton("确定",new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									//保存修改
									item.put("isJoin", 0);
									item.put("currentNum", Integer.parseInt(item.get("currentNum").toString())-1);
									drawable.setCircleScale_(drawable.getCircleScale()-0.1f);
									drawable.setProgress_(drawable.getProgress()-0.1f);
									_holder.info1.setText("点击参与");
									_holder.info2.setText(item.get("currentNum")+"/"+maxNum);
								}
							}).show();
						}
						else
						{
							drawable.setCircleScale_(drawable.getCircleScale()+0.1f);
							drawable.setProgress_(drawable.getProgress()+0.1f);
							item.put("isJoin", 1);
							item.put("currentNum", Integer.parseInt(item.get("currentNum").toString())+1);
							_holder.info1.setText("已参与");
							_holder.info2.setText(item.get("currentNum")+"/"+maxNum);
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
		public ImageView image_us_photo;

		
	}
//	@Override
//	public Filter getFilter() {
//		 if (mFilter == null) {
//	            mFilter = new SimpleFilter();
//	        }
//	        return mFilter;
//	}

}
