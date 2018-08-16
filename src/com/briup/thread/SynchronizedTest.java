package com.briup.thread;

public class SynchronizedTest implements Runnable{
    Ticket ticket;
    
	public SynchronizedTest() {
	}

	public SynchronizedTest(Ticket ticket) {//只有new 的时候可以直接获得ticket对象
		this.ticket = ticket;
	}

	@Override
	public void run() {
		while(ticket.getNum()>0){
			ticket.sale();
//			synchronized (SynchronizedTest.class) {
//				System.out.println(Thread.currentThread().getName()+"卖票，余票："+ticket.getNum()+"张");
//				try {
//					Thread.currentThread().sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				};	
//			}
			synchronized (ticket) {//同步标记
				System.out.println(Thread.currentThread().getName()+"卖票，余票："+ticket.getNum()+"张");
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
		Thread t1=new Thread(st,"上海火车站");
		Thread t2=new Thread(st,"太原火车站");
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