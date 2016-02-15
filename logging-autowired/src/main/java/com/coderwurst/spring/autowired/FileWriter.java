package com.coderwurst.spring.autowired;

public class FileWriter implements LogWriter{

	public void write(String text) {
		System.out.println("File Writer: " + text);
	}
}
