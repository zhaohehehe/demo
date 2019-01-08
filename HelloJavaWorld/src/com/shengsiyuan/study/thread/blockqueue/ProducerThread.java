package com.shengsiyuan.study.thread.blockqueue;

import java.util.concurrent.BlockingQueue;

public class ProducerThread extends Thread {
	@Override
	public void run() {
		String[] strArr=new String[]{
				"JAVA","Struts","Spring"
		};
		for(int i=0;i<100;i++){
			System.out.println(getName()+"生产者准备生产集合元素");
			try{
				Thread.sleep(200);
				bq.put(strArr[i%3]);//如果队列已经满，阻塞
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(getName()+"生产完成"+bq);
		}
		
	}
	private BlockingQueue<String> bq;

	public ProducerThread(BlockingQueue<String> bq) {
		super();
		this.bq = bq;
	}
}
