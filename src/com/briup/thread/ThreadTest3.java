package com.briup.thread;

public class ThreadTest3 implements Runnable{
    
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+
				":interrupt:"+Thread.currentThread().isInterrupted());
		
		try {
			Thread.currentThread().sleep(1000);
			System.out.println(Thread.currentThread().getName()+"������������");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(Thread.currentThread().getName()+"runִ���쳣");
		}
		System.out.println(Thread.currentThread().getName()+"runִ�����");
	/*for(int i=0;i<5;i++){
		 System.out.println(Thread.currentThread().getName()
					+"-isActive:"+Thread.currentThread().isAlive()
					+"-priority:"+Thread.currentThread().getPriority()
					+"-max_prepority��"+Thread.currentThread().MAX_PRIORITY
					+"-min_pripority:"+Thread.currentThread().MIN_PRIORITY
					+"-interropt:"+Thread.currentThread().isInterrupted());//�����߳��Ƿ��Ѿ��жϡ�
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
		Thread t1=new Thread(tt,"first");//���߳�ȡ����
		Thread t2=new Thread(tt,"second");
		//t1.setName("first");//���߳�ȡ����
		//t1.setPriority(10);
	   // t2.setPriority(1);
		t1.start();
		if(!t1.isInterrupted()){
			t1.interrupt();
		}
		t2.start();
//		try {
//			t2.join();//˭����˭����  ˭���� ����û�����õ�
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
