package com.shengsiyuan.study.thread.session;

public class DrawThread extends Thread {
	private Account account;
	private double drawMoney;
	public DrawThread(Account account, double drawMoney, String name) {
		super(name);
		this.account = account;
		this.drawMoney = drawMoney;
	}
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			account.draw(drawMoney);
		}
	}
	
}
