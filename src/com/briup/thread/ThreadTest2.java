package com.briup.thread;

public class ThreadTest2 implements Runnable{// ʵ��Runnable�ӿ�

	private  int ticket=100;//����ʵ�ֵĿ����÷Ǿ�̬��
	
	@Override
	public void run() {
		
		while(ticket>0){
			ticket--;
			System.out.println(Thread.currentThread().getName()+"ʣ�ࣺ"+ticket+"Ʊ");
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
