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

public class LoginPhoneNum extends Activity {
	AQuery aq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		aq=new AQuery(this);
		setContentView(R.layout.activity_main);
		//HuyooUtils.Init(this);//Bmob初始化
		this.aq.id(R.id.send_phonenum_imageview).clicked(this,"sendPhoneNum");
	}
	public void sendPhoneNum(View view){
		Intent intent = new Intent();
		String phoneNum = this.aq.id(R.id.phonenum_edittext).getText().toString().trim();
		intent.putExtra("phoneNum",phoneNum);
		getApplicationContext().deleteDatabase("hoyoo");
		Application.setDatabaseHelper(new DatabaseHelper(getApplicationContext(), "hoyoo"));
		EPerson person = Application.getPersonService().getEPersonByPhoneNum(phoneNum);
		if(person!=null){
			intent.setClass(this,LoginPassword.class);
		}else{
			intent.setClass(this,Signup.class);
		}
		startActivity(intent);
	}
}
