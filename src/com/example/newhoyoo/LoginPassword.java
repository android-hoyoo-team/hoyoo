package com.example.newhoyoo;


import java.util.List;

import com.androidquery.AQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.huyoo.entity.EPerson;
import com.huyoo.global.Achievement;
import com.huyoo.global.AchievementDispatcher;
import com.huyoo.global.Application;
/**
 * 密码输入界面,输入正确进入主界面.
 * @author XF
 *
 */
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
		//根据用户手机号获取用户信息
		EPerson person = Application.getPersonService().getEPersonByPhoneNum(phoneNum);
		if(person!=null&&password.equals(person.getPassword())){
			//根据用户号码登陆，如果用户已经登陆则返回已登录信息，如果用户没有登陆则执行登陆操作后返回登陆信息
			Application.login(phoneNum);
			//注册登录事件，当登录的时候，会触发相应的监听。
			Achievement.login();
			//通过id获取其好友列表，满足好友的条件是，既被他关注，也关注了他的。
			List<EPerson> friends = Application.getPersonService().getFriends(Application.getLoginInfo().getPerson().getId());
			if(friends!=null&&friends.size()>0)Achievement.haveFriend();
			intent.setClass(LoginPassword.this, Main.class);
			startActivity(intent);
		}
		else{
			Toast.makeText(getApplicationContext(), "密码输入错误，请重新输入", Toast.LENGTH_LONG).show();
		}
	}
}
