package com.briup.exercise;

public class RabbitAndTurtle extends Thread{
    public  double flag;
    public  int count=100;

	@Override
	public void run() {
	    while(count<100){
	    	flag=Math.random()*1;
	    	if(Thread.currentThread().getName().equals("rabbit")){
	    		if(flag>0&&flag<0.3){
					count=count-2;
					System.out.println(Thread.currentThread().getName()+"还剩："+count+"米");
					if(count<=0){
						System.out.println(Thread.currentThread().getName()+"赢了");
					}
	    	}
			
			}else if(Thread.currentThread().getName().equals("turtle")){
				if(flag>=0.3&&flag<1){
					count=count-1;
					System.out.println(Thread.currentThread().getName()+"还剩："+count+"米");
					if(count<=0){
						System.out.println(Thread.currentThread().getName()+"赢了");
					}
			}
	    }	
	    }
	
	}
}
	

	

