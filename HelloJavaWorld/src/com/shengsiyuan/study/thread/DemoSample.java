package com.shengsiyuan.study.thread;

public class DemoSample {

	private int number;
	public synchronized void increase(){
		//一定要用while循环，if只能判断一次，只适用于2个线程
		while(0!=number){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		number++;
		System.out.println(number);
		notify();
	}
	public synchronized void decrease(){
		//一定要用while循环，if只能判断一次，只适用于2个线程
		while(0==number){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		number--;
		System.out.println(number);
		notify();
	}
	

}
