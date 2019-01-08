package com.shengsiyuan.study.thread.session;





public class Account {
	public synchronized void deposit(double depositAmout){
		try{
			if(flag){
				this.wait();
			}else{
				System.out.println(Thread.currentThread().getName()+"¥Ê«Æ£∫"+depositAmout);
				balance+=depositAmout;
				System.out.println("’Àªß”‡∂ÓŒ™£∫"+balance);
				flag=true;
				this.notifyAll();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public synchronized void draw(double drawAmout){
		try{
			if(!flag){
				this.wait();
			}else{
				System.out.println(Thread.currentThread().getName()+"»°«Æ£∫"+drawAmout);
				balance-=drawAmout;
				System.out.println("’Àªß”‡∂ÓŒ™£∫"+balance);
				flag=false;
				this.notifyAll();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
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
