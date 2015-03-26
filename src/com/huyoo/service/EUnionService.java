package com.huyoo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.WebView;

import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EArticle;
import com.huyoo.entity.EUnion;
import com.huyoo.entity.RUnionPerson;
import com.huyoo.global.Application;
import com.huyoo.global.DatabaseHelper;

/**
 * 
 * @author HJL 20150302
 *
 */
public class EUnionService {

	public List<EUnion> getUnions(Map<String,Object> params){
		List<EUnion> unions = new ArrayList<EUnion>();
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer sb = new StringBuffer();
		List<String> selectionArgs = new ArrayList<String>();
		String sql = "select * from EUnion where 1=1";
		if(params!=null){
			if(params.get("id")!=null){
				sb.append(" and id=?");
				selectionArgs.add(params.get("id").toString());
			}
			if(params.get("time")!=null){
				sb.append(" and time=?");
				selectionArgs.add(params.get("time").toString());
			}
		}
		sql += sb.toString();
		String[] args = new String[selectionArgs.size()];
		Cursor cursor = db.rawQuery(sql,selectionArgs.toArray(args));
		while(cursor.moveToNext()){
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int chairmanId = cursor.getInt(cursor.getColumnIndex("chairmanId"));
			int levelId = cursor.getInt(cursor.getColumnIndex("levelId"));
			String type = cursor.getString(cursor.getColumnIndex("type"));
			int totalNum = cursor.getInt(cursor.getColumnIndex("totalNum"));
			String icon = cursor.getString(cursor.getColumnIndex("icon"));
			long time = cursor.getLong(cursor.getColumnIndex("time"));
			int currentExp = cursor.getInt(cursor.getColumnIndex("currentExp"));
			int activityNum = cursor.getInt(cursor.getColumnIndex("activityNum"));
			String status = cursor.getString(cursor.getColumnIndex("status"));
			EUnion union = new EUnion();
			union.setId(id);
			union.setName(name);
			union.setChairmanId(chairmanId);
			union.setLevelId(levelId);
			union.setType(type);
			union.setTotalNum(totalNum);
			union.setIcon(icon);
			union.setTime(time);
			union.setCurrentExp(currentExp);
			union.setActivityNum(activityNum);
			union.setStatus(status);
			unions.add(union);
		}
		return unions;
	}

	public EUnion getEUnionByID(int id)
	{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<EUnion> unions = getUnions(params);
		if(unions!=null&&unions.size()>0)return unions.get(0);
		return null;
	}

	public EUnion getUnionByPersonId(int personId){
		List<RUnionPerson> ups = Application.getPersonService().getUnionPersonByPersonId(personId);
		if(ups!=null&&ups.size()>0){
			for (RUnionPerson up : ups) {
				EUnion union = getEUnionByID(up.getUnionId());
				if(union!=null&&"normal".equals(union.getStatus()))return union;
			}
		}
		return null;
	}

	public List<EUnion> getAllEUnion()
	{
		return getUnions(null);
	}
	/**
	 * 返回公会最新动态，服务端返回html字符串
	 * @param id
	 * @param num
	 * @return
	 */
	public List<String> getLatestNews(int id,int num)
	{
		List<String> list = new ArrayList<String>();
		for(int i =0;i<num;i++)
		{
			String data ="<img height=\"15dp;\" src=\"http://a2.qpic.cn/psb?/V123gJXv3aiMj6/80WTBdgeP3GoowD3vBhdlA1xmlyksgJQOl.sxKSE99c!/b/dAQAAAAAAAAA&bo=yQDcAAAAAAABBzU!&rf=viewer_4\"/>"
					+"<span style=\"font-weight:bold;\">王昆</span>完成了"
					+"<span style=\"color:#ffbb33\">[这尼玛是什么]</span>成就";
			list.add(data);
		}
		return list;
	}
	public String getApplyUnionNotice(){
		return "这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。"
				+ "这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。"
				+ "这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。";
	}


	public int saveUnion(EUnion union){
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		//cv.put("id", 1);
		cv.put("name", union.getName());
		cv.put("currentExp", union.getCurrentExp());
		cv.put("icon", union.getIcon());
		cv.put("levelId", union.getLevelId());
		cv.put("time", union.getTime());
		cv.put("totalNum", union.getTotalNum());
		cv.put("status", union.getStatus());
		cv.put("type", union.getType());
		cv.put("activityNum", union.getActivityNum());
		long result = db.insert("EUnion", null, cv);
		if(result != -1){
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("time", union.getTime());
			List<EUnion> unions = getUnions(params);
			if(unions!=null)return unions.get(0).getId();
		}
		return -1;
	} 

	public void saveUnionPerson(List<RUnionPerson> ups){

	}

//	public List<EUnion> getUnionsByPersonId(int personId){
//		return getUnions(null);
//	}

	public long removePersonFromUnion(int id){
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("status","out");
		return db.update("RUnionPerson", cv, "personId=? and unionId=?",new String[]{id+"",Application.getLoginInfo().getUnion().getId()+""});
	}
}
