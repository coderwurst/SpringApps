package com.coderwurst.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	
	public static void main(String[] args) {
		
		// first reads this and goes to look for the xml file
		ApplicationContext context = new ClassPathXmlApplicationContext("com/coderwurst/spring/test/beans/beans.xml");
		
		// file has been found, bean returns the Person Object
		Person person = (Person) context.getBean("person");
		person.setTaxId(888);
		
		Address address2 = (Address)context.getBean("address2");
		System.out.println(address2);
		
		// cannot close AppContext but can close FSXAC therefore we cast it
		((ClassPathXmlApplicationContext)context).close();
	}

}
