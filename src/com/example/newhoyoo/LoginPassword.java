package com.example.newhoyoo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;
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
import com.huyoo.global.Achievement;
import com.huyoo.global.AchievementDispatcher;
import com.huyoo.global.Application;
import com.huyoo.middle.*;

public class LoginPassword extends Activity {
	AQuery aq;
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		this.aq=new AQuery(this);
		this.aq.id(R.id.password_imageview).clicked(this, "login");
//		DispatchEvent.addEventListener("message", new IMethod<String>() {
//
//			@Override
//			public void excute(DEvent<String> event) {
//				// TODO Auto-generated method stub
//				Toast.makeText(getApplicationContext(), event.getTarget().toString(), Toast.LENGTH_LONG).show();
//			}
//		});
	}
	public void login(){
		String password = this.aq.id(R.id.password_edittext).getText().toString().trim();

		//密码正确，初始化loginInfo信息,进入主界面
		Intent intent = getIntent();
		String phoneNum = intent.getStringExtra("phoneNum");

		EPerson person = Application.getPersonService().getEPersonByPhoneNum(phoneNum);
		if(person!=null&&password.equals(person.getPassword())){
			Application.login(phoneNum);
			AchievementDispatcher achievementDispatcher = new AchievementDispatcher();
			Achievement.login();
			intent.setClass(LoginPassword.this, Main.class);
			startActivity(intent);
		}
	}
}
