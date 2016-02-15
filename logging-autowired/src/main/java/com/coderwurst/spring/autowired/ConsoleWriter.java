package com.coderwurst.spring.autowired;

public class ConsoleWriter implements LogWriter{

	public void write(String text) {
		System.out.println("Console Writer: " + text);
	}

}
