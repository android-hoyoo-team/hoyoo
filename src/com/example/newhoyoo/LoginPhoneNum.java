package com.example.newhoyoo;
/*import com.huyoo.middle.*;*/




import com.androidquery.AQuery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.huyoo.entity.EPerson;
import com.huyoo.global.Application;
import com.huyoo.global.DatabaseHelper;
/**
 * 登陆页面,输入电话号码,如果是老用户,进入输入密码界面;否则,进入注册界面.
 * @author XF
 *
 */
public class LoginPhoneNum extends Activity {
	AQuery aq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		aq=new AQuery(this);
		setContentView(R.layout.activity_main);
		//HuyooUtils.Init(this);//Bmob初始化
		//点击事件
		this.aq.id(R.id.send_phonenum_imageview).clicked(this,"sendPhoneNum");
	}
	//
	public void sendPhoneNum(View view){
		Intent intent = new Intent();
		String phoneNum = this.aq.id(R.id.phonenum_edittext).getText().toString().trim();
		intent.putExtra("phoneNum",phoneNum);
		//getApplicationContext().deleteDatabase("hoyoo");
		//*************************************************************************//
		//用于初期版本中本地数据的初始化，后期换成服务器后 将该句代码注释
		Application.setDatabaseHelper(new DatabaseHelper(getApplicationContext(), "hoyoo"));
		//*************************************************************************//
		
		//根据用户手机号获取
		EPerson person = Application.getPersonService().getEPersonByPhoneNum(phoneNum);
		if(person!=null){
			intent.setClass(this,LoginPassword.class);
		}else{
			intent.setClass(this,Signup.class);
		}
		startActivity(intent);
	}
}
