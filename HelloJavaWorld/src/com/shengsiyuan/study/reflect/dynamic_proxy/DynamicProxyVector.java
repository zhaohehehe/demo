package com.shengsiyuan.study.reflect.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Vector;

public class DynamicProxyVector implements InvocationHandler {

	private Object obj;
	public DynamicProxyVector(Object obj) {

		this.obj=obj;
	}
	
	private static Object factory(Object obj){
		Class<?> classType=obj.getClass();
		//为什么不是new DynamicProxyVector(obj).getClass().getClassLoader();
		return Proxy.newProxyInstance(classType.getClassLoader(),
				classType.getInterfaces(),new DynamicProxyVector(obj));
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("before calling:"+method);
		if(args!=null){
			for(Object obj:args){
				System.out.println(obj);
			}
		}
		Object objRetrun=method.invoke(obj, args);
		System.out.println("after calling"+method);
		return objRetrun;
	}
	public static void main(String[] args) {
		System.out.println("----可以强制转换，因为Vector实现了List接口----");
		//可以强制转换，因为Vector实现了List接口
		List<String> list=(List<String>) factory(new Vector());
		list.add("New");
		list.add("York");
		System.out.println("------打印list会调用toString()方法，进入invoke(),没有参数---------------");
		//打印list会调用toString()方法，进入invoke(),没有参数
		System.out.println(list);
		System.out.println("-----删除index=1后,再打印--------------");
		//删除
		list.remove(1);
		System.out.println(list);
	}

}
