package com.coderwurst.spring.robot.database;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main ( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/coderwurst/spring/robot/database/beans/beans.xml");
    	
    	OffersDAO offersDao = (OffersDAO) context.getBean("offersDao");
    	System.out.println("Retrieving Info from DB");
    	List <Offer> offers = offersDao.getOffers();
    	
    	for (Offer offer : offers) {
    		System.out.println(offer.toString());
    	}
    	
    	((ClassPathXmlApplicationContext)context).close();
    }
}
