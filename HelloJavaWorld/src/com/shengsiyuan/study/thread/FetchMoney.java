package com.shengsiyuan.study.thread;

public class FetchMoney {

	public static void main(String[] args) {
		Bank bank=new Bank();
		//柜台
		MoneyThread mt1=new MoneyThread(bank);
	   // bank=new Bank();
		//提款机
		MoneyThread mt2=new MoneyThread(bank);
		mt1.start();
		mt2.start();
	}

}
class Bank{
	private int money=1000;
	public synchronized int getMoney(int number){
		if(number<0){
			return -1;
		}
		else if(number>money){
			return -2;
		}
		else if(money<0){
			return -3;
		}
		else{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			money-=number;
			System.out.println("left money:"+money);
			return number;
		}
	}
} 
class MoneyThread extends Thread{
	private Bank bank;
	public MoneyThread(Bank bank) {

		this.bank=bank;
	}
	@Override
	public void run() {

		System.out.println(bank.getMoney(600));
	}
}






















