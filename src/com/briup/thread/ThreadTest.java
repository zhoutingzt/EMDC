package com.briup.thread;

public class ThreadTest extends Thread {//通过继承的方法思想多线程
    private  static int ticket=100;//如果用继承来实现共享数据的时候 需要将数据声明为静态的 
	@Override
	public void run() {
		while(ticket>0){
			ticket--;
		System.out.println(getName()+"剩余："+ticket+"票"); //getName返回线程的名字
		}
	}

	public static void main(String[] args) {
         ThreadTest tt=new ThreadTest();
         ThreadTest t2=new ThreadTest();
         tt.start();
         t2.start();
         
         //不能调用重写的run()方法 因为这样的话会单个单个启动线程
         System.out.println(currentThread().getName()+"执行完成");//main线程和其他线程具有相同的等级
		
	}

}
