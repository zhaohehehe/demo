package com.shengsiyuan.study.thread2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//同步线程
/*如果使用ThreadLocal管理变量，则每一个使用该变量的线程都获得该变量的副本，  
副本之间相互独立，这样每一个线程都可以随意修改自己的变量副本，而不会对其他线程产生影响。*/ 
public class SynchronizedThreadLocalVariable {
	//1.银行内部类
	class Bank{
		private ThreadLocal<Integer> account=new ThreadLocal<Integer>(){
			protected Integer initialValue(){
				return 100;
			}
		};
		public int getAccount(){
			return account.get();
		}
		//同步方法,synchronized和volatile只保留一个即可
		/*public synchronized void saveByMethod(int money){
			account+=money;
			}*/
		//同步方法
		public void saveByMethod(int money){
			account.set(account.get()+money);
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
		SynchronizedThreadLocalVariable st=new SynchronizedThreadLocalVariable();
		st.useThread();
	}
}
