package com.example.newhoyoo.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;
import main.java.com.sefford.circularprogressdrawable.sample.CircularProgressDrawable;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.example.newhoyoo.R;
import com.example.newhoyoo.YaoqingActivity;
import com.example.newhoyoo.widget.FilterImageView;
import com.huyoo.bean.Result;
import com.huyoo.entity.EInvitation;
import com.huyoo.global.Application;
import com.huyoo.utils.DateUtil;
import com.huyoo.utils.GsonUtil;

public class InvitationListAdapter extends BaseAdapter{
	public final static String HAS_JOIN="已参加";
	public final static String HAS_FULL="人数已满";
	public final static String TO_JOIN="点击参与";
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
		Map<String,Object> res=changeItem(item);
		String url = res.get("personUrl").toString();
		//			aq.id(R.id.image_us_photo).image(url);
		aq.ajax(url, Bitmap.class, new AjaxCallback<Bitmap>() {
			@Override
			public void callback(String url, Bitmap object, com.androidquery.callback.AjaxStatus status) {
				Drawable drawable = new BitmapDrawable(object);  
				_holder.image_us_photo.setImageDrawable(drawable);
			}
		});
		holder.personName.setText(res.get("personName").toString());
		holder.personLevel.setText(res.get("personLevel").toString());
		holder.address.setText(res.get("address").toString());
		holder.issueTime.setText(res.get("issueTime").toString());
		holder.content.setText(res.get("content").toString());
		holder.activityTime_date.setText(res.get("activityTime_date").toString());
		holder.activityTime_time.setText(res.get("activityTime_time").toString());
		int isJoin = Integer.parseInt(res.get("isJoin").toString());

		holder.info1.setText(res.get("info1").toString());
		holder.info2.setText(res.get("info2").toString());
		int currentNum =Integer.parseInt(res.get("currentNum").toString());
		int maxNum =Integer.parseInt(res.get("maxNum").toString());

		final CircularProgressDrawable  drawable = new CircularProgressDrawable(convertView.getResources().getDimensionPixelSize(R.dimen.drawable_ring_size),
				convertView.getResources().getColor(android.R.color.darker_gray),
				convertView.getResources().getColor(android.R.color.holo_red_light),
				convertView.getResources().getColor(android.R.color.holo_red_light));
		float p=(float) (currentNum/(maxNum+0.0));
		drawable.setProgress(p);
		drawable.setCircleScale(p);
		holder.progress.setImageDrawable(drawable);
		IMethod<Integer> method = new IMethod<Integer>() {
			
			public void excute(DEvent<Integer> event) {
				 Integer id = event.getTarget();
				 Map<String, Object> map0 = Application.getInvitationService().getInvitationMapById(id);
				 Map<String,Object> map=changeItem(map0);
				 int currentNum =Integer.parseInt(map.get("currentNum").toString());
				 int maxNum =Integer.parseInt(map.get("maxNum").toString());
				 drawable.setCircleScale_((float) (currentNum/(maxNum+0.0)));
				 drawable.setProgress_((float) (currentNum/(maxNum+0.0)));
				 _holder.info1.setText(map.get("info1").toString());
				 _holder.info2.setText(map.get("info2").toString());
			}
		};
		DispatchEvent.addEventListener("join_invitation_event"+item.get("id"), method);
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DispatchEvent.dispatchEvent(new DEvent<Integer>("invitationListItemClick", Integer.parseInt(item.get("id").toString())));
			}
		});
		addProgressListener(holder,context,Integer.parseInt(item.get("id").toString()));
		/**为Button添加点击事件*/             
		return convertView;
	}
	public final static class ViewHolder{
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
	public static void addProgressListener(final ViewHolder _holder,final Context context,final int invitationId)
	{
		
		_holder.progress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final CircularProgressDrawable drawable = (CircularProgressDrawable) _holder.progress.getDrawable();
				int vid=v.getId();
				if(vid==_holder.progress.getId())
				{
					 final Map<String, Object> invitationMapById = Application.getInvitationService().getInvitationMapById(invitationId);
//				Map<String,Object> tag = (Map<String,Object>) holder.progress.getTag();
					int jo=invitationMapById.get("isJoin")==null?0:Integer.parseInt(invitationMapById.get("isJoin").toString());
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
								Result<Map<String,Object>> joinInvitationMapById = Application.getInvitationService().joinInvitationById(Integer.parseInt(invitationMapById.get("id").toString()),false);
								Map<String, Object> map = joinInvitationMapById.getResult();
								if(joinInvitationMapById.getStatus().equals("success"))
								{
									int currentNum =Integer.parseInt(map.get("currentNum").toString());
									int maxNum =Integer.parseInt(map.get("maxNum").toString());
									drawable.setCircleScale_((float) (currentNum/(maxNum+0.0)));
									drawable.setProgress_((float) (currentNum/(maxNum+0.0)));
									_holder.info1.setText(TO_JOIN);
									_holder.info2.setText(currentNum+"/"+maxNum);
									//添加改变事件
									DispatchEvent.dispatchEvent(new DEvent<Integer>("join_invitation_event"+map.get("id"), Integer.parseInt(map.get("id").toString())));
									
								}
								else
								{
									Toast.makeText(context, joinInvitationMapById.getMessage(), Toast.LENGTH_LONG).show();
								}
								//保存修改
							}
						}).show();
					}
					else
					{
						Result<Map<String, Object>> joinInvitationById = Application.getInvitationService().joinInvitationById(invitationId,true);
						Map<String, Object> map = joinInvitationById.getResult();
						if(joinInvitationById.getStatus().equals("success"))
						{
							int currentNum =Integer.parseInt(map.get("currentNum").toString());
							int maxNum =Integer.parseInt(map.get("maxNum").toString());
							drawable.setCircleScale_((float) (currentNum/(maxNum+0.0)));
							drawable.setProgress_((float) (currentNum/(maxNum+0.0)));
							_holder.info1.setText(HAS_JOIN);
							_holder.info2.setText(currentNum+"/"+maxNum);
							
							DispatchEvent.dispatchEvent(new DEvent<Integer>("join_invitation_event"+map.get("id"), Integer.parseInt(map.get("id").toString())));
						}
						else
						{
							Toast.makeText(context, joinInvitationById.getMessage(), Toast.LENGTH_LONG).show();
						}
					}
				}
				
			}
		});
	}
	public static void setHolder(Map<String,Object> res)
	{
		
	}
	public static Map<String,Object> changeItem(Map<String,Object> item)
	{
		Map<String,Object> res=new HashMap<String,Object>();
		 Object personUrl = item.get("personUrl");
		 if(personUrl==null)
		 {
			 personUrl="http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/D7239B770A164485B56175BF5698D2AB";
		 }
		res.put("personUrl", personUrl.toString());
		res.put("personName", item.get("personName")==null?"":item.get("personName").toString());
		res.put("personLevel", item.get("personLevel")==null?"":item.get("personLevel").toString());
		res.put("address", item.get("address")==null?"":item.get("address").toString());
		Object  _issueTime = item.get("issueTime");
		if(_issueTime==null)
		{
			_issueTime="";
		}
		else
		{
			_issueTime = DateUtil.date2Str(new Date(Long.parseLong(_issueTime.toString())),"yyyy年MM月dd日 HH:mm:ss");
		}
		res.put("issueTime",_issueTime.toString());
		res.put("content",item.get("content")==null?"":item.get("content").toString() );
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
		res.put("activityTime_date", _activityTime_date);
		res.put("activityTime_time", _activityTime_time);
		int isJoin = item.get("isJoin")==null?0:Integer.parseInt(item.get("isJoin").toString());
		res.put("isJoin", isJoin);

		int currentNum=item.get("currentNum")==null?0:Integer.parseInt(item.get("currentNum").toString());
		int maxNum=item.get("maxNum")==null?0:Integer.parseInt(item.get("maxNum").toString());
		if(isJoin==1)
		{
			res.put("info1", HAS_JOIN);
		}
		else if(currentNum>=maxNum)
		{
			res.put("info1", HAS_FULL);
		}
		else
		{
			res.put("info1", TO_JOIN);
		}
		res.put("info2", currentNum+"/"+maxNum);
		res.put("currentNum",currentNum );
		res.put("maxNum", maxNum);
		Object icons = item.get("icons");
		List<String> ls=null;
		if(icons!=null)
		{
			ls = (List<String>) GsonUtil.jsonToList(icons.toString());
		}
		if(ls==null)
			ls=new ArrayList<String>();
		res.put("icons", ls);
		return res;
	}
}
