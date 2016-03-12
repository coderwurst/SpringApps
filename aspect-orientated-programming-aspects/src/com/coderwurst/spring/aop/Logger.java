package com.coderwurst.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect 	// aspect
@Component	// bean
public class Logger {
	
	@Pointcut("args(exposure, speed)")
	public void cameraSnapArgs(int exposure, double speed) {
		// empty implementation
	}
	
	@Pointcut("target(com.coderwurst.spring.aop.Camera)")
	public void cameraSnapTarget() {
		// empty implementation
	}
	
		
	@Before("cameraSnapTarget() && cameraSnapArgs(exposure, speed)") 
	public void beforeAdvice(JoinPoint jp, int exposure, double speed) {
		System.out.println("*********** Before advice ***********");
		
		System.out.printf("Exposure %d, speed %.2f\n", exposure, speed);
		
	}
	
	@After("within(com.coderwurst.spring.aop.Camera) && @annotation(org.springframework.stereotype.Component)")
	public void afterAdvice() {
		System.out.println("*********** After advice ***********");
	}
	
	@Pointcut("within(com.coderwurst.spring.aop.Camera)")
	public void cameraSnapWithin() {
		// empty implementation
	}
	
	/*@After("cameraSnap()") 
	public void afterAdvice() {
		System.out.println("After advice...");
	}
	
	@AfterReturning("cameraSnap()") 
	public void afterReturningAdvice() {
		System.out.println("After Returning advice...");
	}
	
	@AfterThrowing("cameraSnap()") 
	public void afterThrowingAdvice() {
		System.out.println("After Throwing advice...");
	}
	
	@Around("cameraSnap()") 
	public void afterAroundAdvice(ProceedingJoinPoint point) {
		System.out.println("Wraps around the advice before...");
		
		try {
			point.proceed();
		} catch (Throwable e) {
			System.out.println("in around advice " + e.getMessage());
		}
		
		System.out.println("Wraps around the advice after...");
	}*/

}
