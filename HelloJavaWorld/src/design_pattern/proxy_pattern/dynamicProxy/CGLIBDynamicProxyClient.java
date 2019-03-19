package com.zhaohe.demo.test;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLIBDynamicProxyClient {
	public static void main(String[] args) {
		Subject1 realSubject1 = new RealSubject1();

		MyMethodInterceptor methodInterceptor = new MyMethodInterceptor();
		Subject1 subject1 = (Subject1) methodInterceptor.newProxy(realSubject1);
		// 官场 final方法不能被代理
		subject1.request1();
		System.out.println("==========================");
		subject1.request1Final();
	}
}

class MyMethodInterceptor implements MethodInterceptor {

	private void preRequest() {
		System.out.println("ProxySubject Pre Request!");
	}

	private void postRequest() {
		System.out.println("ProxySubject Post Request!");
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		this.preRequest();
		Object result = proxy.invokeSuper(obj, args);
		this.postRequest();
		return result;
	}

	// 创建代理类实例
	public Object newProxy(Object target) {
		Enhancer enhancer = new Enhancer();
		/**
		 * CGLIB则不能对final的方法生成代理，因为CGLib原理是动态生成被代理类的子类，final不能被继承
		 */
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(new MyMethodInterceptor());
		return enhancer.create();
	}

}
