package com.briup.exercise;

public class RabbitAndTurtle1 extends Thread{
      private int s=100;
      private String name;
      private double flag;
      private static boolean end=false;
	public RabbitAndTurtle1(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		while(s>0){
			if(end){
				break;
			}
			flag=Math.random()*1;
			if(name.equals("rabbit")){
				if(flag<=0.3&& flag>0){
					s=s-2;
					if(s==0){
						break;
					}
					System.out.println("rabbit还剩:"+s+"米");
				}
			//	Thread.yield();
			}else if(name.equals("turtle")) {
				if(flag>0.3&&flag<1){
					s=s-1;
					if(s==0){
						break;
					}
					System.out.println("turtle还剩:"+s+"米");
				}
				//Thread.yield();
			}
		}
		if(!end){
			end=true;
			System.out.println(name+"赢了!");
		}
	}
	
	
	
      
	
}
