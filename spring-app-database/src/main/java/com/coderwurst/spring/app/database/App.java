package com.coderwurst.spring.app.database;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class App 
{
    public static void main ( String[] args ) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/coderwurst/spring/app/database/beans/beans.xml");
    	
    	OffersDAO offersDao = (OffersDAO) context.getBean("offersDao");
    	System.out.println("Retrieving Info from DB");

    	try {
    		
    		/*boolean result = offersDao.delete(4);
    		
    		System.out.println("result of delete: " + result);*/
    		
    		List <Offer> offers = offersDao.getOffers();
        	
        	for (Offer offer : offers) {
        		System.out.println(offer.toString());
        	}
        	
        	Offer offer = offersDao.getOffer(2);
        	System.out.println("Should be Mike: " + offer);
    		
    	} catch (CannotGetJdbcConnectionException ex) {
    		
    		System.out.println("Cannot get database connection");
    		
    	} catch (DataAccessException dbException) {
    		
    		System.out.println(dbException.getMessage());
    		System.out.println(dbException.getClass());
 
    	} 
    	
    	
    	((ClassPathXmlApplicationContext)context).close();
    }
}
