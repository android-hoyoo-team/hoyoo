package com.example.newhoyoo;


import java.util.List;

import com.androidquery.AQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.huyoo.entity.EPerson;
import com.huyoo.global.Achievement;
import com.huyoo.global.AchievementDispatcher;
import com.huyoo.global.Application;

public class LoginPassword extends Activity {
	AQuery aq;
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		this.aq=new AQuery(this);
		this.aq.id(R.id.password_imageview).clicked(this, "login");
	}
	public void login(){
		String password = this.aq.id(R.id.password_edittext).getText().toString().trim();
		//密码正确，初始化loginInfo信息,进入主界面
		Intent intent = getIntent();
		String phoneNum = intent.getStringExtra("phoneNum");
		EPerson person = Application.getPersonService().getEPersonByPhoneNum(phoneNum);
		if(person!=null&&password.equals(person.getPassword())){
			Application.login(phoneNum);
			Achievement.login();
			List<EPerson> friends = Application.getPersonService().getFriends(Application.getLoginInfo().getPerson().getId());
			if(friends!=null&&friends.size()>0)Achievement.haveFriend();
			intent.setClass(LoginPassword.this, Main.class);
			startActivity(intent);
		}
	}
}
