package com.huyoo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huyoo.entity.EPerson;
import com.huyoo.entity.RUnionPerson;

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
		for(int i = 0;i<20;i++)
		{
			EPerson person = new EPerson();
			person.setId(i+1);
			person.setName("王昆"+i);
			person.setLevelId(1);
			person.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/9855C5331E004040B1A5D6C9D8483108");
			person.setSex("男");
			person.setSchool("蚌埠学院");
			person.setDepartment("计算机");
			person.setBirthday(10000000000l);
			person.setPhoneNum("18888888888");
			person.setPosition("学生会主席");
			person.setUnionId(i+1);
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

		for(int i = 0;i<getPersons(null).size();i++){
			if(id == getPersons(null).get(i).getId()){
				return getPersons(null).get(i);
			}
		}
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
		EPerson person = new EPerson();
		switch (phoneNum) {
		case "10000":
			//正常状态，有工会
			person.setId(1);
			person.setName("王昆");
			person.setLevelId(1);
			person.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/9855C5331E004040B1A5D6C9D8483108");
			person.setSex("男");
			person.setSchool("蚌埠学院");
			person.setDepartment("计算机");
			person.setBirthday(10000000000l);
			person.setPhoneNum(phoneNum);
			person.setPosition("学生会主席");
			person.setUnionId(1);
			person.setVp(80);
			person.setCurrentExp(2730);
			break;
		case "10001":
			//无工会，无申请，无邀请
			person.setId(2);
			person.setName("吕高昂");
			person.setLevelId(1);
			person.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/8AF5E330B6E245BAAAFC8078BC8A9D91");
			person.setSex("男");
			person.setSchool("蚌埠学院");
			person.setDepartment("计算机");
			person.setBirthday(10000000000l);
			person.setPhoneNum(phoneNum);
			person.setPosition("学生会主席");
			person.setVp(80);
			person.setCurrentExp(2730);
			break;
		case "10002":
			//无工会,有申请
			person.setId(3);
			person.setName("何磊");
			person.setLevelId(1);
			person.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/51DD76FE9A8349DFA47BEAD61C91E523");
			person.setSex("男");
			person.setSchool("蚌埠学院");
			person.setDepartment("计算机");
			person.setBirthday(10000000000l);
			person.setPhoneNum(phoneNum);
			person.setPosition("学生会主席");
			person.setVp(80);
			person.setCurrentExp(2730);
			break;
		case "10003":
			//无工会,有申请
			person.setId(4);
			person.setName("莫小千");
			person.setLevelId(1);
			person.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/948552F5DB63434AA0F970301A675FDB");
			person.setSex("女");
			person.setSchool("蚌埠学院");
			person.setDepartment("计算机");
			person.setBirthday(10000000000l);
			person.setPhoneNum(phoneNum);
			person.setPosition("学生会主席");
			person.setVp(80);
			person.setCurrentExp(2730);
			break;
		default:
			break;
		}
		return person;
				//		Map<String,Object> params = new HashMap<String, Object>();
				//		params.put("phoneNum", phoneNum);
				//		List<EPerson> persons = this.getPersons(params);
				//		if(persons!=null&&persons.size()>0)
				//			return persons.get(0);
				//		return null; 
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
}
