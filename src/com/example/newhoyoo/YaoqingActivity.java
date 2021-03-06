/*
 * Copyright (C) 2014 Saúl Díaz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.example.newhoyoo.adapter.CommentListAdapter;
import com.example.newhoyoo.adapter.ImageListAdapter;
import com.example.newhoyoo.adapter.InvitationListAdapter;
import com.example.newhoyoo.adapter.InvitationListAdapter.ViewHolder;
import com.example.newhoyoo.widget.FilterImageView;
import com.huyoo.bean.Message;
import com.huyoo.entity.EComment;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.RInvitationLike;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.HorizontalListView;

import main.java.com.sefford.circularprogressdrawable.sample.CircularProgressDrawable;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
 * 单个邀请的显示页面
 * @author HJL
 *
 */
public class YaoqingActivity extends Activity implements OnGestureListener{
	RelativeLayout bar;
	// 定义手势检测器实例
	GestureDetector detector;
	// 定义一个动画数组，用于为ViewFlipper指定切换动画效果
	Animation[] animations =new Animation[4];
	// 定义手势动作两点之间的最小距离
	final int FLIP_DISTANCE = 50;
	private final static int[] imageId = { 0, 1, 2, 3 };
	HorizontalListView horizontalList;
	ViewFlipper viewflipper;
	LinearLayout imageGroup;
	ImageListAdapter imageAdapter;
	ListView listView;
	CommentListAdapter commentListAdapter;
	Map<String, Object> item;
	float empty = 0;
	float full = 0.1f;
	float max;
	AQuery aq = new AQuery(this);
	ImageView img0, img1, img2, img3, img4, back;
	ImageButton imgB1, imgB2, imgB3, imgB4;
	Button button1, button2;
	FilterImageView button3;
	//	private EPerson person;
	private ViewHolder holder;
	// Views
	ImageView ivDrawable;
	Button btStyle2;

	CircularProgressDrawable drawable;
	int tag = 0;
	// 点赞列表
	List<RInvitationLike> likeList = null;
	// 点赞数
	int likes = 0;
	View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (tag > 0)
				return;
			drawable.setCircleScale_(drawable.getCircleScale() + 0.1f);
			drawable.setProgress_(drawable.getProgress() + 0.1f);
			tag++;
		}
	};

	/**
	 * 添加滚动图片
	 * @param i
	 * @param icon
	 * @return
	 */
	private ImageView addImageView(int i, String icon) {
		final ImageView image = new ImageView(this);
		image.setScaleType(ImageView.ScaleType.FIT_XY);
		image.setId(i);
		AQuery aquery = new AQuery(this);
		aquery.ajax(icon, Bitmap.class, new AjaxCallback<Bitmap>() {
			@Override
			public void callback(String url, Bitmap object, com.androidquery.callback.AjaxStatus status) {
				Drawable drawable = new BitmapDrawable(object);  
				image.setImageDrawable(drawable);
			}
		});
		return image;
	}

	Animator currentAnimation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yaoqing_list);
		Intent intent = getIntent();
		Integer id=(Integer) intent.getSerializableExtra("id");
		item = Application.getInvitationService().getInvitationMapById(id);

		Map<String, Object> res = InvitationListAdapter.changeItem(item);
		//邀请view的组件类
		holder = new ViewHolder();
		holder.personName = (TextView) findViewById(R.id.personName);
		holder.personLevel = (TextView) findViewById(R.id.textView6);
		holder.issueTime = (TextView) findViewById(R.id.textView3);
		holder.content = (TextView) findViewById(R.id.textView8);
		holder.activityTime_date = (TextView) findViewById(R.id.textView10);
		holder.address = (TextView) findViewById(R.id.textView14);
		holder.activityTime_time = (TextView) findViewById(R.id.textView12);
		holder.info1 = (TextView) findViewById(R.id.textView15);
		holder.info2 = (TextView) findViewById(R.id.textView16);
		holder.progress = (ImageView) findViewById(R.id.iv_drawable);
		holder.image_us_photo = (ImageView) findViewById(R.id.imageView7);
		bar=(RelativeLayout)findViewById(R.id.invitation_action_bar);
		viewflipper = (ViewFlipper) findViewById(R.id.viewflipper);
		List<String> invitationIcons = (List<String>) res.get("icons");

		if (invitationIcons != null && invitationIcons.size() > 0) {
			for (int i = 0; i < invitationIcons.size(); i++) {
				//添加滚动图片
				viewflipper.addView(addImageView(i,invitationIcons.get(i)));

			}
		}
		//添加滚动图片的点击监听事件
		DispatchEvent.addEventListener("ImageListAdapterClick", new IMethod<Integer>() {

			@Override
			public void excute(DEvent<Integer> event) {
				change(event.getTarget());
			}
		});

		//资源加载
		animations[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
		animations[1] = AnimationUtils.loadAnimation(this, R.anim.left_out);
		animations[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
		animations[3] = AnimationUtils.loadAnimation(this, R.anim.right_out);
		detector=new GestureDetector(this,this);
		horizontalList = (HorizontalListView) findViewById(R.id.image_view_list);
		int width=getResources().getDimensionPixelOffset(R.dimen.horizon_width)*invitationIcons.size()+getResources().getDimensionPixelOffset(R.dimen.horizon_margin);
		LayoutParams lp = horizontalList.getLayoutParams();
		lp.width=width;		
		imageAdapter = new ImageListAdapter(this);
		imageAdapter.setMapList(invitationIcons);
		horizontalList.setAdapter(imageAdapter);
		aq = new AQuery(this);
		String url = res.get("personUrl").toString();
		//获取用户的图片
		aq.ajax(url, Bitmap.class, new AjaxCallback<Bitmap>() {
			@Override
			public void callback(String url, Bitmap object,
					com.androidquery.callback.AjaxStatus status) {
				Drawable drawable = new BitmapDrawable(object);
				holder.image_us_photo.setImageDrawable(drawable);
			}
		});
		holder.personName.setText(res.get("personName").toString());
		holder.personLevel.setText(res.get("personLevel").toString());
		holder.address.setText(res.get("address").toString());
		holder.issueTime.setText(res.get("issueTime").toString());
		holder.content.setText(res.get("content").toString());
		holder.activityTime_date.setText(res.get("activityTime_date")
				.toString());
		holder.activityTime_time.setText(res.get("activityTime_time")
				.toString());
		int isJoin = Integer.parseInt(res.get("isJoin").toString());

		holder.info1.setText(res.get("info1").toString());
		holder.info2.setText(res.get("info2").toString());
		int currentNum = Integer.parseInt(res.get("currentNum").toString());
		final int maxNum = Integer.parseInt(res.get("maxNum").toString());
		//添加邀请点击按钮
		final CircularProgressDrawable drawable = new CircularProgressDrawable(
				getResources()
				.getDimensionPixelSize(R.dimen.drawable_ring_size),
				getResources().getColor(android.R.color.darker_gray),
				getResources().getColor(android.R.color.holo_red_light),
				getResources().getColor(android.R.color.holo_red_light));
		float p = (float) (currentNum / (maxNum + 0.0));
		drawable.setProgress(p);
		drawable.setCircleScale(p);
		holder.progress.setImageDrawable(drawable);
		if (currentNum < maxNum || isJoin == 1) {
			InvitationListAdapter.addProgressListener(holder, this,
					id);
		}

		back = (ImageView) findViewById(R.id.yaoqing_back);
		final YaoqingActivity target = this;
		//退出当前
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				target.finish();
			}
		});

		ivDrawable = (ImageView) findViewById(R.id.iv_drawable);
		btStyle2 = (Button) findViewById(R.id.bt_style_2);

		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (FilterImageView) findViewById(R.id.button3);
		//获取点赞数量
		likeList = Application.getInvitationService().getInvitationLikesByInvitationId(
				Integer.parseInt(item.get("id").toString()));
		showLikes();
		button1.setOnClickListener(new Button1Listener());
		button2.setOnClickListener(new Button2Listener());
		button3.setOnClickListener(new Button3Listener());

		listView = (ListView) findViewById(R.id.listView1);
		List<EComment> commentList = Application.getCommentService()
				.getCommentByInvitationId(id);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (EComment eComment : commentList) {
			Map<String, Object> map = new HashMap<String, Object>();
			EPerson person = Application.getPersonService().getEPersonById(
					eComment.getPersonId());
			map.put("commentId", eComment.getId());
			map.put("content", eComment.getContent());
			map.put("personUrl", person.getIcon());
			map.put("personName", person.getName());
			map.put("commentTime", eComment.getTime());
			mapList.add(map);
		}
		//回复列表Adapter
		commentListAdapter = new CommentListAdapter(this) {

			@Override
			public View getView(final int position, View convertView,
					ViewGroup parent) {
				// TODO Auto-generated method stub

				View v = super.getView(position, convertView, parent);
				AQuery vAq = new AQuery(v);
				vAq.id(R.id.comment_button).clicked(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						Map<String, Object> currentItem = (Map<String, Object>) commentListAdapter.getItem(position);
						intent.putExtra("activityId", item.get("id").toString());
						intent.putExtra("commentIdTo",
								currentItem.get("commentId").toString());
						intent.putExtra("commentNameTo",
								currentItem.get("personName").toString());
						intent.setClass(YaoqingActivity.this, Comment.class);
						YaoqingActivity.this.startActivity(intent);
					}
				});

				return v;
			}

		};
		commentListAdapter.setCommentList(mapList);
		listView.setAdapter(commentListAdapter);

	}

	
	/**
	 * 转发 监听类
	 * @author CLU
	 *
	 */
	class Button1Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.putExtra("id", item.get("id").toString());
			intent.putExtra("personName", item.get("personName").toString());
			intent.putExtra("personUrl", item.get("personUrl").toString());
			intent.putExtra("content", item.get("content").toString());
			intent.setClass(YaoqingActivity.this, InventTranspond.class);
			YaoqingActivity.this.startActivity(intent);
		}

	}

	/**
	 *回复监听类
	 */
	class Button2Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.putExtra("activityId", item.get("id").toString());
			intent.putExtra("commentIdTo", "");
			intent.setClass(YaoqingActivity.this, Comment.class);
			startActivity(intent);
		}
	}

	/**
	 *点赞监听
	 */
	class Button3Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			boolean flag = false;
			RInvitationLike removeLike = new RInvitationLike();
			// 检查用户是否已赞
			for (RInvitationLike rInvitationLike : likeList) {
				if (rInvitationLike.getPersonId() == Application.getLoginInfo()
						.getPerson().getId()) {
					flag = true;
					removeLike = rInvitationLike;
					break;
				}
			}
			if (!flag) {
				RInvitationLike rInvitationLike = new RInvitationLike();
				rInvitationLike.setPersonId(Application.getLoginInfo()
						.getPerson().getId());
				rInvitationLike.setInvitationId(Integer.parseInt(item.get("id")
						.toString()));
				likeList.add(rInvitationLike);
				showLikes();
				Toast.makeText(YaoqingActivity.this, "已点赞", Toast.LENGTH_LONG)
				.show();
			} else {
				likeList.remove(removeLike);
				showLikes();
				Toast.makeText(YaoqingActivity.this, "点赞已取消", Toast.LENGTH_LONG)
				.show();
			}
		}

	}

	public void showLikes() {
		if (likeList == null || likeList.size() == 0) {
			aq.id(R.id.button3).image(R.drawable.bt_06_nor);
			aq.id(R.id.like_num).text("");
		} else {
			aq.id(R.id.button3).image(R.drawable.bt_07_hl);
			aq.id(R.id.like_num).text("(" + likeList.size() + ")");
		}

	}
	public void firstImage() {
		img0.setImageDrawable(imgB1.getDrawable());
		img1.setVisibility(View.VISIBLE);
		img2.setVisibility(View.GONE);
		img3.setVisibility(View.GONE);
		img4.setVisibility(View.GONE);
	}

	public void secondImage() {
		img0.setImageDrawable(imgB2.getDrawable());
		img1.setVisibility(View.GONE);
		img2.setVisibility(View.VISIBLE);
		img3.setVisibility(View.GONE);
		img4.setVisibility(View.GONE);
	}

	public void threeImage() {
		img0.setImageDrawable(imgB3.getDrawable());
		img1.setVisibility(View.GONE);
		img2.setVisibility(View.GONE);
		img3.setVisibility(View.VISIBLE);
		img4.setVisibility(View.GONE);
	}

	public void fourImage() {
		img0.setImageDrawable(imgB4.getDrawable());
		img1.setVisibility(View.GONE);
		img2.setVisibility(View.GONE);
		img3.setVisibility(View.GONE);
		img4.setVisibility(View.VISIBLE);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		int barHeight=bar.getHeight();
		int height = viewflipper.getHeight();
		if(e1!=null&&e2!=null)
		{
			if(e1.getY()<barHeight+height && e2.getY()<barHeight+height && e1.getY()>barHeight &&e2.getY()>barHeight){
				if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
					change(-1);
					return true;
				} else if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
					change(-2);
					return true;
				}
			}
		}
		return false;
	}
	private void change(int i)
	{
		int index=viewflipper.getDisplayedChild();
		View childAt = horizontalList.getChildAt(index);
		childAt.findViewById(R.id.image_select).setVisibility(View.INVISIBLE);
		if(i<0)
		{
			if(i==-1)
			{
				viewflipper.setInAnimation(animations[0]);
				viewflipper.setOutAnimation(animations[1]);
				viewflipper.showNext();
			}
			else
			{
				viewflipper.setInAnimation(animations[2]);
				viewflipper.setOutAnimation(animations[3]);
				viewflipper.showPrevious();
			}
		}
		else 
		{
			int t=i-index;
			if(t>0)
			{
				for(int n=0;n<t;n++)
				{
					viewflipper.setInAnimation(animations[0]);
					viewflipper.setOutAnimation(animations[1]);
					viewflipper.showNext();
				}
			}
			else if(t<0)
			{
				for(int n=0;n<0-t;n++)
				{
					viewflipper.setInAnimation(animations[2]);
					viewflipper.setOutAnimation(animations[3]);
					viewflipper.showPrevious();
				}
			}
		}
		int index1=viewflipper.getDisplayedChild();
		View childAt1 = horizontalList.getChildAt(index1);
		childAt1.findViewById(R.id.image_select).setVisibility(View.VISIBLE);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}
}
