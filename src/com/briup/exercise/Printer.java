package com.briup.exercise;

public class Printer extends Thread{
   
	private Storage data;

	public Printer() {
	
	}

	public Printer(Storage data) {
		this.data = data;
	}



	@Override
	public void run() {
		while(true){
			synchronized (data) {
				if(data.isCount){
					int num=data.getNum();
					 System.out.println(num);
					  data.isCount=false;
					  if(num>99){
						  break;
					  }
					  data.notify();
				}
//				else{
//					try {
//						wait();
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
			  
			}
		}
	}
	
}
