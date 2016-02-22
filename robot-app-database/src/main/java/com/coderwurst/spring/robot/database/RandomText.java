package com.coderwurst.spring.robot.database;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomText {

	private static String [] texts = {
			"I'll be back",
			"Get out now!",
			"I want your clothes, boots and motorcycle.",
			null
	};
	
	public String getText() {
		Random random = new Random();
		
		return texts[random.nextInt(texts.length)];
	}

}
