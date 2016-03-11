package com.coderwurst.sping.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main (String [] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/coderwurst/sping/aop/beans.xml");
		
		Camera camera = (Camera)context.getBean("camera");
		
		camera.snap();
		
		context.close();
		
	}
	
	
}
