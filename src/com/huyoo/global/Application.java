package com.huyoo.global;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huyoo.bean.LoginInfo;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.RPersonAchievement;
import com.huyoo.service.EAchievementService;
import com.huyoo.service.EArticleService;
import com.huyoo.service.ECommentService;
import com.huyoo.service.EInvitationService;
import com.huyoo.service.ELevelService;
//import com.huyoo.service.EMessageService;
import com.huyoo.service.EPersonService;
import com.huyoo.service.EUnionService;

/**
 *所有服务的单例类
 *
 */
public class Application {

	private static EPersonService personService;
	private static EAchievementService achievementService;
	private static ECommentService commentService;
	private static EUnionService unionService;
	private static ELevelService levelService;
	//	private static EMessageService messageService;
	private static EInvitationService invitationService;
	private static LoginInfo loginInfo;

	private static EArticleService articleService;
	private static DatabaseHelper databaseHelper;

	private static AchievementDispatcher achievementDispatcher;

	/**
	 * 获取EUnionService单例
	 * 
	 * @return
	 */
	public static EUnionService getUnionService() {
		if(unionService==null)
			unionService=new EUnionService();
		return unionService;
	}
	public static ELevelService getLevelService() {
		if(levelService==null)
			levelService = new ELevelService();
		return levelService;
	}
	/**
	 * 获取EPersonService单例
	 * 
	 * @return
	 */
	public static EPersonService getPersonService() {
		if (personService == null)
			personService = new EPersonService();
		return personService;
	}

	/**
	 * 获取EAchievementService 单例
	 * @return
	 */
	public static EAchievementService getAchievementService() {
		if (achievementService == null)
			achievementService = new EAchievementService();
		return achievementService;
	}

	/**
	 * 获取ECommentService 单例
	 * @return
	 */
	public static ECommentService getCommentService() {
		if (commentService == null)
			commentService = new ECommentService();
		return commentService;
	}

	//	public static EMessageService getMessageService() {
	//		if (messageService == null)
	//			messageService = new EMessageService();
	//		return messageService;
	//	}

	/**
	 * 获取EInvitationService 单例
	 * @return
	 */
	public static EInvitationService getInvitationService() {
		if (invitationService == null)
			invitationService = new EInvitationService();
		return invitationService;
	}
	/**
	 * 获取EArticleService单例
	 * @return
	 */
	public static EArticleService getArticleSercice(){
		if(articleService==null)
			articleService=new EArticleService();
		return articleService;
	}

	/**
	 * 根据用户信息登陆
	 * 
	 * @param reLogin
	 *            如果true则重新登陆，如果false：loginInfo不为空则返回且不重新登陆，否则从新登陆过
	 * @return
	 */
	public static LoginInfo login(Map<String, Object> param,
			boolean reLogin) {
		if (reLogin || loginInfo == null) {
			/*
			 * 连接服务器获取用户信息
			 */
			return _login(param);
		}
		return loginInfo;
	}

	/**
	 * 获取用户登录信息
	 * @return
	 */
	public static LoginInfo getLoginInfo() {
		//		Map<String,Object> map = new HashMap<String, Object>();
		//		map.put("phoneNum", "18888888888");
		//	return login(null, false);
		return loginInfo;
	}
	/**
	 * 根据用户号码登陆，如果用户已经登陆则返回已登录信息，如果用户没有登陆则执行登陆操作后返回登陆信息
	 * 
	 * @param PhoneNum
	 *            用户电话号码
	 * @return
	 */
	public static void login(String phoneNum) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("phoneNum", phoneNum);
		//loginInfo.setPerson(getPersonService().getEPersonByPhoneNum(phoneNum));
		LoginInfo lInfo = new LoginInfo();
		EPerson person = getPersonService().getEPersonByPhoneNum(phoneNum);
		lInfo.setPerson(person);
		loginInfo = lInfo;
		//return loginInfo;
		//return login(p, false);
	}

	public static LoginInfo _login(Map<String, Object> param) {
		List<EPerson> persons = getPersonService().getPersons(param);
		if (persons.size() > 0) {
			LoginInfo lInfo = new LoginInfo();
			lInfo.setPerson(persons.get(0));
			loginInfo = lInfo;
			return loginInfo;
		}
		return null;
	}
	public static DatabaseHelper getDatabaseHelper() {
		return databaseHelper;
	}
	public static void setDatabaseHelper(DatabaseHelper databaseHelper) {
		Application.databaseHelper = databaseHelper;
	}
	public static AchievementDispatcher getAchievementDispatcher(){
		if(achievementDispatcher==null){
			achievementDispatcher = new AchievementDispatcher();
		}
		return achievementDispatcher;
	}
}
