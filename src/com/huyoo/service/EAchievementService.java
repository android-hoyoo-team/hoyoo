package com.huyoo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huyoo.entity.EAchievement;

public class EAchievementService {
	
	public List<EAchievement> getEAchievements(Map<String,Object> params)
	{
		List<EAchievement> achievements = new ArrayList<EAchievement>();
//		for(int i= 0 ;i < 10; i++)
//		{
//			EAchievement ac = new EAchievement();
//			ac.setDescription("这里是成就 的具体说明这");
//			ac.setExp(10);
//			ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
//			ac.setId(i);
//			ac.setName("有车有房");
//			ac.setType("person");
//			achievements.add(ac);
//		}
		EAchievement eAchieviment1=new EAchievement();
		eAchieviment1.setAddition("首次登录HoYoo");
		eAchieviment1.setDescription("千呼万唤终于等到了你");
		eAchieviment1.setExp(20);
		eAchieviment1.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/A7CD0734EBCC440BA9DFF1CE45B988DD");
		eAchieviment1.setId(1);
		eAchieviment1.setName("你可来了！");
		eAchieviment1.setType("软件综合");
		achievements.add(eAchieviment1);
		EAchievement eAchieviment2=new EAchievement();
		eAchieviment2.setAddition("上传自己的头像");
		eAchieviment2.setDescription("此处应该有自拍");
		eAchieviment2.setExp(10);
		eAchieviment2.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/8B36B19036A345F7AA165614D9D8FE10");
		eAchieviment2.setId(2);
		eAchieviment2.setName("有头有脸");
		eAchieviment2.setType("软件综合");
		achievements.add(eAchieviment2);
		EAchievement eAchieviment3=new EAchievement();
		eAchieviment3.setAddition("第一次发布或参与一次邀请");
		eAchieviment3.setDescription("");
		eAchieviment3.setExp(15);
		eAchieviment3.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/AC4B59CE5F194B23A4444FF26C12CC10");
		eAchieviment3.setId(3);
		eAchieviment3.setName("好玩吧，没玩过吧");
		eAchieviment3.setType("软件综合");
		achievements.add(eAchieviment3);
		EAchievement eAchieviment4=new EAchievement();
		eAchieviment4.setAddition("第一次进入成就界面");
		eAchieviment4.setDescription("有无数的成就等着你完成");
		eAchieviment4.setExp(5);
		eAchieviment4.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/A7CD0734EBCC440BA9DFF1CE45B988DD");
		eAchieviment4.setId(4);
		eAchieviment4.setName("漫漫长路");
		eAchieviment4.setType("软件综合");
		achievements.add(eAchieviment4);
		EAchievement eAchieviment5=new EAchievement();
		eAchieviment5.setAddition("第一次关注别人");
		eAchieviment5.setDescription("");
		eAchieviment5.setExp(5);
		eAchieviment5.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/8B36B19036A345F7AA165614D9D8FE10");
		eAchieviment5.setId(5);
		eAchieviment5.setName("我宣你！");
		eAchieviment5.setType("软件综合");
		achievements.add(eAchieviment5);
		EAchievement eAchieviment6=new EAchievement();
		eAchieviment6.setAddition("获得一个互相关注");
		eAchieviment6.setDescription("");
		eAchieviment6.setExp(5);
		eAchieviment6.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
		eAchieviment6.setId(6);
		eAchieviment6.setName("友人");
		eAchieviment6.setType("软件综合");
		achievements.add(eAchieviment6);
		EAchievement eAchieviment7=new EAchievement();
		eAchieviment7.setAddition("成就等级达到“新兵”");
		eAchieviment7.setDescription("");
		eAchieviment7.setExp(10);
		eAchieviment7.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
		eAchieviment7.setId(7);
		eAchieviment7.setName("初出茅庐");
		eAchieviment7.setType("软件综合");
		achievements.add(eAchieviment7);
		EAchievement eAchieviment8=new EAchievement();
		eAchieviment8.setAddition("发布的邀请得到了别人的回复");
		eAchieviment8.setDescription("");
		eAchieviment8.setExp(10);
		eAchieviment8.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
		eAchieviment8.setId(8);
		eAchieviment8.setName("你也喜欢这个？");
		eAchieviment8.setType("软件综合");
		achievements.add(eAchieviment8);
		return achievements;
	}
	
	public EAchievement getEAchievementById(int id)
	{
		List<EAchievement> achievements=getEAchievements(null);
		for (EAchievement eAchievement : achievements) {
			if(eAchievement.getId()==id){
				return eAchievement;
			}
		}
		return null;
	}
	
	public	List<EAchievement> getEAchievementsByPersonId(int personId)
	{
		List<EAchievement> achievements=new ArrayList<EAchievement>();
		return achievements;
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
//		achievements.add(ac);
		return achievements;
	}
	
	public List<EAchievement> getRecommendEAchievements(int personId,int num) {
		
		return getEAchievements(null).subList(0, num);
	}
	
	public List<EAchievement> getRelativeAchievements(int achievementId){
		List<EAchievement> resultList=new ArrayList<EAchievement>();
		resultList=getEAchievements(null);
		return resultList;
	}
	
	public List<EAchievement> getEAchievementsByType(int person,String type){
		List<EAchievement> achievements = new ArrayList<EAchievement>();
		switch (type) {
		case "社交类":
//			for(int i = 0 ;i < 10;i++)
//			{
//				EAchievement ac = new EAchievement();
//				ac.setDescription("这里是成就 的具体说明这");
//				ac.setExp(10);
//				ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
//				ac.setId(i);
//				ac.setName("社交"+i);
//				ac.setType("社交类");
//				achievements.add(ac);
//			}
//			break;
		case "娱乐类":
//			for(int i = 0 ;i < 7;i++)
//			{
//				EAchievement ac = new EAchievement();
//				ac.setDescription("这里是成就 的具体说明这");
//				ac.setExp(10);
//				ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
//				ac.setId(i);
//				ac.setName("娱乐"+i);
//				ac.setType("娱乐类");
//				achievements.add(ac);
//			}
//			break;
		case "竞技类":
//			for(int i = 0 ;i < 12;i++)
//			{
//				EAchievement ac = new EAchievement();
//				ac.setDescription("这里是成就 的具体说明这");
//				ac.setExp(10);
//				ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
//				ac.setId(i);
//				ac.setName("竞技"+i);
//				ac.setType("person");
//				achievements.add(ac);
//			}
//			break;
		case "探索类":
//			for(int i = 0 ;i < 10;i++)
//			{
//				EAchievement ac = new EAchievement();
//				ac.setDescription("这里是成就 的具体说明这");
//				ac.setExp(10);
//				ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
//				ac.setId(i);
//				ac.setName("探索"+i);
//				ac.setType("探索类");
//				achievements.add(ac);
//			}
//			break;

		case "综合类":
//			for(int i = 0 ;i < 10;i++)
//			{
//				EAchievement ac = new EAchievement();
//				ac.setDescription("这里是成就 的具体说明这");
//				ac.setExp(10);
//				ac.setIcon("http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
//				ac.setId(i);
//				ac.setName("综合"+i);
//				ac.setType("综合类");
//				achievements.add(ac);
//			}
			achievements=getEAchievements(null);
			break;
		}
		return achievements;
	}
}
