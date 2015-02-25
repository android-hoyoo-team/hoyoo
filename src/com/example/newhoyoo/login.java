package com.example.newhoyoo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import cn.bmob.v3.listener.SaveListener;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.huyoo.middle.*;

public class login extends Activity {
	AQuery aq;
	private loginloadingfragment loginloadingfragment;
	private FragmentManager fragmentManager;
	private String username;	//用来保存电话号码
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		aq=new AQuery(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		Intent intent = getIntent();
		this.username = intent.getStringExtra("username"); //获取从第一个界面传递来的电话号码

		intent.putExtra("username", this.username);
		//this.aq.id(R.id.sendphonenum)
		this.aq.id(R.id.sendpwd).clicked(this,"sendpwdClick");
	}
	public void sendpwdClick(View view){
//		this.aq.id(R.id.sendpwd).background(R.color.buttonEffect);
//		this.aq.id(R.id.linearLayout1).visibility(View.GONE);
//
//		fragmentManager = getFragmentManager();
//		FragmentTransaction transaction = fragmentManager.beginTransaction();
//		if (loginloadingfragment == null) {
//			loginloadingfragment = new loginloadingfragment();
//			transaction.add(R.id.content, loginloadingfragment);
//		} else {
//			transaction.show(loginloadingfragment);
//		}
//		transaction.commit();
//
//		this.aq.id(R.id.testtext).text("I can work!");
		//Toast.makeText(getApplicationContext(), this.username, Toast.LENGTH_SHORT).show();
		String pwd = this.aq.id(R.id.password_text).getText().toString();
		HuyooUser user=new HuyooUser();
		user.setUsername(this.username);
		user.setPassword(pwd);
		user.login(this, new SaveListener() {
			@Override
			public void onSuccess() {
				Toast.makeText(getApplicationContext(), "登陆成功",Toast.LENGTH_LONG).show();
				Intent intent = getIntent();
				intent.setClass(login.this, Main.class);
				login.this.startActivity(intent);
			}
			@Override
			public void onFailure(int code, String msg) {
				Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
			}
		});
		
	}

}
