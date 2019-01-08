package com.shengsiyuan.study.thread.session2;

public class DepositThread extends Thread {
	private Account account;
	private double drawMoney;
	public DepositThread(Account account, double drawMoney, String name) {
		super(name);
		this.account = account;
		this.drawMoney = drawMoney;
	}
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			account.deposit(drawMoney);
		}
	}
	
}
