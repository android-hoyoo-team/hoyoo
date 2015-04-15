package com.huyoo.global;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
/**
 * 注册了成就相关的事件，以便监听。
 * @author XF
 *
 */
public class Achievement {
	/**
	 * 注册登录事件，当登录的时候，会触发相应的监听。
	 */
	public static void login(){
		DispatchEvent.dispatchEvent(new DEvent("login", "message"));
	}
	public static void enterAchievement(){
		DispatchEvent.dispatchEvent(new DEvent("enterAchievement", "message"));
	}
	public static void uploadHeader(){
		DispatchEvent.dispatchEvent(new DEvent("uploadHeader", "message"));
	}
	public static void joinInvitation() {
		DispatchEvent.dispatchEvent(new DEvent("joinInvitation", "message"));
	}
	public static void payAttention() {
		DispatchEvent.dispatchEvent(new DEvent("payAttention", "message"));
	}
	public static void haveFriend() {
		DispatchEvent.dispatchEvent(new DEvent("haveFriend", "message"));
	}
}
