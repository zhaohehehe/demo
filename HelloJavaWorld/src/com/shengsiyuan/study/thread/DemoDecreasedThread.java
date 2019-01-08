package com.shengsiyuan.study.thread;

public class DemoDecreasedThread extends Thread{

	private DemoSample sample;
	public DemoDecreasedThread(DemoSample ds){
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
			sample.decrease();
		}
	}
}
