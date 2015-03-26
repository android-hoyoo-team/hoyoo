package com.huyoo.global;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;

public class Achievement {
	public static void login(){
		DispatchEvent.dispatchEvent(new DEvent("login", "message"));
	}
	public static void enterAchievement(){
		DispatchEvent.dispatchEvent(new DEvent("enterAchievement", "message"));
	}
	public static void uploadHeader(){
		DispatchEvent.dispatchEvent(new DEvent("uploadHeader", "message"));
	}
}
