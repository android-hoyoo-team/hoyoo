package com.huyoo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huyoo.entity.RPersonAchievement;
import com.huyoo.global.Application;
import com.huyoo.global.DatabaseHelper;

public class RPersonAchievementService {
	
	public List<RPersonAchievement> getPersonAchievements(Map<String,Object> params){
		List<RPersonAchievement> rpas = new ArrayList<RPersonAchievement>();
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer sb = new StringBuffer();
		List<String> selectionArgs = new ArrayList<String>();
		String sql = "select * from RPersonAchievement where 1=1";
		if(params!=null){
			if(params.get("id")!=null){
				sb.append(" and id=?");
				selectionArgs.add(params.get("id").toString());
			}
			if(params.get("personId")!=null){
				sb.append(" and personId=?");
				selectionArgs.add(params.get("personId").toString());
			}
			if(params.get("achievementId")!=null){
				sb.append(" and achievementId=?");
				selectionArgs.add(params.get("achievementId").toString());
			}
		}
		sql += sb.toString();
		String[] args = new String[selectionArgs.size()];
		Cursor cursor = db.rawQuery(sql,selectionArgs.toArray(args));
		while(cursor.moveToNext()){
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			int personId = cursor.getInt(cursor.getColumnIndex("personId"));
			int achievementId = cursor.getInt(cursor.getColumnIndex("achievementId"));
			long time = cursor.getLong(cursor.getColumnIndex("time"));
			int currentProgress = cursor.getInt(cursor.getColumnIndex("currentProgress"));
			RPersonAchievement rpa = new RPersonAchievement();
			rpa.setId(id);
			rpa.setPersonId(personId);
			rpa.setAchievementId(achievementId);
			rpa.setTime(time);
			rpa.setCurrentProgress(currentProgress);
			rpas.add(rpa);
		}
		return rpas;
	}
	
	public List<RPersonAchievement> getPersonAchievementByPersonId(int personId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("personId", personId);
		return getPersonAchievements(params);
	}
	
	public RPersonAchievement getPersonAchievementBy(int personId,int achievementId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("personId", personId);
		params.put("achievementId", achievementId);
		List<RPersonAchievement> rpas = getPersonAchievements(params);
		if(rpas!=null&&rpas.size()>0)return rpas.get(0);
		return null;
	}
	
	public long addPersonAchievement(RPersonAchievement rpa){
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("personId", rpa.getPersonId());
		cv.put("achievementId", rpa.getAchievementId());
		cv.put("currentProgress", rpa.getCurrentProgress());
		cv.put("time", rpa.getTime());
		return db.insert("RPersonAchievement", null, cv);
	}
	public long updatePersonAchievement(RPersonAchievement rpa){
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("personId", rpa.getPersonId());
		cv.put("achievementId", rpa.getAchievementId());
		cv.put("currentProgress", rpa.getCurrentProgress());
		cv.put("time", rpa.getTime());
		return db.update("RPersonAchievement", cv, null, null);
	}
}
