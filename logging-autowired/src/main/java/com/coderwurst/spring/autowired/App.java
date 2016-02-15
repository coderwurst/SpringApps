package com.coderwurst.spring.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main ( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/coderwurst/spring/autowired/beans/beans.xml");
    	
    	Logger logger = (Logger) context.getBean("logger");
    	
    	logger.writeConsole("This time we are using annotations");
    	logger.writeFile("and again!");
    	
    	((ClassPathXmlApplicationContext)context).close();
    }
}
