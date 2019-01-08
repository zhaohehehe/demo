package com.shengsiyuan.study.thread.control;

/**
 * 如果前台线程都死亡，后台线程会自动死亡
 * 后台线程创建的子线程默认是后台线程
 * @author zhaohe
 *
 */
public class DaemonTest extends Thread{
	public static void main(String[] args) {
		DaemonTest t=new DaemonTest();
		System.out.println(t.isDaemon());
		t.setDaemon(true);//设置成后台线程
		t.start();
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
		//程序执行到该处，前台线程结束
		//后台线程也随之结束
	}
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println(getName()+" "+i);
		}
	}

}
