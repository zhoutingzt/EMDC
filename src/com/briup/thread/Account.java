package com.briup.thread;

public class Account {

	private int balance;
	
	public Account() {
	}
	public Account(int balance) {
		this.balance = balance;
	}
	//��Ǯ
    public synchronized void withdrow(int money){
    	if(money>balance){
    		System.out.println(Thread.currentThread().getName()+"���ѣ�"+money+"��"+balance+"�ٻ���");
    	try {
			wait();//���ŵ��˵ȴ��� �ͷ���
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}else {
			balance-=money;
			System.out.println(Thread.currentThread().getName()+"���ѣ�"+money+"���:"+balance);
		}
    }
    //��Ǯ
    public synchronized void deposite(int money){
    	 balance+=money;
    	 System.out.println(Thread.currentThread().getName()+"��Ǯ��"+money+"��"+balance);
    	 notify();//֪ͨ��Ǯ��� 
        //	 notifyAll();
    	}
}
