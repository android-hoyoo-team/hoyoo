package com.huyoo.service;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huyoo.entity.EPerson;

/**
 * 
 * @author xufei 20150302
 *
 */
public class EPersonService {

	/**
	 * 
	 * @param param
	 * @return
	 */
	public List<EPerson> getPersons(Map<String,Object> params)
	{
		List<EPerson> persons = new ArrayList<EPerson>();
		//		if(params.containsKey("phoneNum"))
		//		{
		for(int i = 0;i<10;i++)
		{
			EPerson person = new EPerson();
			person.setId(1);
			person.setName("王昆");
			person.setLevelId(1);
			person.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/9855C5331E004040B1A5D6C9D8483108");
			person.setSex("男");
			person.setSchool("蚌埠学院");
			person.setDepartment("计算机");
			person.setBirthday(10000000000l);
			person.setPhoneNum("18888888888");
			person.setPosition("学生会主席");
			person.setUnionId(1);
			person.setVp(80);
			person.setCurrentExp(2730);
			persons.add(person);
		}
		//		}
		return persons;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public EPerson getEPersonById(int id)
	{
		return getPersons(null).get(0);
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

	public EPerson getEPersonByPhoneNum(String phoneNum)
	{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("phoneNum", phoneNum);
		List<EPerson> persons = this.getPersons(params);
		if(persons!=null&&persons.size()>0)
			return persons.get(0);
		return null; 
	}

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
		return null;
	}

	public List<EPerson> getPersonsByUnionId(int unionId)
	{
		return getPersons(null);
	}
}
