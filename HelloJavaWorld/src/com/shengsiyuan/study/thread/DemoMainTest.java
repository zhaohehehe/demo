package com.shengsiyuan.study.thread;

public class DemoMainTest {

	public static void main(String[] args) {
		DemoSample ds=new DemoSample();
		Thread t1=new DemoIncreasedThread(ds);
		Thread t2=new DemoDecreasedThread(ds);
		Thread t3=new DemoIncreasedThread(ds);
		Thread t4=new DemoDecreasedThread(ds);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
