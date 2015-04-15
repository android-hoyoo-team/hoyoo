package com.huyoo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.RPersonAchievement;
import com.huyoo.global.Application;
import com.huyoo.global.DatabaseHelper;

/**
 * 等级服务
 * @author HJL 20150302
 *
 */
public class ELevelService {
	
	/**
	 * 根据参数获取等级
	 * @param params
	 * @return
	 */
	public List<ELevel> getLevels(Map<String,Object> params){
		List<ELevel> levels = new ArrayList<ELevel>();
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer sb = new StringBuffer();
		List<String> selectionArgs = new ArrayList<String>();
		String sql = "select * from ELevel where 1=1";
		if(params!=null){
			if(params.get("id")!=null){
				sb.append(" and id=?");
				selectionArgs.add(params.get("id").toString());
			}
		}
		sql += sb.toString();
		String[] args = new String[selectionArgs.size()];
		Cursor cursor = db.rawQuery(sql,selectionArgs.toArray(args));
		while(cursor.moveToNext()){
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int upgradeExp = cursor.getInt(cursor.getColumnIndex("upgradeExp"));
			String type = cursor.getString(cursor.getColumnIndex("type"));
			ELevel level = new ELevel();
			level.setId(id);
			level.setName(name);
			level.setUpgradeExp(upgradeExp);
			level.setType(type);
			levels.add(level);
		}
		return levels;
	}
	
	
	/**
	 * 根据id获取等级信息
	 * @param id
	 * @return
	 */
	public ELevel getELevelByID(int id)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<ELevel> levels = getLevels(params);
		if(levels!=null&&levels.size()>0)return levels.get(0);
		return null;
	}
	/**
	 * 获取等级信息
	 * @return
	 */
	public List<ELevel> getAllELevels()
	{
		return null;
	}
}
