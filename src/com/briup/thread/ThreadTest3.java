package com.briup.thread;

public class ThreadTest3 implements Runnable{
    
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+
				":interrupt:"+Thread.currentThread().isInterrupted());
		
		try {
			Thread.currentThread().sleep(1000);
			System.out.println(Thread.currentThread().getName()+"休眠正常结束");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(Thread.currentThread().getName()+"run执行异常");
		}
		System.out.println(Thread.currentThread().getName()+"run执行完成");
	/*for(int i=0;i<5;i++){
		 System.out.println(Thread.currentThread().getName()
					+"-isActive:"+Thread.currentThread().isAlive()
					+"-priority:"+Thread.currentThread().getPriority()
					+"-max_prepority："+Thread.currentThread().MAX_PRIORITY
					+"-min_pripority:"+Thread.currentThread().MIN_PRIORITY
					+"-interropt:"+Thread.currentThread().isInterrupted());//测试线程是否已经中断。
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		*/
	}

	public static void main(String[] args) {
		ThreadTest3 tt=new ThreadTest3();
		Thread t1=new Thread(tt,"first");//给线程取别名
		Thread t2=new Thread(tt,"second");
		//t1.setName("first");//给线程取别名
		//t1.setPriority(10);
	   // t2.setPriority(1);
		t1.start();
		if(!t1.isInterrupted()){
			t1.interrupt();
		}
		t2.start();
//		try {
//			t2.join();//谁调用谁先走  谁调用 挂起没被调用的
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
