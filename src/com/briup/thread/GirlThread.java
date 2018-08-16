package com.briup.thread;

public class GirlThread implements Runnable{
   private Account account;

public GirlThread() {
}

public GirlThread(Account account) {
	
	this.account = account;
}

@Override
public void run() {
	while(true){
		int am=(int)(Math.random()*1000);
		account.withdrow(am);
		try {
			Thread.currentThread().sleep(am);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
   
	
}
