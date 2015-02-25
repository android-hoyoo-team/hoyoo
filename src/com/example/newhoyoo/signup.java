package com.example.newhoyoo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;

import cn.bmob.v3.listener.SaveListener;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.huyoo.middle.HuyooUser;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends Activity {
	AQuery aq=new AQuery(this);
	private loginloadingfragment loginloadingfragment;
	private FragmentManager fragmentManager;
	private static String security_code=null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.signup);
       this.aq.id(R.id.phonesign).clicked(this,"phonesignClick");
        this.aq.id(R.id.signaccount).clicked(this,"signaccountClick");
        Intent intent = getIntent();
        String username = intent.getStringExtra("username"); //获取从第一个界面传递来的电话号码
        this.aq.id(R.id.phonenumsign).text(username);
        EditText et=(EditText)findViewById(R.id.passwordsign_text);
        if(et.hasFocus()){//检测密码的格式，必须是8位以上的。
        	this.aq.id(R.id.testtext).text("getfocus!");
        	//this.aq.id(R.id.passwordsign_text).textChanged(this, "checkformat");
        }else{
        	this.aq.id(R.id.testtext).text("lostfocus!");
        }
      
        this.aq.id(R.id.security_codesign).clicked(this,"security_codesignClick");
        
    }
    /**
     * 再次判断是否为老用户,请求服务器http://192.168.0.112:4000/？
     * @param view
     */
    public void phonesignClick(View view){
    	//Toast.makeText(getApplicationContext(), "1111111", Toast.LENGTH_LONG).show();
        this.aq.id(R.id.sendpwd).background(R.color.buttonEffect);
       // String pwd = this.aq.id(R.id.password_text).getText().toString();
        //String url = myApp.web("api/user?username=" + pwd);
        String username=this.aq.id(R.id.phonenumsign).getText().toString();
        String url = myApp.web("api/user?username=" + username);
        aq.ajax(url,JSONObject.class, this, "loginloadingCallback");
    }
    
    //向注册手机发送验证码
    public void security_codesignClick(View view){
    	String phonenum = this.aq.id(R.id.phonenumsign).getText().toString();
    	SmsManager smsManager = SmsManager.getDefault();
    	Random random = new Random();
    	security_code = random.nextInt(10)+""+random.nextInt(10)+""+random.nextInt(10)+""+random.nextInt(10)
    			+""+random.nextInt(10)+""+random.nextInt(10);
    	smsManager.sendTextMessage(phonenum, null, "您好，您正在注册NewHuyoo的验证码是:"+security_code+".", null, null);
    }
    public void signaccountClick(View view){
    	String username = this.aq.id(R.id.phonenumsign).getText().toString();
    	String password = this.aq.id(R.id.passwordsign_text).getText().toString();
    	String securitycode = this.aq.id(R.id.security_code).getText().toString();
    	if(securitycode==null||"".equals(securitycode)||!securitycode.equals(security_code))
    	{
    		Toast.makeText(getApplicationContext(), "验证码输入不正确！", Toast.LENGTH_LONG).show();
    		return;
    	}
    	HuyooUser user = new HuyooUser();
    	user.setUsername(username);
    	user.setPassword(password);
    	user.signUp(this, new SaveListener() {
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
				Intent intent = getIntent();
		    	intent.setClass(signup.this, Main.class);
		    	signup.this.startActivity(intent);
		    	security_code=null;
			}
			
			@Override
			public void onFailure(int arg0, String msg) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
				security_code=null;
			}
		});
    	
      /*  this.aq.id(R.id.sendpwd).background(R.color.buttonEffect);
        this.aq.id(R.id.linearLayout1).visibility(View.GONE);
        
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
		if (loginloadingfragment == null) {
			loginloadingfragment = new loginloadingfragment();
			transaction.add(R.id.content, loginloadingfragment);
		} else {
			transaction.show(loginloadingfragment);
		}
		transaction.commit();
				
        this.aq.id(R.id.testtext).text("I can work!");
        String pwd = this.aq.id(R.id.password_text).getText().toString();
        String url = myApp.web("api/user?username=" + pwd);
        aq.ajax(url,JSONObject.class, this, "loginloadingCallback");*/
    }
    
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
