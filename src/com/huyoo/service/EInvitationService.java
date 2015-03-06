package com.huyoo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huyoo.entity.EInvitation;

public class EInvitationService {

	public List<EInvitation> getInvitations(Map<String,Object> params)
	{
		return null;
	}
	public List<Map<String,Object>> getInvitationsMapByUnionId(int UnionId)
	{
		/*
		personName
		personLevel
		issueTime
		activityTime
		content
		address
		currentNum
		maxNum
		isJoin 0:1(join)
		 * */
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		Map<String ,Object> m=new HashMap<String, Object>();
		m.put("id", 1);
		m.put("personName", "张三");
		m.put("personLevel", "塔基大师");
		m.put("issueTime", 1425545750006l);
		m.put("activityTime", 1425546750006l);
		m.put("content", "周六晚上过来玩");
		m.put("address", "你想不到的地方");
		m.put("currentNum", 15);
		m.put("maxNum", 50);
		m.put("isJoin", 1);
		Map<String ,Object> m1=new HashMap<String, Object>();
		m1.put("id", 2);
		m1.put("personName", "李四");
		m1.put("personLevel", "塔基大师");
		m1.put("issueTime", 1425545750006l);
		m1.put("activityTime", 1425546750006l);
		m1.put("content", "周日晚上过来玩");
		m1.put("address", "你想到的地方");
		m1.put("currentNum", 14);
		m1.put("maxNum", 30);
		m1.put("isJoin", 0);
		Map<String ,Object> m2=new HashMap<String, Object>();
		m2.put("id", 2);
		m2.put("personName", "王二");
		m2.put("personLevel", "塔基大师1");
		m2.put("issueTime", 1425545750006l);
		m2.put("activityTime", 1425546750006l);
		m2.put("content", "周一晚上过来玩");
		m2.put("address", "你想到的地方1");
		m2.put("currentNum", 10);
		m2.put("maxNum", 10);
		m2.put("isJoin", 1);
		Map<String ,Object> m3=new HashMap<String, Object>();
		m3.put("id", 1);
		m3.put("personName", "张三");
		m3.put("personLevel", "塔基大师");
		m3.put("issueTime", 1425545750006l);
		m3.put("activityTime", 1425546750006l);
		m3.put("content", "周六晚上过来玩");
		m3.put("address", "你想不到的地方");
		m3.put("currentNum", 15);
		m3.put("maxNum", 50);
		m3.put("isJoin", 1);
		Map<String ,Object> m4=new HashMap<String, Object>();
		m4.put("id", 1);
		m4.put("personName", "张三");
		m4.put("personLevel", "塔基大师");
		m4.put("issueTime", 1425545750006l);
		m4.put("activityTime", 1425546750006l);
		m4.put("content", "周六晚上过来玩");
		m4.put("address", "你想不到的地方");
		m4.put("currentNum", 15);
		m4.put("maxNum", 50);
		m4.put("isJoin", 1);
		Map<String ,Object> m5=new HashMap<String, Object>();
		m5.put("id", 1);
		m5.put("personName", "张三");
		m5.put("personLevel", "塔基大师");
		m5.put("issueTime", 1425545750006l);
		m5.put("activityTime", 1425546750006l);
		m5.put("content", "周六晚上过来玩");
		m5.put("address", "你想不到的地方");
		m5.put("currentNum", 15);
		m5.put("maxNum", 50);
		m5.put("isJoin", 1);
		Map<String ,Object> m6=new HashMap<String, Object>();
		m6.put("id", 1);
		m6.put("personName", "张三");
		m6.put("personLevel", "塔基大师");
		m6.put("issueTime", 1425545750006l);
		m6.put("activityTime", 1425546750006l);
		m6.put("content", "周六晚上过来玩");
		m6.put("address", "你想不到的地方");
		m6.put("currentNum", 15);
		m6.put("maxNum", 50);
		m6.put("isJoin", 1);
		Map<String ,Object> m7=new HashMap<String, Object>();
		m7.put("id", 1);
		m7.put("personName", "张三");
		m7.put("personLevel", "塔基大师");
		m7.put("issueTime", 1425545750006l);
		m7.put("activityTime", 1425546750006l);
		m7.put("content", "周六晚上过来玩");
		m7.put("address", "你想不到的地方");
		m7.put("currentNum", 15);
		m7.put("maxNum", 50);
		m7.put("isJoin", 1);
		list.add(m);
		list.add(m1);
		list.add(m3);
		list.add(m4);
		list.add(m5);
		list.add(m6);
		list.add(m7);
		return list;
	}
}
