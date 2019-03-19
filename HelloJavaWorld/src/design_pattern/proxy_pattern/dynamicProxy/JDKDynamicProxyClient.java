package com.zhaohe.demo.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicProxyClient {

	public static void main(String[] args) throws Exception {
		Subject1 javaProxy1 = new RealSubject1();
		Subject2 javaProxy2 = new RealSubject2();

		Subject1 newJavaProxy1 = (Subject1) Proxy.newProxyInstance(JDKDynamicProxyClient.class.getClassLoader(),
				new Class[] { Subject1.class }, new MyInvocationHandler(javaProxy1));
		newJavaProxy1.request1();
		System.out.println("=============================================");
		newJavaProxy1.request1Final();

		System.out.println("=============================================");

		Subject2 newJavaProxy2 = (Subject2) Proxy.newProxyInstance(JDKDynamicProxyClient.class.getClassLoader(),
				new Class[] { Subject2.class }, new MyInvocationHandler(javaProxy2));
		newJavaProxy2.request2();

	}

}

/**
 * JDK动态代理只能对实现了【接口】的类生成代理，而不能针对类
 * JDK动态代理可以对声明为final的方法进行代理，而CGLIB则不能，因为CGLib原理是动态生成被代理类的子类
 * 
 * @author ZH
 *
 */
class RealSubject1 implements Subject1 {

	@Override
	public void request1() {
		System.out.println("Real Subjct1 Request!");
	}

	@Override
	public final void request1Final() {
		System.out.println("Real Subjct1 Final Request!");

	}

}

class RealSubject2 implements Subject2 {
	@Override
	public void request2() {
		System.out.println("Real Subjct2 Request!");
	}

}

/**
 * InvocationHandler 的一个实现，实际上处理代理的逻辑在这里,例如对RealSubject1(被代理类)生成代理类
 * 静态代理代理类中只能维护一种Subject接口，如果有多种不同的Subject需要创建很多不同的代理类，动态代理可以解决这种弊端.
 * 例如如果针对Subject1和Subject2就要生成2种代理，而动态代理只需生成一个MyInvocationHandler即可
 * 
 * @author ZH
 *
 */
class MyInvocationHandler implements InvocationHandler {
	// 代理Subject
	private Object proxy;

	public MyInvocationHandler(Object proxy) {
		this.proxy = proxy;
	}

	private void preRequest() {
		System.out.println("ProxySubject Pre Request!");
	}

	private void postRequest() {
		System.out.println("ProxySubject Post Request!");
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		this.preRequest();
		System.out.println("代理类接口：" + proxy.getClass().getInterfaces()[0]);
		// 调用被代理类的真实方法
		Object obj = method.invoke(this.proxy, args);
		this.postRequest();
		return obj;
	}

}