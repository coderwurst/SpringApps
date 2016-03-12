package com.coderwurst.spring.camera.accessories;

import org.springframework.stereotype.Component;

@Component
public class Lens {
	
	public void zoom (Integer factor) {
		System.out.println("Zooming lens: " + factor);
	}

}
