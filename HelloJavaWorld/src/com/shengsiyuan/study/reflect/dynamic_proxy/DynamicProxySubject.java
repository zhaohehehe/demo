package com.shengsiyuan.study.reflect.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxySubject implements InvocationHandler {

	/**
	 * private RealSubject realSub;只能代理RealSubject，换成Object可以代理任意类
	 */
	private Object obj;
	public DynamicProxySubject(Object obj) {

		this.obj=obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Pre request!");
		
		System.out.println(args==null);
		method.invoke(obj, args);
		
		System.out.println("Post request!");
		return null;
	}
	/**
	 * 在真实角色操作之前，代理角色附加功能
	 */
	private void preRequest(){
		System.out.println("ProxySubject Pre Request!");
	}
	/**
	 * 在真实角色操作之后，代理角色附加功能
	 */
	private void postRequest(){
		System.out.println("ProxySubject Post Request!");
	}

}
