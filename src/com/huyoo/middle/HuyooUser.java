package com.huyoo.middle;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.content.Context;
import cn.bmob.v3.AsyncCustomEndpoints;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.CloudCodeListener;
import cn.bmob.v3.listener.CountListener;

public class HuyooUser extends BmobUser{

	public enum Sex{
		BOY,GIRL
	}
	private Sex sex;
	private string nick;
	
	public Sex getSex(){
		return this.sex;
	}
	
	public void setSex(Sex sex){
		this.sex = sex;
	}
	
	public string getNick(){
		return this.nick;
	}

	public void setNick(string nick){
		this.nick = nick;
	}
	
	public void count(Context context,CountListener listener){
		BmobQuery<HuyooUser> query = new BmobQuery<HuyooUser>();
		query.addWhereEqualTo("username",this.getUsername());
		query.count(context, HuyooUser.class,listener);
	}
	
	public void signupWithMsg(Context context,String msg,CloudCodeListener listener){
		AsyncCustomEndpoints ace = new AsyncCustomEndpoints();
		JSONObject params = new JSONObject();
		try {
			params.put("username",this.getUsername());
			params.put("password",this.getPassword());
			params.put("regmsg", msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ace.callEndpoint(context,"signup_msg", params,listener);
		
	}
}
