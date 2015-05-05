package com.example.newhoyoo;

import com.androidquery.AQuery;
import com.ryg.expandable.ui.CustomActionbar;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.os.Build;

/**
 * 邀请转发页面
 * @author HJL
 *
 */
public class InventTranspond extends Activity {
	//actionbar
	public CustomActionbar actionbar;
	public Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.invent_transpond);
		intent=getIntent();
		actionbar=(CustomActionbar)findViewById(R.id.transpond_actionbar);
		AQuery aq = new AQuery(this);
		actionbar.setImageResource(R.drawable.back_image_button);
		//转发邀请按钮
		actionbar.setTitle("转发邀请");
		//发布按钮
		actionbar.setButton("发布");
		//返回时间监听
		aq.id(R.id.actionbar_left).clicked(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InventTranspond.this.finish();
			}
		});
		//发布事件监听
		aq.id(R.id.actionbar_right).clicked(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		aq.id(R.id.comment_checkbox).text("同时评论给  "+intent.getStringExtra("personName"));
		aq.id(R.id.head_imageview_01).image(intent.getStringExtra("personUrl"));
		aq.id(R.id.name).text("@"+intent.getStringExtra("personName"));
		aq.id(R.id.content).text(intent.getStringExtra("content"));
	}
}
