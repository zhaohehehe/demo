package com.zhaohe.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//http://www.java2s.com
public class DateFormater {
	public static Date strToDate(String source) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		java.util.Date date = new java.util.Date(sdf.parse(source).getTime());
		return date;
	}

	public static String dateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		return sdf.format(date);
	}
	public static void main(String[] args) {
		try {
			System.out.println(new Date());
			System.out.println(DateFormater.dateToStr(new Date()));
			System.out.println(DateFormater.strToDate("Sat Aug 19 19:39:54 CST 2017"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

