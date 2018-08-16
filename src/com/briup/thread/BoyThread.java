package com.briup.thread;

public class BoyThread implements Runnable {
    private Account account;
    
	public BoyThread() {
	}

	public BoyThread(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		while(true){
			int am=(int)(Math.random()*1000);
			account.deposite(am);
			try {
				Thread.currentThread().sleep(am*5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}
