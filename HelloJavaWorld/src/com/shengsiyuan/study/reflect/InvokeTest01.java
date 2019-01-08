package com.shengsiyuan.study.reflect;

import java.lang.reflect.Method;

public class InvokeTest01 {

	public int add(int a,int b){
		return a+b;
	}
	public String echo(String str){
		return "Hello"+str;
	}
	
	public static void main(String[] args) throws Exception {
		/*InvokeTest01 invoke=new InvokeTest01();
		System.out.println(invoke.add(2, 3));
		System.out.println(invoke.echo("zhaohe"));*/
		//获得类所对应的Class对象
		Class<?> classType=InvokeTest01.class;
		//创建Class对象所对应的实例
		Object invoke=classType.newInstance();
//		System.out.println(invoke instanceof InvokeTest01);
		//param:方法名，参数所对应的Class对象
		Method addMethod=classType.getMethod("add", new Class[]{int.class,int.class});
		//param:实例名，参数
		Object objAdd=addMethod.invoke(invoke, new Object[]{2,3} );
		System.out.println(objAdd);
		System.out.println("------------------------------");
		Method echoMethod=classType.getMethod("echo", String.class);
		Object objEcho=echoMethod.invoke(invoke, "zhaohe");
		System.out.println(objEcho);
		
		
	}
}
