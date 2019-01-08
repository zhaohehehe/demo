package com.shengsiyuan.study.thread2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.monitor.Monitor;

//同步线程
public class SynchronizedThreadReentrantLock {
	//1.银行内部类
	class Bank{
		 private int account=100;
		 private Lock lock=new ReentrantLock();
		public int getAccount(){
			return account;
		}
		//同步方法,synchronized和volatile只保留一个即可
		/*public synchronized void saveByMethod(int money){
			account+=money;
			}*/
		//同步方法
		public void saveByMethod(int money){
			lock.lock();
			try{
			account+=money;
			}finally{
				lock.unlock();
			}
		}
		/*//同步代码块
		public void saveByBlock(int money){
			synchronized(this){
				account+=money;
			}
		}*/
	}
	//2.线程类
	class NewThread implements Runnable{
		private Bank bank;
		public NewThread(Bank bank){
			this.bank=bank;
		}
		@Override
		public void run() {
			for(int i=0;i<10;i++){
				bank.saveByMethod(10);//结果有序，不重复
				//bank.saveByBlock(10);//结果无序，可以重复
				System.out.println(i+"账户余额为："+bank.getAccount());
			}
		}
	}
	//3.构建线程，调用内部类
	public void useThread(){
		Bank bank=new Bank();
		NewThread new_thread=new NewThread(bank);
		System.out.println("线程1");
		//new_thread.start();//error，按下面方式start
		Thread thread1=new Thread(new_thread);
		thread1.start();
		System.out.println("线程2");
		//new_thread.start();//error，按下面方式start
		Thread thread2=new Thread(new_thread);
		thread2.start();		
	}
	public static void main(String[] args) {
		SynchronizedThreadReentrantLock st=new SynchronizedThreadReentrantLock();
		st.useThread();
	}
}
