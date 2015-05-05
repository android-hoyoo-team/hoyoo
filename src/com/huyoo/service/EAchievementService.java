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

/**
 * 处理成就的服务 后期直接替换成相应的后台服务
 * @author HJL
 *
 */
public class EAchievementService {

	/**
	 * 根据参数获取相应的成就
	 * @param params
	 * @return
	 */
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


	/**
	 * 根据成就id获取成就
	 * @param id
	 * @return
	 */
	public EAchievement getEAchievementById(int id)
	{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<EAchievement> achievements = getEAchievements(params);
		if(achievements!=null&&achievements.size()>0)return achievements.get(0);
		return null;
	}
	/**
	 * 根据用户id获取成就
	 * @param personId
	 * @return
	 */
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

	/**
	 * 获取最新成就
	 * @param personId 用户id 
	 * @param num 数量
	 * @return
	 */
	public List<EAchievement> getLastestEAchievements(int personId,int num){
		List<EAchievement> achievements = getEAchievementsByPersonId(personId);
		if(achievements == null)return null;
		if(achievements.size()<num) return achievements;
		return achievements.subList(0, num);
	}

	/**
	 * 获取推荐完成的成绩
	 * @param personId 用户
	 * @param num 数量
	 * @return
	 */
	public List<EAchievement> getRecommendEAchievements(int personId,int num) {
		List<EAchievement> ras = new ArrayList<EAchievement>();
		List<EAchievement> achievements = getEAchievements(null);
		if(achievements!=null&&achievements.size()>0){
			for (EAchievement eAchievement : achievements) {
				RPersonAchievement personAchievement = getPersonAchievementBy(personId, eAchievement.getId());
				if(personAchievement==null||eAchievement.getTotalProgress()>personAchievement.getCurrentProgress())
				{
					ras.add(eAchievement);
					if(ras.size()==num)return ras;
				}
			}
		}
		return ras;
	}
	
	/**
	 * 根据 成就id 获取其相关成就
	 * @param id 
	 * @return
	 */
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

	/**
	 * 按照类别获取
	 * @param type 类别
	 * @return
	 */
	public List<EAchievement> getEAchievementsByType(String type){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("type", type);
		return getEAchievements(params);
	}
	/*=============================================================================================*/
	/**
	 * 获取人的成就关系
	 * @param params 参数类表
	 * @return
	 */
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

	/**
	 * 根据用户id 获取人成就关系 
	 * @param personId
	 * @param isSortByTime
	 * @return
	 */
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

	/**
	 * 根据 用户id 成就id 获取 唯一成就关系 
	 * @param personId
	 * @param achievementId
	 * @return
	 */
	public RPersonAchievement getPersonAchievementBy(int personId,int achievementId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("personId", personId);
		params.put("achievementId", achievementId);
		List<RPersonAchievement> rpas = getPersonAchievements(params);
		if(rpas!=null&&rpas.size()>0)return rpas.get(0);
		return null;
	}

	/**
	 * 获得成就
	 * @param rpa
	 * @return
	 */
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
	/**
	 * 更新用户成就
	 * @param rpa
	 * @return
	 */
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
