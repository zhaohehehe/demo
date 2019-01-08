package com.shengsiyuan.study.thread.session;

public class AccountMain {
	/**
	 * 程序会出现阻塞，因为能存钱300次，取钱只能是100次，所以出现程序阻塞，但并不是死锁
	 * @param args
	 */
	public static void main(String[] args) {
		Account act=new  Account();
		new DrawThread(act, 100, "取钱者").start();
		new DepositThread(act, 100, "存钱者甲").start();
		new DepositThread(act, 100, "存钱者乙").start();
		new DepositThread(act, 100, "存钱者丙").start();
		
	}
}
