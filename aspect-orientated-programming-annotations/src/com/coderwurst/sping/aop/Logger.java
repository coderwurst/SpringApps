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
	/*
	@Before("execution(void com.coderwurst.sping.aop.Camera.snap())") // advice
	public void aboutToTakePhoto() {
		System.out.println("about to take photo...");
	}*/
	
	// any method, any return type, any params
	@Before("execution(* com.coderwurst.sping.aop.Camera.* (..))") // advice
	public void aboutToTakePhoto() {
		System.out.println("about to take photo...");
	}
	
	@Before("execution(void com.coderwurst.sping.aop.Camera.snap(String))") // advice
	public void aboutToTakePhotoString() {
		System.out.println("... with photo name...");
	}
	
	@Before("execution(String com.coderwurst.sping.aop.Camera.*())") // advice
	public void aboutToTakePhotoNightTime() {
		System.out.println("... at night...");
	}
	
	// will take any method returning any type from any class in the aop package, with an int
	@Before("execution(* *.*.*(..))") // advice
	public void aboutToTakePhotoWithLens() {
		System.out.println("... with int param ...");
	}


}
