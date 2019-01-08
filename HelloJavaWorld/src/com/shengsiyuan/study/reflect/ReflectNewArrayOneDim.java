package com.shengsiyuan.study.reflect;

import java.lang.reflect.Array;

public class ReflectNewArrayOneDim {

	public static void main(String[] args) throws Exception {
		
		Class<?> classType=Class.forName("java.lang.String");

		Object array=Array.newInstance(classType, 10);
		Array.set(array, 5, "hello");
		Array.set(array, 6, "world");
		int length=Array.getLength(array);
		for(int i=0;i<length;i++){
			System.out.println(Array.get(array, i));
		}
		
	}

}
