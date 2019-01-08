package com.shengsiyuan.study.thread;

public class ThreadTest01 {

	public static void main(String[] args) {
		Thread1 t1=new Thread1("1");
		Thread2 t2=new Thread2("2");
		Thread2 t3=new Thread2("3");
		//维护一个静态成员变量Thread-number，每创建一个线程，+1
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		System.out.println(t3.getName());
		t1.start();
		t2.start();
		t3.start();
	}
	

}
class Thread1 extends Thread{
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println("Hello:"+i);
		}
	}
	public Thread1(String name){
		super(name);
	}
}

class Thread2 extends Thread{
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println("welcome:"+i);
		}
	}
	public Thread2(String name){
		super(name);
	}
}
