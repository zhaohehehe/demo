package com.shengsiyuan.study.set;

import java.util.HashSet;

public class HashSetTest01 {

	public static void main(String[] args) {
		HashSet<Object> set=new HashSet<Object>();
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
		System.out.println(set.add("e"));
		System.out.println(set.add("a"));
		System.out.println(set);
	}

}
