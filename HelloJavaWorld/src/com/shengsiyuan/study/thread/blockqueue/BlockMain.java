package com.shengsiyuan.study.thread.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockMain {
	public static void main(String[] args) {
		BlockingQueue<String> bq=new ArrayBlockingQueue<>(1);
		new ProducerThread(bq).start();
		new ProducerThread(bq).start();
		new ProducerThread(bq).start();
		new ConsumerThread(bq).start();
	}
}
