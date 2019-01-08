package com.shengsiyuan.study.thread.control;

public class YieldTest extends Thread{
	public static void main(String[] args) {
		YieldTest yt1=new YieldTest("¸ß¼¶");
		yt1.setPriority(MAX_PRIORITY);
		yt1.start();
		YieldTest yt2=new YieldTest("µÍ¼¶");
		yt2.setPriority(MIN_PRIORITY);
		yt2.start();
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(getName()+" "+i);
			if(i==3){
				Thread.yield();
			}
		}
	}
	public YieldTest(String name){
		super(name);
	}
}
