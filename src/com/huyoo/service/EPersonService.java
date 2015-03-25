package com.huyoo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huyoo.entity.EPerson;
import com.huyoo.entity.RAttention;
import com.huyoo.entity.RUnionPerson;
import com.huyoo.global.Application;
import com.huyoo.global.DatabaseHelper;

/**
 * 
 * @author xufei 20150302
 *
 */
public class EPersonService {

//	/**
//	 * 
//	 * @param param
//	 * @return
//	 */
//	public List<EPerson> getPersons(Map<String,Object> params)
//	{
//		List<EPerson> persons = new ArrayList<EPerson>();
//		for(int i = 0;i<20;i++)
//		{
//			EPerson person = new EPerson();
//			person.setId(i+1);
//			person.setName("王昆"+i);
//			person.setLevelId(1);
//			person.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/9855C5331E004040B1A5D6C9D8483108");
//			person.setSex("男");
//			person.setSchool("蚌埠学院");
//			person.setDepartment("计算机");
//			person.setBirthday(10000000000l);
//			person.setPhoneNum("18888888888");
//			person.setPosition("学生会主席");
//			person.setUnionId(i+1);
//			person.setVp(80);
//			person.setCurrentExp(2730);
//			persons.add(person);
//		}
//		//		}
//		return persons;
//	}

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

//	/**
//	 * 
//	 * @param id
//	 * @return
//	 */
//	public EPerson getEPersonById(int id)
//	{
//
//		for(int i = 0;i<getPersons(null).size();i++){
//			if(id == getPersons(null).get(i).getId()){
//				return getPersons(null).get(i);
//			}
//		}
//		return getPersons(null).get(0);
//	}
	
	public EPerson getEPersonById(int id){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<EPerson> persons = getPersons(params);
		if(persons!=null&&persons.size()>0)return persons.get(0);
		return null;
	}

	/**
	 * 
	 * @param unionId
	 * @return
	 */
	public List<EPerson> getEPersonsByUnionId(int unionId)
	{
		return getPersons(null);
	}

	public EPerson getEPersonByPhoneNum(String phoneNum){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("phoneNum", phoneNum);
		List<EPerson> persons = getPersons(params);
		if(persons!=null&&persons.size()>0)return persons.get(0);
		return null;
	}
	
//	public EPerson getEPersonByPhoneNum(String phoneNum)
//	{
//		EPerson person = new EPerson();
//		switch (phoneNum) {
//		case "10000":
//			//正常状态，有工会
//			person.setId(1);
//			person.setName("王昆");
//			person.setLevelId(1);
//			person.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/9855C5331E004040B1A5D6C9D8483108");
//			person.setSex("男");
//			person.setSchool("蚌埠学院");
//			person.setDepartment("计算机");
//			person.setBirthday(10000000000l);
//			person.setPhoneNum(phoneNum);
//			person.setPosition("学生会主席");
//			person.setUnionId(1);
//			person.setVp(80);
//			person.setCurrentExp(2730);
//			break;
//		case "10001":
//			//无工会，无申请，无邀请
//			person.setId(2);
//			person.setName("吕高昂");
//			person.setLevelId(1);
//			person.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/8AF5E330B6E245BAAAFC8078BC8A9D91");
//			person.setSex("男");
//			person.setSchool("蚌埠学院");
//			person.setDepartment("计算机");
//			person.setBirthday(10000000000l);
//			person.setPhoneNum(phoneNum);
//			person.setPosition("学生会主席");
//			person.setVp(80);
//			person.setCurrentExp(2730);
//			break;
//		case "10002":
//			//无工会,有申请
//			person.setId(3);
//			person.setName("何磊");
//			person.setLevelId(1);
//			person.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/51DD76FE9A8349DFA47BEAD61C91E523");
//			person.setSex("男");
//			person.setSchool("蚌埠学院");
//			person.setDepartment("计算机");
//			person.setBirthday(10000000000l);
//			person.setPhoneNum(phoneNum);
//			person.setPosition("学生会主席");
//			person.setVp(80);
//			person.setCurrentExp(2730);
//			break;
//		case "10003":
//			//无工会,有申请
//			person.setId(4);
//			person.setName("莫小千");
//			person.setLevelId(1);
//			person.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/948552F5DB63434AA0F970301A675FDB");
//			person.setSex("女");
//			person.setSchool("蚌埠学院");
//			person.setDepartment("计算机");
//			person.setBirthday(10000000000l);
//			person.setPhoneNum(phoneNum);
//			person.setPosition("学生会主席");
//			person.setVp(80);
//			person.setCurrentExp(2730);
//			break;
//		default:
//			break;
//		}
//		return person;
//		//		Map<String,Object> params = new HashMap<String, Object>();
//		//		params.put("phoneNum", phoneNum);
//		//		List<EPerson> persons = this.getPersons(params);
//		//		if(persons!=null&&persons.size()>0)
//		//			return persons.get(0);
//		//		return null; 
//	}

	/**
	 * 通过id获取的关注数，也就是其关注别人的数量
	 * @param personId
	 * @return
	 */
	public int getFocusCount(int id)
	{
		return 220;
	}
	/**
	 * 通过id获取被关注数，也就是其粉丝数
	 * @param personId
	 * @return
	 */
	public int getFansCount(int id)
	{
		return 880;
	}

	/**
	 * 通过id获取其好友列表，满足好友的条件是，既被他关注，也关注了他的。
	 * @param id
	 * @return
	 */
	public List<EPerson> getFriends(int id)
	{

		return getPersons(null);
	}

	public List<EPerson> getPersonsByUnionId(int unionId)
	{
		return getPersons(null);
	}


	public List<RUnionPerson> getUnionPerson(int personId){
		List<RUnionPerson> ups = new ArrayList<RUnionPerson>();

		if(personId == 1){
			RUnionPerson up1 = new RUnionPerson();
			up1.setUnionId(1);
			up1.setPersonId(1);
			up1.setStatus("in");
			RUnionPerson up2 = new RUnionPerson();
			up2.setUnionId(2);
			up2.setPersonId(1);
			up2.setStatus("out");
			RUnionPerson up3 = new RUnionPerson();
			up3.setUnionId(3);
			up3.setPersonId(1);
			up3.setStatus("out");
			ups.add(up1);
			ups.add(up2);
			ups.add(up3);
		}
		if(personId == 2){
			RUnionPerson up1 = new RUnionPerson();
			up1.setUnionId(1);
			up1.setPersonId(2);
			up1.setStatus("out");
			RUnionPerson up2 = new RUnionPerson();
			up2.setUnionId(2);
			up2.setPersonId(2);
			up2.setStatus("in");
			RUnionPerson up3 = new RUnionPerson();
			up3.setUnionId(3);
			up3.setPersonId(2);
			up3.setStatus("out");
			ups.add(up1);
			ups.add(up2);
			ups.add(up3);
		}
		if(personId == 3){

		}
		return ups;
	}


	public boolean saveAttention(RAttention attention){

		return true;
	}

	public List<RAttention> getAttentionsByPersonId(int id){
		List<RAttention> attentions = new ArrayList<RAttention>();
		for(int i = 0;i<10;i++){
			RAttention attention = new RAttention();
			attention.setPersonIdFrom(id);
			attention.setPersonIdTo(i);
			attentions.add(attention);
		}
		return attentions;
	}

	public boolean removeFriend(int id){

		return true;
	}
}
