package com.shengsiyuan.study.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class CollectionsUtilTest01 {

	public static void main(String[] args) {
		//ArrayList<Integer> list=new ArrayList<Integer>();
		LinkedList<Integer> list=new LinkedList<Integer>();
		list.add(new Integer(20));
		list.add(new Integer(-20));
		list.add(new Integer(-30));
		list.add(new Integer(90));
		System.out.println(list);//20, -20, -30, 90
		
		Comparator<Integer> com=Collections.reverseOrder();
		Collections.sort(list, com);//90, 20, -20, -30
		
		//Collections.sort(list);//-30, -20, 20, 90
		System.out.println(list);
		//¥Ú¬“À≥–Ú
		Collections.shuffle(list);
		System.out.println(list);
		
	}

}
