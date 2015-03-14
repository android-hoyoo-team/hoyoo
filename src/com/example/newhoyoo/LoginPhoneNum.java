package com.example.newhoyoo;
/*import com.huyoo.middle.*;*/

import org.json.JSONObject;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.SaveListener;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.huyoo.middle.*;

public class LoginPhoneNum extends Activity {
	AQuery aq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		aq=new AQuery(this);
		setContentView(R.layout.activity_main);
		HuyooUtils.Init(this);//Bmob初始化
		this.aq.id(R.id.send_phonenum_imageview).clicked(this,"sendPhoneNum");
	}
	public void sendPhoneNum(View view){
		Intent intent = new Intent();
		String phoneNum = this.aq.id(R.id.phonenum_edittext).getText().toString().trim();
		intent.putExtra("phoneNum",phoneNum);
		if("10000".equals(phoneNum)||"10001".equals(phoneNum)
				||"10002".equals(phoneNum)||"10004".equals(phoneNum)){
			//老用户，进入密码输入界面
			intent.setClass(this,LoginPassword.class);
		}
		else{
			intent.setClass(this,Signup.class);
		}
		startActivity(intent);





		//		HuyooUser user = new HuyooUser();
		//		final String username = this.aq.id(R.id.phonenum).getText().toString();
		//		user.setUsername(username);
		//		user.count(this, new CountListener() {
		//			@Override
		//			public void onSuccess(int count) {
		//				if(count == 0){	//不存在
		//					Toast.makeText(getApplicationContext(), "新用户,来注册吧",Toast.LENGTH_SHORT).show();
		//					Intent myIntent = new Intent(MainActivity.this, Signup.class);
		//					myIntent.putExtra("username",username); 
		//					MainActivity.this.startActivity(myIntent);
		//				}else{	//存在
		//					Toast.makeText(getApplicationContext(), "老用户,快登陆",Toast.LENGTH_SHORT).show();
		//
		//					Intent myIntent = new Intent(MainActivity.this, Login.class);
		//					myIntent.putExtra("username",username); 
		//					MainActivity.this.startActivity(myIntent);
		//				}
		//			}
		//			@Override
		//			public void onFailure(int code, String msg) {
		//				Toast.makeText(getApplicationContext(), "failed "+msg,Toast.LENGTH_LONG).show();
		//			}
		//		});
	}
	//    
	//    public void loginCallback(String url, JSONObject json, AjaxStatus sstatus){
	//    	if(json == null){
	//			this.aq.id(R.id.textView2).text("json is null!");
	//		}
	//    	else if(json.has("error")){
	//    		this.aq.id(R.id.textView2).text("you are an old user~");
	//			//TODO:Login
	//		}
	//    	else{
	//    		this.aq.id(R.id.textView2).text("Welcome to hooyo~");
	//			//TODO:SignUp
	//		}
	//    }
	//    
	//    private void skip_login()//返回主界面
	//    {
	//    	startActivity(new Intent(this,Login.class));  
	//        finish();  
	//    }
	//    private void skip_signup()//返回主界面
	//    {
	//    	startActivity(new Intent(this,Signup.class));  
	//        finish();  
	//}

}
