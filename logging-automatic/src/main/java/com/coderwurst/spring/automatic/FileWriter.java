package com.coderwurst.spring.automatic;

import org.springframework.stereotype.Component;

@Component
public class FileWriter implements LogWriter{

	public void write(String text) {
		System.out.println("File Writer: " + text);
	}
}
