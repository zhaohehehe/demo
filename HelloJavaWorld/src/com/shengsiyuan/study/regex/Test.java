package com.shengsiyuan.study.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		Pattern p=Pattern.compile("a*b");
		Matcher ma=p.matcher("aaabab");
		while(ma.find()){
			System.out.println(ma.group(0));
		}
		//System.out.println(ma.matches());
		System.out.println();
	}
}
