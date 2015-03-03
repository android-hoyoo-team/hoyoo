package com.huyoo.global;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huyoo.bean.LoginInfo;
import com.huyoo.entity.EPerson;
import com.huyoo.service.EAchievementService;
import com.huyoo.service.ECommentService;
import com.huyoo.service.EInvitationService;
import com.huyoo.service.EPersonService;
import com.huyoo.service.EUnionService;

public class Application {

	private static EPersonService personService;
	private static EAchievementService achievementService;
	private static ECommentService commentService;
	private static EUnionService unionService;

	private static EInvitationService eInvitationService;
	private static LoginInfo loginInfo;

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

	/**
	 * 获取EInvitationService 单例
	 * @return
	 */
	public static EInvitationService getInvitationService() {
		if (eInvitationService == null)
			eInvitationService = new EInvitationService();
		return eInvitationService;
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
	 * 
	 * @param PhoneNum
	 *            用户电话号码
	 * @return
	 */
	public static LoginInfo getLoginInfo() {
		return login(null, false);
	}
	/**
	 * 根据用户号码登陆，如果用户已经登陆则返回已登录信息，如果用户没有登陆则执行登陆操作后返回登陆信息
	 * 
	 * @param PhoneNum
	 *            用户电话号码
	 * @return
	 */
	public static LoginInfo login(String PhoneNum) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("phoneNum", PhoneNum);
		return login(p, false);
	}

	private static LoginInfo _login(Map<String, Object> param) {
		List<EPerson> persons = getPersonService().getPersons(param);
		if (persons.size() > 0) {
			LoginInfo lInfo = new LoginInfo();
			lInfo.setPerson(persons.get(0));
			loginInfo = lInfo;
			return loginInfo;
		}
		return null;
	}
}
