package com.example.newhoyoo;


import org.json.JSONObject;


import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.huyoo.entity.EPerson;
import com.huyoo.global.Application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
/**
 * 注册页面
 * @author XF
 *
 */
public class Signup extends Activity {
	AQuery aq=new AQuery(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		this.aq.id(R.id.signaccount).clicked(this,"signaccountClick");
		Intent intent = getIntent();
		String phoneNum = intent.getStringExtra("phoneNum"); //获取从第一个界面传递来的电话号码
		this.aq.id(R.id.phoneNum_edittext).text(phoneNum);
		this.aq.id(R.id.phoneNum_edittext).textChanged(this, "checkPhoneNum");
		this.aq.id(R.id.password_edittext).textChanged(this, "checkPassword");
		this.aq.id(R.id.getverifycode).clicked(this,"checkSecurityCode");
	}
	/**
	 * @param view
	 */
	public void phonesignClick(View view){


	}

	//向注册手机发送验证码
	public void security_codesignClick(View view){
		Toast.makeText(getApplicationContext(), "验证码功能暂未开放，敬请期待", Toast.LENGTH_LONG).show();
	}

	/**
	 * 检查手机号时候被注册
	 * @return
	 */
	public boolean checkPhoneNum(){
		String phoneNum = this.aq.id(R.id.phoneNum_edittext).getText().toString().trim();
		EPerson person = Application.getPersonService().getEPersonByPhoneNum(phoneNum);
		if(person == null) return true;
		else{
			this.aq.id(R.id.testtext).text("该手机号码已被注册！");
			return false;
		}
	}
	//密码检查，不能为空 、长度大于8
	public boolean checkPassword(){
		String password = this.aq.id(R.id.password_edittext).getText().toString().trim();
		if(password==null||"".equals(password)){
			this.aq.id(R.id.testtext).text("密码不能为空!");
			return false;
		}else if(password.length()<8)
		{
			this.aq.id(R.id.testtext).text("密码不能为空!");
			return false;
		}
		if(password!=null&&password.length()>=8)return true;
		else{
			return false;
		}
	}
	/**
	 * 检查安全码
	 * @return
	 */
	public boolean checkSecurityCode(){
		return true;
	}
	/**
	 * 注册
	 * @param view
	 */
	public void signaccountClick(View view){
		//检查 手机号是否注册，检查密码，检查安全码
		if(checkPhoneNum()&&checkPassword()&&checkSecurityCode()){
			String phoneNum = this.aq.id(R.id.phoneNum_edittext).getText().toString();
			String password = this.aq.id(R.id.password_edittext).getText().toString();
			EPerson person = new EPerson();
			person.setPhoneNum(phoneNum);
			person.setPassword(password);
			long result = Application.getPersonService().addPerson(person);
			if(result!=-1){
				Application.login(phoneNum);
				Intent intent = new Intent();
				intent.setClass(Signup.this, Main.class);
				startActivity(intent);
			}else{
				Toast.makeText(getApplicationContext(), "注册未成功，请检查信息是否完整、正确！", Toast.LENGTH_LONG).show();
			}
		}

	}

	/**
	 * 登陆Callback
	 * @param url
	 * @param json
	 * @param status
	 */
	public void loginloadingCallback(String url, JSONObject json, AjaxStatus status){
		//this.aq.id(R.id.linearLayout1).visibility(View.GONE);   TEXT SUCCESS!//
		if(json == null){
			this.aq.id(R.id.textView2).text("json is null!");
		}
		else if(json.has("error")){
			this.aq.id(R.id.textView2).text("you are an old user~");
			//TODO:Login
		}
		else{
			this.aq.id(R.id.textView2).text("Welcome to hooyo~");
			//TODO:SignUp
		}
	}
}
