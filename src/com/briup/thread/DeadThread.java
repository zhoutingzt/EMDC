package com.briup.thread;

public class DeadThread implements Runnable{

	public int flag;
	public static Object o1=new Object();//筷子
	public  static Object o2=new Object();//碗
	@Override
	public void run() {//解决死锁 按照某一个顺序去执行
		if(flag==0){
			synchronized (o1) {
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			synchronized (o2) {
				System.out.println("1号我能吃饭了");
			}
			}
		}else if(flag==1){
			synchronized (o1) {
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			synchronized (o2) {
			System.out.println("2号我能吃饭了");	
			}
			}
		}
		
	}
	public static void main(String[] args) {
		DeadThread dt1=new DeadThread();
	    DeadThread dt2=new DeadThread();
	    dt1.flag=0;
	    dt2.flag=1;
	    Thread t1=new Thread(dt1);
	    Thread t2=new Thread(dt2);
	    t1.start();
	    t2.start();
	}

}
