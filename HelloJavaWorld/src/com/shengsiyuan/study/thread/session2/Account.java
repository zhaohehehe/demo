package com.shengsiyuan.study.thread.session2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	private final Lock lock=new ReentrantLock();
	private final Condition condition=lock.newCondition();
	public void deposit(double depositAmout){
		lock.lock();
		try{
			if(flag){
				condition.await();
			}else{
				System.out.println(Thread.currentThread().getName()+"¥Ê«Æ£∫"+depositAmout);
				balance+=depositAmout;
				System.out.println("’Àªß”‡∂ÓŒ™£∫"+balance);
				flag=true;
				condition.signalAll();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void draw(double drawAmout){
		lock.lock();
		try{
			if(!flag){
				condition.await();
			}else{
				System.out.println(Thread.currentThread().getName()+"»°«Æ£∫"+drawAmout);
				balance-=drawAmout;
				System.out.println("’Àªß”‡∂ÓŒ™£∫"+balance);
				flag=false;
				condition.signalAll();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	private String accoutNo;
	private double balance;
	private boolean flag=false;
	public Account(){}
	public Account(String accoutNo, double balance) {
		super();
		this.accoutNo = accoutNo;
		this.balance = balance;
	}
	public String getAccoutNo() {
		return accoutNo;
	}
	public void setAccoutNo(String accoutNo) {
		this.accoutNo = accoutNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	

}
