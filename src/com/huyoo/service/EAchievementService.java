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
			ac.setDescription("这里是成就 的具体说明这");
			ac.setExp(10);
			ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
			ac.setId(i);
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
		EAchievement ac = new EAchievement();
		ac.setDescription("这里是成就 的具体说明这里是成就 的具体说明这里是成就 的具体说明这里是成就 的具体说明这里是成就 的具体说明这里是成就 的具体说明这里是成就 的具体说明这里是成就 的具体说明");
		ac.setExp(0);
		ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/C5BA7EAC764C4E60A2C707AAD40BED37");
		ac.setId(10000);
		ac.setName("更多");
		ac.setType("综合类");
		List<EAchievement> achievements = getEAchievements(null).subList(0, num);
		achievements.add(ac);
		return achievements;
	}
	
	public List<EAchievement> getRecommendEAchievements(int personId,int num) {
		
		return getEAchievements(null).subList(0, num);
	}
	
	public List<EAchievement> getEAchievementsByType(int person,String type){
		List<EAchievement> achievements = new ArrayList<EAchievement>();
		switch (type) {
		case "社交类":
			for(int i = 0 ;i < 10;i++)
			{
				EAchievement ac = new EAchievement();
				ac.setDescription("这里是成就 的具体说明这");
				ac.setExp(10);
				ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
				ac.setId(i);
				ac.setName("社交"+i);
				ac.setType("社交类");
				achievements.add(ac);
			}
			break;
		case "娱乐类":
			for(int i = 0 ;i < 7;i++)
			{
				EAchievement ac = new EAchievement();
				ac.setDescription("这里是成就 的具体说明这");
				ac.setExp(10);
				ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
				ac.setId(i);
				ac.setName("娱乐"+i);
				ac.setType("娱乐类");
				achievements.add(ac);
			}
			break;
		case "竞技类":
			for(int i = 0 ;i < 12;i++)
			{
				EAchievement ac = new EAchievement();
				ac.setDescription("这里是成就 的具体说明这");
				ac.setExp(10);
				ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
				ac.setId(i);
				ac.setName("竞技"+i);
				ac.setType("person");
				achievements.add(ac);
			}
			break;
		case "探索类":
			for(int i = 0 ;i < 10;i++)
			{
				EAchievement ac = new EAchievement();
				ac.setDescription("这里是成就 的具体说明这");
				ac.setExp(10);
				ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
				ac.setId(i);
				ac.setName("探索"+i);
				ac.setType("探索类");
				achievements.add(ac);
			}
			break;

		case "综合类":
			for(int i = 0 ;i < 10;i++)
			{
				EAchievement ac = new EAchievement();
				ac.setDescription("这里是成就 的具体说明这");
				ac.setExp(10);
				ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
				ac.setId(i);
				ac.setName("综合"+i);
				ac.setType("综合类");
				achievements.add(ac);
			}
			break;
		}
		return achievements;
	}
}
