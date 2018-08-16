package com.briup.exercise;

public class Race {

	public static void main(String[] args) {
		RabbitAndTurtle1 rabbit=new RabbitAndTurtle1("rabbit");
		RabbitAndTurtle1 turtle=new RabbitAndTurtle1("turtle");
		//rabbit.setName("rabbit");
		//turtle.setName("turtle");
        rabbit.start();
        turtle.start();

	}

}
