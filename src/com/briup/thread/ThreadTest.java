package com.briup.thread;

public class ThreadTest extends Thread {//ͨ���̳еķ���˼����߳�
    private  static int ticket=100;//����ü̳���ʵ�ֹ������ݵ�ʱ�� ��Ҫ����������Ϊ��̬�� 
	@Override
	public void run() {
		while(ticket>0){
			ticket--;
		System.out.println(getName()+"ʣ�ࣺ"+ticket+"Ʊ"); //getName�����̵߳�����
		}
	}

	public static void main(String[] args) {
         ThreadTest tt=new ThreadTest();
         ThreadTest t2=new ThreadTest();
         tt.start();
         t2.start();
         
         //���ܵ�����д��run()���� ��Ϊ�����Ļ��ᵥ�����������߳�
         System.out.println(currentThread().getName()+"ִ�����");//main�̺߳������߳̾�����ͬ�ĵȼ�
		
	}

}
