package com.briup.exercise;

public class Counter extends Thread{
	private  Storage data;

	public Counter() {
	}
	public Counter(Storage data) {
		super();
		this.data = data;
	}

	@Override
	public void run() {
		int num=0;
	    while(num<100){
	    	synchronized (data) {//ji
	    		if(!data.isCount){
	    			data.setNum(num++);
	    			data.isCount=true;
		    		//notify();
	    		}
//	    		else {
//					try {
//						data.wait();
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}	
			}
	    }
	}

}
