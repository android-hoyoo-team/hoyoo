package com.huyoo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
	//公会成就，显示的时候在成就的首尾加上[]
	public static String FormatAchievement(String achievement){
		
		return "["+achievement+"]";
	}
}
