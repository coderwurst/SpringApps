package com.coderwurst.sping.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coderwurst.spring.camera.accessories.Lens;

public class App {

	public static void main (String [] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/coderwurst/sping/aop/beans.xml");
		
		Camera camera = (Camera)context.getBean("camera");
		Lens lens = (Lens)context.getBean("lens");
		
		camera.snap();
		/*camera.snap(1000);
		camera.snap("selfie");
		camera.snapNightTime();
		
		lens.zoom(5); */
		
		context.close();
		
	}
	
	
}
