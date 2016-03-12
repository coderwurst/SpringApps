package com.coderwurst.sping.aop;

import org.springframework.stereotype.Component;

@Component
public class Camera {
	
	public void snap() {
		System.out.println("SNAP!");
	}
	
	public void snap(int exposure) {
		System.out.println("SNAP! Exposure: " + exposure);
	}
	
	public void snap(String name) {
		System.out.println("SNAP! Name: " + name);
	}
	
	public String snapNightTime() {
		System.out.println("SNAP - Night Mode!");
		return "it's dark alright";
	}

}
