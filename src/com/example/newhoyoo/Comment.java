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
import android.widget.EditText;
import android.os.Build;

public class Comment extends Activity {
	public CustomActionbar actionbar;
	public EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_comment);
		Intent intent = getIntent();
		et=(EditText) findViewById(R.id.pinglun_content);
		actionbar = (CustomActionbar) findViewById(R.id.comment_actionbar);
		actionbar.setImageResource(R.drawable.back_image_button);
		actionbar.setTitle("发评论");
		actionbar.setButton("发布");
		AQuery aq = new AQuery(this);
		aq.id(R.id.actionbar_left).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Comment.this.finish();
			}
		});
		aq.id(R.id.actionbar_right).clicked(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		// aq.id(R.id.pinglun_back).clicked(this,"back");
		// aq.id(R.id.fabu_button).clicked(this, "fabu");
		if (!intent.getStringExtra("commentIdTo").equals("")) {
			et.setText("回复 "+intent.getStringExtra("commentNameTo")+" : ");
			et.setSelection(et.length());
		}
	}

	// public void back(){
	// this.finish();
	// }
	// //发布评论
	// public void fabu(){
	//
	// }
}
