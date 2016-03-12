package com.coderwurst.spring.aop;

import org.springframework.stereotype.Component;

@Component("camera")
public class Camera implements PhotoSnapper{
	
	public Camera() {
		System.out.println("Hello from Camera Constructor");
	}
	
	public void snap() {
		System.out.println("SNAP!");
	}
	
	public void snap(int exposure) {
		System.out.println("SNAP! Exposure: " + exposure);
	}
	
	public void snap(double exposure) {
		System.out.println("SNAP! Exposure: " + exposure);
	}
	
	public void snap(int exposure, double speed) {
		System.out.println("SNAP! Exposure: " + exposure + "\nSNAP! Speed: " + speed);
	}
	
	public void snap(String name) {
		System.out.println("SNAP! Name: " + name);
	}
	
	public String snapNightTime() {
		System.out.println("SNAP - Night Mode!");
		return "it's dark alright";
	}

}
