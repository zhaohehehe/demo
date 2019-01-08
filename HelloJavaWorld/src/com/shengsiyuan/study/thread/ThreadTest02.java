package com.shengsiyuan.study.thread;

public class ThreadTest02 {

	public static void main(String[] args) {
		/*Thread t1=new Thread(new Runnable(){
			public void run() {
				for(int i=0;i<100;i++){
					System.out.println("Hello:"+i);
				}
			}
		});
		t1.start();*/
		Thread t2=new Thread(new MyThread1());
		t2.start();
		Thread t3=new Thread(new MyThread2());
		t3.start();
	}

}
class MyThread1 implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println("Hello:"+i);
		}
	}
	
}
class MyThread2 implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println("Welcome:"+i);
		}
	}
	
}
