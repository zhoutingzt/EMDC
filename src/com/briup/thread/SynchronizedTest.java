package com.briup.thread;

public class SynchronizedTest implements Runnable{
    Ticket ticket;
    
	public SynchronizedTest() {
	}

	public SynchronizedTest(Ticket ticket) {//ֻ��new ��ʱ�����ֱ�ӻ��ticket����
		this.ticket = ticket;
	}

	@Override
	public void run() {
		while(ticket.getNum()>0){
			ticket.sale();
//			synchronized (SynchronizedTest.class) {
//				System.out.println(Thread.currentThread().getName()+"��Ʊ����Ʊ��"+ticket.getNum()+"��");
//				try {
//					Thread.currentThread().sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				};	
//			}
			synchronized (ticket) {//ͬ�����
				System.out.println(Thread.currentThread().getName()+"��Ʊ����Ʊ��"+ticket.getNum()+"��");
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
			
		}
		
	}

	public static void main(String[] args) {
		Ticket ticket=new Ticket();
		ticket.setNum(100);
		SynchronizedTest st=new SynchronizedTest(ticket);
		Thread t1=new Thread(st,"�Ϻ���վ");
		Thread t2=new Thread(st,"̫ԭ��վ");
		t1.start();
		t2.start();

	}

}
class Ticket{
	private int num;

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(int num) {
		super();
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void sale(){
		if(num>0){
			num--;
		}
	}
	@Override
	public String toString() {
		return "Ticket [num=" + num + "]";
	}
	
}