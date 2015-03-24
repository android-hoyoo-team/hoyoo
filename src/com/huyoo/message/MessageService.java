package com.huyoo.message;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.newhoyoo.AchievementDetail;
import com.huyoo.bean.Message;
import com.huyoo.entity.EAchievement;
import com.huyoo.utils.Utils;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;

public class MessageService {
	private static  MessageService self;
	private Application appContext ;
	public static MessageService getInstance(Application appContext)
	{
		if(self==null)
			self =new MessageService(appContext);
		return self;
	}
	private MessageService(final Application appContext)
	{
		Log.d("hjl", "MessageService init");
		DispatchEvent.addEventListener("message", new IMethod<Message>() {
			@Override
			public void excute(DEvent<Message> e) {
				Message target = e.getTarget();
				Log.d("hjl",target.getType() );
				if(target.getType().equals("achievement"))
				{
					Log.d("hjl", "achievement");
//					EAchievement achievement = com.huyoo.global.Application.getAchievementService().getEAchievementById(1);
//					Utils.getAchievementDialog(appContext,achievement).show();
//					Toast.makeText(appContext, Utils.getTopActivity(appContext).getClassName(),Toast.LENGTH_LONG).show();
					Intent intent=new Intent();
					intent.putExtra("id", 1+"");
//					intent.setComponent(Utils.getTopActivity(appContext));
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setClassName("com.example.newhoyoo", "com.example.newhoyoo.AchievementDialog");
					appContext.startActivity(intent);
				}
			}
		});
	}
}
