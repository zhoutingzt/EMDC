package com.briup.thread;

public class ThreadTest2 implements Runnable{// 实现Runnable接口

	private  int ticket=100;//对于实现的可以用非静态的
	
	@Override
	public void run() {
		
		while(ticket>0){
			ticket--;
			System.out.println(Thread.currentThread().getName()+"剩余："+ticket+"票");
		}
	}

	public static void main(String[] args) {
		ThreadTest2 tt=new ThreadTest2();
		Thread t=new Thread(tt);
		Thread t2=new Thread(tt);
		t.start();
		t2.start();
	}

}
