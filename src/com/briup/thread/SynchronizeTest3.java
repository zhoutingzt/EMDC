package com.briup.thread;

public class SynchronizeTest3 implements Runnable{
	private MethodSync methodSyn=new MethodSync();
	
	@Override
	public void run() {
		methodSyn.Method();
	}
	
	public static void main(String[] args) {
		Thread t1=new Thread(new SynchronizeTest3());
		Thread t2=new Thread(new SynchronizeTest3());
		t1.start();
		t2.start();

	}

}
class MethodSync{
	private static int num=0;
	public synchronized void Method(){
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"="+num++);
		}
	}
}