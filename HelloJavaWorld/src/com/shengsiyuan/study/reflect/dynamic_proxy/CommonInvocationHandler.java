package com.shengsiyuan.study.reflect.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CommonInvocationHandler implements InvocationHandler {
	
	private Object target;
	
	public CommonInvocationHandler(Object obj) {

		this.target=obj;
	}

	public CommonInvocationHandler() {

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		method.invoke(target, args);
		return null;
	}
	
	public void setTarget(Object target) {
		this.target = target;
	}


}
