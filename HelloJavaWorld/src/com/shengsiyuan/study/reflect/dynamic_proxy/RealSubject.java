package com.shengsiyuan.study.reflect.dynamic_proxy;

public class RealSubject implements Subject {

	@Override
	public void request() {

		System.out.println("Real Subjct Request!");
	}

}
