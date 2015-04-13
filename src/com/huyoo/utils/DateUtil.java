package com.huyoo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	   public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	    /**
		  * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
		  * @param str 字符串
		  * @param format 日期格式
		  * @return 日期
		  * @throws java.text.ParseException
		  */
		public static Date str2Date(String str){
			if (null == str || "".equals(str)) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
			Date date = null;
			try {
				date = sdf.parse(str);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
		 * @param str 字符串
		 * @param format 日期格式
		 * @return 日期
		 * @throws java.text.ParseException
		 */
		public static Date str2Date(String str,String format){
			if (null == str || "".equals(str)) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = null;
			try {
				date = sdf.parse(str);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}

		 /** 日期转换为字符串
		  * @param date 日期
		  * @param format 日期格式 "yyyy-MM-dd HH:mm:ss"
		  * @return 字符串
		  */
		public static String date2Str(Date date) {
			if (null == date) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
			return sdf.format(date);
		}
		public static String date2Str(Date date,String format) {
			if (null == date||format==null||"".equals(format.trim())) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		
		public static int getDays(Date start,Date end)
		{
			return (int) ((start.getTime()-end.getTime())/1000/60/60/24);
		}
		public static int getWorkingDays(Date start,boolean includeS,Date end,boolean includesE)
		{
			int tag=0;
			start.setHours(0);
			start.setMinutes(0);
			start.setSeconds(0);
			if(!includeS)
				start.setDate(start.getDate()+1);
			if(!includesE)
				end.setDate(end.getDate()-1);
			while(start.before(end))
			{
				if(0<start.getDay()&&start.getDay()<6)
				{
					tag++;
				}
				start.setDate(start.getDate()+1);
			}
			return tag;
		}
		public static String getTimeBeforeNow(long seconds){
			String time=null;
			seconds=seconds/1000;
			if(seconds<60)
				time="刚刚";
			else if(seconds>=60&&seconds<3600)
				time=(seconds/60)+"分钟前";
			else if(seconds>=3600&&seconds<(3600*24))
				time=(seconds/3600)+"小时前";
			else
				time=(seconds/3600/24)+"天前";
			return time;
		}
}
