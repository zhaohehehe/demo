package com.shengsiyuan.study.thread.blockqueue;

import java.util.concurrent.BlockingQueue;

public class ConsumerThread extends Thread {
	@Override
	public void run() {
		while(true){
			System.out.println(getName()+"生消费者准备消费集合元素");
			try{
				Thread.sleep(200);
				bq.take();//如果队列已经空，阻塞
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(getName()+"消费完成"+bq);
		}
		
	}
	private BlockingQueue<String> bq;

	public ConsumerThread(BlockingQueue<String> bq) {
		super();
		this.bq = bq;
	}
}
