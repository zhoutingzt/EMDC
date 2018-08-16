package com.briup.thread;

public class SynchronizedTest2 extends Thread {
     public static int num=10;
	
	@Override
	public void run() {
//		while(num>0){
//			num--;
//			print(num);
//		}
		print(num);
	}
	public synchronized void print(int num){
		
		while(num>0){
			num--;
			System.out.println(getName()+"-"+num);
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	public static void main(String[] args) {
		SynchronizedTest2 t1=new SynchronizedTest2();
		SynchronizedTest2 t2=new SynchronizedTest2();
		t1.start();
		t2.start();

	}

}
