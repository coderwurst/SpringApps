package com.coderwurst.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coderwurst.spring.camera.accessories.Lens;

public class App {

	public static void main (String [] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/coderwurst/spring/aop/beans.xml");
		
		Camera camera = (Camera)context.getBean("camera");
		
		camera.snap();
		camera.snap(500);
		camera.snap(10.2);
		camera.snap(400, 8.8);
		camera.snapNightTime();
		
		context.close();
		
	}
	
	
}
