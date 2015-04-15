package com.example.newhoyoo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;
import com.ryg.expandable.ui.CustomActionbar;

/**
 * 创建公会中转页面,用以显示是否满足创建公会的条件.
 * @author XF
 *
 */
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
		if(verifyCondition())//判断是否符合创建公会的条件,如果符合,显示"您已经达到了所有创建公会所必须的条件",并使创建公会的按钮可以点击;否则显示原因，并使按钮不能点击.
		{
			this.aq.id(R.id.union_condition_textview).text("您已经达到了所有创建公会所必须的条件");
			this.aq.id(R.id.create_union_button).checked(true);
			this.aq.id(R.id.create_union_button).clicked(this, "createUnion");
		}else{
			this.aq.id(R.id.union_condition_textview).text("您未达到创建公会的条件");//这里按需求填写原因.
			this.aq.id(R.id.create_union_button).checked(false);
		}
		this.aq.id(R.id.actionbar_left).clicked(this,"back");
	}
	/**
	 * 判断是否符合创建公会的条件,如果符合,显示"您已经达到了所有创建公会所必须的条件",并使创建公会的按钮可以点击;否则显示原因，并使按钮不能点击.
	 * <br>后期添加其他条件
	 * @return
	 */
	public boolean verifyCondition(){
		return true;
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
