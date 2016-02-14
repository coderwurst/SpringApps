package com.coderwurst.spring.logger;

public class Logger {
	
	private ConsoleWriter consoleWriter = new ConsoleWriter();
	private FileWriter fileWriter = new FileWriter();
		
	public void writeFile(String text) {
		this.fileWriter.write(text);
	}
	
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	
	public void writeConsole(String text) {
		this.consoleWriter.write(text);
	}
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
}
