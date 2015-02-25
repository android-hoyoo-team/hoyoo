package com.huyoo.middle;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.AsyncCustomEndpoints;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.CloudCodeListener;
import android.content.Context;
import android.view.View;

public class HuyooUtils {
	public static void Init(Context context){
       //Bmob.initialize(context, "cea5e372e3172cf973aebcb891a9850c");
		Bmob.initialize(context, "960903a484f96238d8792cc6d48a8c53");
	}

	public static void getRegMsg(Context context,String username,CloudCodeListener listener){
		AsyncCustomEndpoints ace = new AsyncCustomEndpoints();
		JSONObject params = new JSONObject();
		try {
			params.put("username",username);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ace.callEndpoint(context,"get_regmsg", params,listener);
		
	}

}
