package com.shengsiyuan.study.thread2;
//同步线程
public class SynchronicedThread {
	/**
	 * 银行预留100元，每个进程存10次，每次10元，共100元，所以如果没有同步机制的话，最后结果是200元，存的数据会被覆盖掉；
	 * 而加入同步机制后，进程之间的数据操作不会被覆盖，即最后银行账户的存款金额为300元，而不是200元。
	 * 同样如果是一个进程存钱，另一个进程取同样的钱，那么最后结果应该是100元不变。
	 * @author zhaohe
	 *
	 */
	//1.银行内部类
	class Bank{
		volatile private int account=100;
		public int getAccount(){
			return account;
		}
		//同步方法,synchronized和volatile只保留一个即可
		/*public synchronized void saveByMethod(int money){
			account+=money;
			}*/
		//同步方法
		public  void saveByMethod(int money){
			account+=money;
		}
		//同步代码块
		public void saveByBlock(int money){
			synchronized(this){
				account+=money;
			}
		}
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
				//bank.saveByMethod(10);//结果有序，不重复
				bank.saveByBlock(10);//结果无序，可以重复
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
		SynchronicedThread st=new SynchronicedThread();
		st.useThread();
	}
}
