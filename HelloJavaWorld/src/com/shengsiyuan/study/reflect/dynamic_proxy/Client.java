package com.shengsiyuan.study.reflect.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {

	public static void main(String[] args) throws Exception {
		RealSubject realSub=new RealSubject();
		InvocationHandler h=new DynamicProxySubject(realSub);
		Class<?> classType=h.getClass();
		/**
		 * 动态代理类的实例
		 * 动态代理类加载（因为动态代理类实现了InvocationHandler接口）,实现真实类的接口,……
		 * Proxy类中getProxyClass方法返回的是Proxy的Class类
		 */
		Class<?> proxyClass=Proxy.getProxyClass(classType.getClassLoader(), realSub.getClass().getInterfaces());
		Subject subject0=(Subject) proxyClass.getConstructor(InvocationHandler.class).newInstance(h);
		subject0.request();
		System.out.println(subject0.getClass());//com.sun.proxy.$Proxy0
		
		Subject subject1=(Subject) Proxy.newProxyInstance(classType.getClassLoader(), realSub.getClass().getInterfaces(), h);
		subject1.request();
		
	/**
	 * 所以关联invoke()的过程是： 
	 * （1）得到Proxy类的Class对象：Proxy.getProxyClass，实现了真实对象的接口；
	 * （2）得到代理类的实例：com.sun.proxy.$Proxy0，运行期间动态生成
	 * （3）调用代理类实例的接口方法：如下源代码，调用$Proxy0.request()方法中调用了h.invoke(this, m3, null)方法，
	 * 	        在本例中，即为DynamicProxySubject的invoke()方法：
	 *     ：this为代理实例（即最后返回的subject）;
	 *     ：m3为 Class.forName("***.RealSubject").getMethod("request",new Class[0]);
	 *     ：args为参数，因为该例中的request()方法没有参数，此时的args就是等于null，可以在DynamicProxySubject.invok()中验证;
	 * 总结起来：$Proxy0通过调用InvocationHandler.invok()方法实现对RealSubject的操作；
	 *		 InvocationHandler.invok()方法的调用在调用$Proxy0.request()接口方法时执行。
	 */
	/* $Proxy0源代码，父类为Proxy
	 *  public final String toString() {
			 return (String) super.h.invoke(this, m2, null);
		}
	*/
		
	/*
		To create a proxy for some interface Foo: 

		     InvocationHandler handler = new MyInvocationHandler(...);
		     Class<?> proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(), Foo.class);
		     Foo f = (Foo) proxyClass.getConstructor(InvocationHandler.class).
		                     newInstance(handler);
		 
		or more simply: 
		     Foo f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
		                                          new Class<?>[] { Foo.class },
		                                          handler);
     */

		
	}
}
