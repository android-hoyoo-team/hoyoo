package com.example.newhoyoo;


import com.androidquery.AQuery;
import com.ryg.expandable.ui.CustomActionbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
/**
 * 公会创建完成后,显示审批倒计时页面.先模拟效果
 * @author XF
 *
 */
public class FinishCreateUnion extends Activity {
	AQuery aq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish_create_union);
		aq = new AQuery(this);
		//actionbar
		CustomActionbar actionbar = (CustomActionbar)findViewById(R.id.create_union_actionbar);
		actionbar.setTitle("创建工会");
		actionbar.setButtonVisibility(View.GONE);
		this.aq.id(R.id.actionbar_left).clicked(this, "back");
		long total = 24*60*60*1000;
		//24小时倒计时
		new CountDownTimer(total,1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				long left = millisUntilFinished/1000;
				long second = left%60;
				long minute = (left/60)%60;
				long hour = left/3600;
				aq.id(R.id.apply_timer).text( String.format("%02d:%02d:%02d", hour,minute,second));
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				aq.id(R.id.apply_timer).text("时间到了！");
			}
		}.start();
	}
	//返回上一层
	public void back(){
		this.finish();
	}
}
