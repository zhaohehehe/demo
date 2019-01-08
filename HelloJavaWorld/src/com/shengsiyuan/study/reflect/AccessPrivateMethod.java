package com.shengsiyuan.study.reflect;

import java.lang.reflect.Method;

public class AccessPrivateMethod {

	public static void main(String[] args) throws Exception {
		Private pri=new Private();
		
		Class<?> classType=pri.getClass();
		
		Method method=classType.getDeclaredMethod("sayHello", new Class[]{String.class});
		/**
		 * 由于访问private方法，会出现java.lang.IllegalAccessException
		 * 解决办法：压制Java的访问控制机制，添加如下代码
		 */
		method.setAccessible(true);
		
		String result=(String) method.invoke(pri, new Object[]{"Jerry"});
		
		System.out.println(result);
		
	}

}
class Private{
	private String sayHello(String str){
		return "Hello:"+str;
	}
}