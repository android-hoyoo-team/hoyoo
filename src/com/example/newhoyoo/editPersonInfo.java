package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.HashMap;

import com.androidquery.AQuery;

import main.java.com.sefford.circularprogressdrawable.sample.CircularProgressDrawable;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Circular progress drawable demonstration
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class editPersonInfo extends Activity {
	AQuery aq ;

	Button btStyle2;

	Animator currentAnimation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		aq = new AQuery(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_personinfo);
		
		this.aq.id(R.id.imageButton_1).clicked(this, "back");
		this.aq.id(R.id.button1).clicked(this, "edit");
		this.aq.id(R.id.imageView2).clicked(this, "changemypic");


	}

	public void edit()
	{
		this.aq.id(R.id.button1).background(R.drawable.buttoneffect_red);
		new AlertDialog.Builder(this).setTitle("提示").setMessage("您确定保存更改吗？")
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
				
			}
		}).show();
	}
	public void back()
	{
		this.aq.id(R.id.imageButton_1).background(R.drawable.bt_02_press);
		this.finish();
	}
	public void changemypic()
	{
		this.aq.id(R.id.imageView2).image(R.drawable.bt_17_press_01);
		//跳转更换头像界面
	}
}

