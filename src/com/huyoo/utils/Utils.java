package com.huyoo.utils;

import java.util.List;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;
import com.huyoo.entity.EAchievement;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class Utils {
	public static ComponentName getTopActivity(Context context)
	{
		//<uses-permission android:name = "android.permission.GET_TASKS"/>
		ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE) ;
		List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1) ;
		
		if(runningTaskInfos != null)
			return (runningTaskInfos.get(0).topActivity);
		else
			return null ;
	}

}
