package com.example.newhoyoo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;
import com.ryg.expandable.ui.CustomActionbar;

public class CreateUnionCondition extends Activity {
	AQuery aq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_union_condition);
		this.aq = new AQuery(this);
		CustomActionbar actionbar = (CustomActionbar)findViewById(R.id.create_union_condition_actionbar);
		actionbar.setImageResource(R.drawable.bt_15_01_selector);
		actionbar.setTitle("创建工会");
		actionbar.setButtonVisibility(View.GONE);
		this.aq.id(R.id.actionbar_left).clicked(this,"back");
		this.aq.id(R.id.create_union_button).clicked(this, "createUnion");
	}
	
	public void back(){
		this.finish();
	}
	public void createUnion(){
		Intent intent = getIntent();
		intent.setClass(this, CreateUnion.class);
		startActivity(intent);
	}
}
