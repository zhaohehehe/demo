package com.shengsiyuan.study.reflect.dynamic_proxy;

import java.lang.reflect.Proxy;

public class FooClient {

	public static void main(String[] args) {
		CommonInvocationHandler handler=new CommonInvocationHandler();
		Foo f=null;
		handler.setTarget(new FooImp1());
		/*Proxy.newProxyInstance(Foo.class.getClassLoader(),
				f.getClass().getInterfaces(), handler);
		*/
		f=(Foo)Proxy.newProxyInstance(Foo.class.getClassLoader(),
				new Class[]{Foo.class}, handler);
		f.doAction();
		System.out.println("----重新动态生成真实对象,代理类没有增加,Proxy动态生成代理类--------");
		handler.setTarget(new FooImp2());
		
		f=(Foo)Proxy.newProxyInstance(Foo.class.getClassLoader(),
				new Class[]{Foo.class}, handler);
		f.doAction();
		
		
		
	}

}
