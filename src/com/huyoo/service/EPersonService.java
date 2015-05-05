package com.huyoo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huyoo.entity.EPerson;
import com.huyoo.entity.RAttention;
import com.huyoo.entity.RUnionPerson;
import com.huyoo.global.Achievement;
import com.huyoo.global.Application;
import com.huyoo.global.DatabaseHelper;

/**
 * 用户服务
 * @author xufei 20150302
 *
 */
public class EPersonService {

	/**
	 * 根据参数获取用户信息
	 * @param params
	 * @return
	 */
	public List<EPerson> getPersons(Map<String,Object> params){
		List<EPerson> persons = new ArrayList<EPerson>();
		DatabaseHelper helper =	Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer sb = new StringBuffer();
		List<String> selectionArgs = new ArrayList<String>();
		String sql = "select * from EPerson where 1=1";
		if(params!=null){
			if(params.get("id")!=null){
				sb.append(" and id=?");
				selectionArgs.add(params.get("id").toString());
			}
			if(params.get("phoneNum")!=null)
			{
				sb.append(" and phoneNum=?");
				selectionArgs.add(params.get("phoneNum").toString());
			}
		}
		sql += sb.toString();
		String[] args = new String[selectionArgs.size()];
		Cursor cursor = db.rawQuery(sql,selectionArgs.toArray(args));
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String sex = cursor.getString(cursor.getColumnIndex("sex"));
			String school = cursor.getString(cursor.getColumnIndex("school"));
			String department = cursor.getString(cursor.getColumnIndex("department"));
			long birthday = cursor.getLong(cursor.getColumnIndex("birthday"));
			String phoneNum = cursor.getString(cursor.getColumnIndex("phoneNum"));
			String position = cursor.getString(cursor.getColumnIndex("position"));
			String password = cursor.getString(cursor.getColumnIndex("password"));
			int levelId = cursor.getInt(cursor.getColumnIndex("levelId"));
			int vp = cursor.getInt(cursor.getColumnIndex("vp"));
			String icon = cursor.getString(cursor.getColumnIndex("icon"));
			int currentExp = cursor.getInt(cursor.getColumnIndex("currentExp"));

			EPerson person = new EPerson();
			person.setId(id);
			person.setName(name);
			person.setSex(sex);
			person.setSchool(school);
			person.setDepartment(department);
			person.setBirthday(birthday);
			person.setPhoneNum(phoneNum);
			person.setPosition(position);
			person.setPassword(password);
			person.setLevelId(levelId);
			person.setVp(vp);
			person.setIcon(icon);
			person.setCurrentExp(currentExp);
			persons.add(person);
		}
		return persons;
	}
	/**
	 * 根据用户id获取
	 * @param id
	 * @return
	 */
	public EPerson getEPersonById(int id){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<EPerson> persons = getPersons(params);
		if(persons!=null&&persons.size()>0)return persons.get(0);
		return null;
	}

	/**
	 * 获取工会用户
	 * @param unionId
	 * @return
	 */
	public List<EPerson> getEPersonsByUnionId(int unionId)
	{
		List<EPerson> persons = new ArrayList<EPerson>();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", "in");
		params.put("unionId", unionId);
		List<RUnionPerson> ups = getUnionPersons(params);
		if(ups!=null&&ups.size()>0){
			for (RUnionPerson up : ups) {
				EPerson person = getEPersonById(up.getPersonId());
				if(person!=null)persons.add(person);
			}
		}
		return persons;
	}

	/**
	 * 根据用户手机号获取
	 * @param phoneNum
	 * @return
	 */
	public EPerson getEPersonByPhoneNum(String phoneNum){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("phoneNum", phoneNum);
		List<EPerson> persons = getPersons(params);
		if(persons!=null&&persons.size()>0)return persons.get(0);
		return null;
	}
	
	/**
	 * 添加用户
	 * @param person
	 * @return
	 */
	public long addPerson(EPerson person){
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("name", person.getName());
		cv.put("levelId", person.getLevelId());
		cv.put("icon", person.getIcon());
		cv.put("sex", person.getSex());
		cv.put("school", person.getSchool());
		cv.put("department", person.getDepartment());
		cv.put("birthday", person.getBirthday());
		cv.put("phoneNum", person.getPhoneNum());
		cv.put("password", person.getPassword());
		cv.put("position",person.getPosition());
		cv.put("vp",person.getVp());
		cv.put("currentExp", person.getCurrentExp());
		return db.insert("EPerson", null, cv);
	}
	
	/**
	 * 更新用户
	 * @param person
	 * @return
	 */
	public long updatePerson(EPerson person){
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("name", person.getName());
		cv.put("levelId", person.getLevelId());
		cv.put("icon", person.getIcon());
		cv.put("sex", person.getSex());
		cv.put("school", person.getSchool());
		cv.put("department", person.getDepartment());
		cv.put("birthday", person.getBirthday());
		cv.put("phoneNum", person.getPhoneNum());
		cv.put("password", person.getPassword());
		cv.put("position",person.getPosition());
		cv.put("vp",person.getVp());
		cv.put("currentExp", person.getCurrentExp());
		return db.update("EPerson", cv, "id=?", new String[]{person.getId()+""});
	}
	/*=========================================================================*/
	/**
	 * 获取用户关注
	 * @param params
	 * @return
	 */
	public List<RAttention> getAttentions(Map<String,Object> params){
		List<RAttention> attentions = new ArrayList<RAttention>();
		DatabaseHelper helper =	Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer sb = new StringBuffer();
		List<String> selectionArgs = new ArrayList<String>();
		String sql = "select * from RAttention where 1=1";
		if(params!=null){
			if(params.get("id")!=null){
				sb.append(" and id=?");
				selectionArgs.add(params.get("id").toString());
			}
			if(params.get("personIdFrom")!=null)
			{
				sb.append(" and personIdFrom=?");
				selectionArgs.add(params.get("personIdFrom").toString());
			}
			if(params.get("personIdTo")!=null)
			{
				sb.append(" and personIdTo=?");
				selectionArgs.add(params.get("personIdTo").toString());
			}
		}
		sql += sb.toString();
		String[] args = new String[selectionArgs.size()];
		Cursor cursor = db.rawQuery(sql,selectionArgs.toArray(args));
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			int personIdFrom = cursor.getInt(cursor.getColumnIndex("personIdFrom"));
			int personIdTo = cursor.getInt(cursor.getColumnIndex("personIdTo"));
			long time = cursor.getLong(cursor.getColumnIndex("time"));

			RAttention attention = new RAttention();
			attention.setId(id);
			attention.setPersonIdFrom(personIdFrom);
			attention.setPersonIdTo(personIdTo);
			attention.setTime(time);
			attentions.add(attention);
		}
		return attentions;
	}

	/**
	 * 获取来自于用户id的关注信息
	 * @param id
	 * @return
	 */
	public List<RAttention> getAttentionFrom(int id){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("personIdFrom", id);
		return getAttentions(params);
	}

	/**
	 * 获取关注id的所有关注
	 * @param id
	 * @return
	 */
	public List<RAttention> getAttentionTo(int id){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("personIdTo", id);
		return getAttentions(params);
	}
	/**
	 * 通过id获取的关注数，也就是其关注别人的数量
	 * @param personId
	 * @return
	 */
	public int getFocusCount(int id)
	{
		return getAttentionFrom(id).size();
	}
	/**
	 * 通过id获取被关注数，也就是其粉丝数
	 * @param personId
	 * @return
	 */
	public int getFansCount(int id)
	{
		return getAttentionTo(id).size();
	}


	/**
	 * 通过id获取其好友列表，满足好友的条件是，既被他关注，也关注了他的。
	 * @param id
	 * @return
	 */
	public List<EPerson> getFriends(int id)
	{ 
		List<EPerson> persons = new ArrayList<EPerson>();
		List<RAttention> fans = getAttentionTo(id);
		List<RAttention> focus = getAttentionFrom(id);
		if(fans!=null&&fans.size()>0&&focus!=null&&focus.size()>0){
			for(RAttention fan:fans){
				for(RAttention f:focus){
					if(fan.getPersonIdFrom() == f.getPersonIdTo()){
						EPerson person = getEPersonById(fan.getPersonIdFrom());
						if(person!=null)persons.add(person);
					}
				}
			}
		}
		return persons;
	}
	/**
	 * 添加关注
	 * @param attention
	 * @return
	 */
	public long addAttention(RAttention attention){
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("personIdTo", attention.getPersonIdTo());
		cv.put("personIdFrom", attention.getPersonIdFrom());
		cv.put("time", attention.getTime());
		long result = db.insert("RAttention", null, cv);
		if(result!=-1)Achievement.payAttention();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("personIdTo",attention.getPersonIdFrom());
		params.put("personIdFrom",attention.getPersonIdTo());
		if(getAttentions(params)!=null&&getAttentions(params).size()>0)Achievement.haveFriend();
		return result;
	}
	/**
	 * 移除好友：移除关注与被关注
	 * @param id
	 * @return
	 */
	public long removeFriend(int id){
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getWritableDatabase();
		return db.delete("RAttention", "personIdTo=? and personIdFrom=?", new String[]{id+"",Application.getLoginInfo().getPerson().getId()+""});
	}


	/*================================================================================*/
	/**
	 * 获取公户成员
	 * @param params
	 * @return
	 */
	public List<RUnionPerson> getUnionPersons(Map<String,Object> params){
		List<RUnionPerson> rups = new ArrayList<RUnionPerson>();
		DatabaseHelper helper =	Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer sb = new StringBuffer();
		List<String> selectionArgs = new ArrayList<String>();
		String sql = "select * from RUnionPerson where 1=1";
		if(params!=null){
			if(params.get("id")!=null){
				sb.append(" and id=?");
				selectionArgs.add(params.get("id").toString());
			}
			if(params.get("unionId")!=null)
			{
				sb.append(" and unionId=?");
				selectionArgs.add(params.get("unionId").toString());
			}
			if(params.get("personId")!=null)
			{
				sb.append(" and personId=?");
				selectionArgs.add(params.get("personId").toString());
			}
			if(params.get("status")!=null)
			{
				sb.append(" and status=?");
				selectionArgs.add(params.get("status").toString());
			}

		}
		sql += sb.toString();
		String[] args = new String[selectionArgs.size()];
		Cursor cursor = db.rawQuery(sql,selectionArgs.toArray(args));
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			int personId = cursor.getInt(cursor.getColumnIndex("personId"));
			int unionId = cursor.getInt(cursor.getColumnIndex("unionId"));
			long time = cursor.getLong(cursor.getColumnIndex("time"));
			String status = cursor.getString(cursor.getColumnIndex("status"));
			RUnionPerson up = new RUnionPerson();
			up.setId(id);
			up.setUnionId(unionId);
			up.setPersonId(personId);
			up.setTime(time);
			up.setStatus(status);
			rups.add(up);
		}
		return rups;
	}

	/**
	 * 获取personId所在工会的工会成员
	 * @param personId
	 * @return
	 */
	public List<RUnionPerson> getUnionPersonByPersonId(int personId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("personId", personId);
		params.put("status", "in");
		return getUnionPersons(params);
	}
	/**
	 * 获取 工会的成员，根据工会id
	 * @param unionId
	 * @return
	 */
	public List<EPerson> getPersonsByUnionId(int unionId)
	{
		List<EPerson> persons = new ArrayList<EPerson>();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("unionId", unionId);
		params.put("status", "in");
		List<RUnionPerson> ups = getUnionPersons(params);
		if(ups!=null&&ups.size()>0){
			for (RUnionPerson up : ups) {
				EPerson person =getEPersonById(up.getPersonId());
				if(person!=null)persons.add(person);
			}
		}
		return persons;
	}
}
