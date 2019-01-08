package com.shengsiyuan.study.reflect;

import java.lang.reflect.Method;

public class ReflectDumpClassTest01 {

	public static void main(String[] args) throws Exception {
		//?代表泛型extands Object
		//获得类所对应的Class对象方式一
		Class<?> classType=Class.forName("java.lang.String");
		//获得类所对应的Class对象方式二
		Class<?> classType1=ReflectDumpClassTest01.class;
		//获得方法
		Method[] methods=classType.getDeclaredMethods();
		for(Method method:methods){
			System.out.println(method);
		}
		
		
	}
}
