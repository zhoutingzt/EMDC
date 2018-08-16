package com.briup.thread;

public class AccountTest {

	public static void main(String[] args) {
	Account account=new Account(1000);
	BoyThread boy=new BoyThread(account);
	GirlThread girl=new GirlThread(account);
	Thread t1=new Thread(boy,"tom");
	Thread t2=new Thread(girl,"rose");
	Thread t3=new Thread(girl,"lily");

	t1.start();
	t2.start();
	t3.start();
	}

}
