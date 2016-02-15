package com.coderwurst.spring.automatic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Logger {
	
	private ConsoleWriter consoleWriter;
	private FileWriter fileWriter;
	
	@Autowired
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	
	@Autowired
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	
	public void writeConsole(String text) {
		if (consoleWriter != null) {
			this.consoleWriter.write(text);
		}
	}
	
	public void writeFile(String text) {
		this.fileWriter.write(text);
	}
	
}
