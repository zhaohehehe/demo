package com.shengsiyuan.study.thread.control;

public class JoinTest extends Thread{
	public JoinTest(String name){
		super(name);
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(this.getName()+" "+i);
		}
	}
	public static void main(String[] args) throws InterruptedException {
		new JoinTest("新线程").start();
		for(int i=0;i<10;i++){
			if(i==3){
				JoinTest jt=new JoinTest("被join的线程");
				jt.start();
				//join会使得被join的线程执行结束后才会执行其他线程
				jt.join();
				//jt.join(1000);
				//jt.join(1000, 10000);
			}
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
	}
}























