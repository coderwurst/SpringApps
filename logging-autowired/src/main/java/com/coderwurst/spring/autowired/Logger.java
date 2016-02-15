package com.coderwurst.spring.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

public class Logger {
	
	// @Autowired
	private ConsoleWriter consoleWriter;// = new ConsoleWriter();
	
	// @Autowired
	private FileWriter fileWriter;// = new FileWriter();
	
	// @Autowired
	/*public Logger(ConsoleWriter consoleWriter, FileWriter fileWriter) {
		this.consoleWriter = consoleWriter;
		this.fileWriter = fileWriter;
	}*/
	
	@Inject
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	
	@Autowired
	@Qualifier("toconsole")
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
	
	@SuppressWarnings("restriction")
	@PostConstruct
	public void init() {
		System.out.println("Hello from init");
	}
	
	@SuppressWarnings("restriction")
	@PreDestroy
	public void destroy() {
		System.out.println("Bye bye from destroy");
	}
	
}
