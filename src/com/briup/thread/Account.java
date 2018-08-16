package com.briup.thread;

public class Account {

	private int balance;
	
	public Account() {
	}
	public Account(int balance) {
		this.balance = balance;
	}
	//花钱
    public synchronized void withdrow(int money){
    	if(money>balance){
    		System.out.println(Thread.currentThread().getName()+"消费："+money+"余额："+balance+"少花点");
    	try {
			wait();//被放到了等待池 释放锁
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}else {
			balance-=money;
			System.out.println(Thread.currentThread().getName()+"消费："+money+"余额:"+balance);
		}
    }
    //存钱
    public synchronized void deposite(int money){
    	 balance+=money;
    	 System.out.println(Thread.currentThread().getName()+"存钱："+money+"余额："+balance);
    	 notify();//通知存钱完毕 
        //	 notifyAll();
    	}
}
