package com.shengsiyuan.study.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AccessPrivateField {

	public static void main(String[] args) throws Exception {
		Private2 p=new Private2();
		Class<?> classType=p.getClass();
		Field field=classType.getDeclaredField("name");
		String fieldName=field.getName();
		String firstLetter=fieldName.substring(0, 1).toUpperCase();
		field.setAccessible(true);
		field.set(p, "jerry");
		
		//System.out.println(p.getName());
		
		Method getMethod=classType.getMethod("get"+firstLetter+fieldName.substring(1));
		String result=(String) getMethod.invoke(p, new Object[]{});
		System.out.println(result);
		
	}

}
class Private2{
	private String name="tom";

	public String getName() {
		return name;
	}
}
