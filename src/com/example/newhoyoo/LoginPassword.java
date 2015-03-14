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

import com.huyoo.entity.EPerson;
import com.huyoo.global.Application;
import com.huyoo.middle.*;

public class LoginPassword extends Activity {
	AQuery aq;
	private Loginloadingfragment loginloadingfragment;
	private FragmentManager fragmentManager;
	private String username;	//用来保存电话号码
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		this.aq=new AQuery(this);
		this.aq.id(R.id.password_imageview).clicked(this, "login");
	}
	public void login(){
		String password = this.aq.id(R.id.password_edittext).getText().toString().trim();
		
		if("1".equals(password)){
			//密码正确，初始化loginInfo信息,进入主界面
			Intent intent = getIntent();
			String phoneNum = intent.getStringExtra("phoneNum");
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("phoneNum", phoneNum);
			params.put("password", password);
			Application.login(phoneNum);
			intent.setClass(LoginPassword.this, Main.class);
			startActivity(intent);
			//fragmentManager = getFragmentManager();
		}
	}

	//		Intent intent = getIntent();
	//		this.username = intent.getStringExtra("username"); //获取从第一个界面传递来的电话号码
	//
	//		intent.putExtra("username", this.username);
	//		//this.aq.id(R.id.sendphonenum)
	//		this.aq.id(R.id.sendpwd).clicked(this,"sendpwdClick");
	//	}
	//	public void sendpwdClick(View view){
	////		this.aq.id(R.id.sendpwd).background(R.color.buttonEffect);
	////		this.aq.id(R.id.linearLayout1).visibility(View.GONE);
	////
	////		fragmentManager = getFragmentManager();
	////		FragmentTransaction transaction = fragmentManager.beginTransaction();
	////		if (loginloadingfragment == null) {
	////			loginloadingfragment = new loginloadingfragment();
	////			transaction.add(R.id.content, loginloadingfragment);
	////		} else {
	////			transaction.show(loginloadingfragment);
	////		}
	////		transaction.commit();
	////
	////		this.aq.id(R.id.testtext).text("I can work!");
	//		//Toast.makeText(getApplicationContext(), this.username, Toast.LENGTH_SHORT).show();
	//		String pwd = this.aq.id(R.id.password_text).getText().toString();
	//		HuyooUser user=new HuyooUser();
	//		user.setUsername(this.username);
	//		user.setPassword(pwd);
	//		user.login(this, new SaveListener() {
	//			@Override
	//			public void onSuccess() {
	//				Toast.makeText(getApplicationContext(), "登陆成功",Toast.LENGTH_LONG).show();
	//				Intent intent = getIntent();
	//				intent.setClass(Login.this, Main.class);
	//				Login.this.startActivity(intent);
	//			}
	//			@Override
	//			public void onFailure(int code, String msg) {
	//				Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();
	//			}
	//		});



}
