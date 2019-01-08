package com.shengsiyuan.study.reflect;

public class SuperClassTest01 {

	public static void main(String[] args) {
		Class<?> classType=Children.class;
		System.out.println(classType);
		Class<?> classType1=classType.getSuperclass();
		System.out.println(classType1);
		System.out.println(classType1.getSuperclass());
		System.out.println(classType1.getSuperclass().getSuperclass());
	}

}
class Parent{
	String name;
}
class Children extends Parent{
	
}