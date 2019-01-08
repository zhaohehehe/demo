package com.shengsiyuan.study.thread.exhandler;
class MyExHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		System.out.println(t +"线程出现了异常："+e);
		
	}
	
}
public class ExHandler {
	public static void main(String[] args) {
		//设置主线程的异常处理器
		Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());
		int a=5/0;
		System.out.println("程序正常结束");
	}
}
