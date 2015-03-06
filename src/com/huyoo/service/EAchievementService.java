package com.huyoo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huyoo.entity.EAchievement;

public class EAchievementService {
	
	public List<EAchievement> getEAchievements(Map<String,Object> params)
	{
		List<EAchievement> achievements = new ArrayList<EAchievement>();
		for(int i= 0 ;i < 10; i++)
		{
			EAchievement ac = new EAchievement();
			ac.setDescription("这里是成就 的具体说明");
			ac.setExp(10);
			ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
			ac.setId(1);
			ac.setName("有车有房");
			ac.setType("person");
			achievements.add(ac);
		}
		return achievements;
	}
	
	public EAchievement getEAchievementById(int id)
	{
		return null;
	}
	
	public	List<EAchievement> getEAchievementsByPersonId(int personId)
	{
		return null;
	}
	
	public List<EAchievement> getLastestEAchievements(int personId,int num)
	{
		return getEAchievements(null).subList(0, num-1);
	}
}
