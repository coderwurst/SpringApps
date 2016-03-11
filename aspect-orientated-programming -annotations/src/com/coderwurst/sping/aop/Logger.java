package com.coderwurst.sping.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect 	// aspect
@Component	// bean
public class Logger {
	
	// example of a reusable point cut, alternative to adding directly into @Before path
	@Pointcut("execution(void com.coderwurst.sping.aop.Camera.snap())")
	public void cameraSnap() {
		// empty implementation
	}
	
	@Before("execution(void com.coderwurst.sping.aop.Camera.snap())") // advice
	public void aboutToTakePhoto() {
		System.out.println("about to take photo...");
	}

}
