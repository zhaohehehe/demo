package com.shengsiyuan.study.thread.threadgroup;

public class ThreadGroupTest{
	public static void main(String[] args) {
		ThreadGroup mainGroup=Thread.currentThread().getThreadGroup();
		System.out.println(mainGroup.isDaemon());
		new MyThreadGroup("主线程组的线程").start();
		ThreadGroup tg=new ThreadGroup("新线程组");
		tg.setDaemon(true);
		MyThreadGroup tt=new MyThreadGroup(tg,"tg租的线程甲");
		tt.start();
		new MyThreadGroup(tg, "tg租的线程乙").start();
	}
}

class MyThreadGroup extends Thread{
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(getName()+"线程的i变量"+i);
		}
	}

	public MyThreadGroup(ThreadGroup group, String name) {
		super(group, name);
	}
	public MyThreadGroup(String name) {
		super(name);
	} 
	
}
