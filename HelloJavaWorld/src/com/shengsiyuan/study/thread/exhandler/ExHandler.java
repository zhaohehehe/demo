package com.shengsiyuan.study.thread.exhandler;
class MyExHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		System.out.println(t +"�̳߳������쳣��"+e);
		
	}
	
}
public class ExHandler {
	public static void main(String[] args) {
		//�������̵߳��쳣������
		Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());
		int a=5/0;
		System.out.println("������������");
	}
}