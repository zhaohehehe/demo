package com.shengsiyuan.study.thread;

public class DemoIncreasedThread extends Thread{

	private DemoSample sample;
	public DemoIncreasedThread(DemoSample ds){
		this.sample=ds;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			try {
				Thread.sleep((long) (Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sample.increase();
		}
	}
}
