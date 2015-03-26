package com.huyoo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huyoo.entity.EAchievement;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.RPersonAchievement;
import com.huyoo.global.Application;
import com.huyoo.global.DatabaseHelper;

public class EAchievementService {

	public List<EAchievement> getEAchievements(Map<String,Object> params){
		List<EAchievement> achievements = new ArrayList<EAchievement>();
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer sb = new StringBuffer();
		List<String> selectionArgs = new ArrayList<String>();
		String sql = "select * from EAchievement where 1=1";
		if(params!=null){
			if(params.get("id")!=null){
				sb.append(" and id=?");
				selectionArgs.add(params.get("id").toString());
			}
			if(params.get("expensionPack")!=null){
				sb.append(" and expensionPack=?");
				selectionArgs.add(params.get("expensionPack").toString());
			}
			if(params.get("type")!=null){
				sb.append(" and type=?");
				selectionArgs.add(params.get("type").toString());
			}
		}
		sql += sb.toString();
		String[] args = new String[selectionArgs.size()];
		Cursor cursor = db.rawQuery(sql,selectionArgs.toArray(args));
		while(cursor.moveToNext()){
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int exp = cursor.getInt(cursor.getColumnIndex("exp"));
			String type = cursor.getString(cursor.getColumnIndex("type"));
			String description = cursor.getString(cursor.getColumnIndex("description"));
			String icon = cursor.getString(cursor.getColumnIndex("icon"));
			String addition = cursor.getString(cursor.getColumnIndex("addition"));
			int totalProgress = cursor.getInt(cursor.getColumnIndex("totalProgress"));
			String expensionPack = cursor.getString(cursor.getColumnIndex("expensionPack"));

			EAchievement achievement = new EAchievement();
			achievement.setId(id);
			achievement.setName(name);
			achievement.setExp(exp);
			achievement.setType(type);
			achievement.setDescription(description);
			achievement.setIcon(icon);
			achievement.setAddition(addition);
			achievement.setTotalProgress(totalProgress);
			achievement.setExpensionPack(expensionPack);
			achievements.add(achievement);
		}
		return achievements;
	}


	public EAchievement getEAchievementById(int id)
	{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<EAchievement> achievements = getEAchievements(params);
		if(achievements!=null&&achievements.size()>0)return achievements.get(0);
		return null;
	}
	public	List<EAchievement> getEAchievementsByPersonId(int personId)
	{
		List<EAchievement> achievements = new ArrayList<EAchievement>();
		List<RPersonAchievement> rpas = getPersonAchievementByPersonId(personId, true);
		if(rpas!=null&&rpas.size()>0){
			for (RPersonAchievement rpa : rpas) {
				EAchievement achievement = getEAchievementById(rpa.getAchievementId());
				if(achievement!=null&&rpa.getCurrentProgress()==achievement.getTotalProgress()){
					achievements.add(achievement);
				}
			}
		}
		return achievements;
	}

	public List<EAchievement> getLastestEAchievements(int personId,int num){
		List<EAchievement> achievements = getEAchievementsByPersonId(personId);
		if(achievements == null)return null;
		if(achievements.size()<num) return achievements;
		return achievements.subList(0, num);
	}

	public List<EAchievement> getRecommendEAchievements(int personId,int num) {
		List<EAchievement> ras = new ArrayList<EAchievement>();
		List<EAchievement> achievements = getEAchievements(null);
		if(achievements!=null&&achievements.size()>0){
			for (EAchievement eAchievement : achievements) {
				RPersonAchievement personAchievementBy = getPersonAchievementBy(personId, eAchievement.getId());
				
				if(personAchievementBy!=null&&eAchievement.getTotalProgress()>personAchievementBy.getCurrentProgress()){
					ras.add(eAchievement);
					if(ras.size()==num)return ras;
				}
			}
		}
		return ras;
	}
	
	public List<EAchievement> getRelativeAchievements(int id){
		List<EAchievement> ras = new ArrayList<EAchievement>();
		EAchievement achievement = getEAchievementById(id);
		if(achievement!=null){
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("expensionPack", achievement.getExpensionPack());
			List<EAchievement> achievements=getEAchievements(params);
			if(achievements!=null&&achievements.size()>0){
				for (EAchievement eAchievement : achievements) {
					if(eAchievement.getId()!=id){
						ras.add(eAchievement);
					}
				}
			}
		}
		return ras;
	}

	public List<EAchievement> getEAchievementsByType(String type){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("type", type);
		return getEAchievements(params);
	}
	/*=============================================================================================*/
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
		String[] array = selectionArgs.toArray(args);
		Cursor cursor = db.rawQuery(sql,array);
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

	public List<RPersonAchievement> getPersonAchievementByPersonId(int personId,boolean isSortByTime){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("personId", personId);
		List<RPersonAchievement> rpas = new ArrayList<RPersonAchievement>();
		if(isSortByTime){
			Collections.sort(rpas,new Comparator<RPersonAchievement>(){

				@Override
				public int compare(RPersonAchievement lhs,
						RPersonAchievement rhs) {
					// TODO Auto-generated method stub
					if(lhs.getTime()<rhs.getTime())
						return 1;
					else if(lhs.getTime()>rhs.getTime())
						return -1;
					return 0;
				}
			});
		}
		rpas = getPersonAchievements(params);
		return rpas;
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
