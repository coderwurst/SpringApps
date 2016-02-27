package com.coderwurst.spring.app.database;

import java.util.ArrayList;
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
    		
    		//createInitialOffers(offersDao);
    		//printDb(offersDao);
    		
    		//runUpdate(offersDao);
    		//printDb(offersDao);
    		
    		// batchUpdate(offersDao);
    		
    		batchUpdateTransactional(offersDao);
    		
    		printDb(offersDao);
    		
    		
    		
    	} catch (CannotGetJdbcConnectionException ex) {
    		
    		System.out.println("Cannot get database connection");
    		
    	} catch (DataAccessException dbException) {
    		
    		System.out.println(dbException.getMessage());
    		System.out.println(dbException.getClass());
 
    	} 
    	
    	
    	((ClassPathXmlApplicationContext)context).close();
    }

	private static void batchUpdate(OffersDAO offersDao) {
		List <Offer> offers = new ArrayList <Offer>();
		offers.add(new Offer("David","david@mail.com","sql"));
		offers.add(new Offer("Joe","joe@mail.com","JUnit expert"));
		offers.add(new Offer("Susanne","susane@mail.com","elegent web design"));
		
		int [] rvals = offersDao.create(offers);
		
		for (int value : rvals) {
			System.out.println("DB Updated: " + value);
		}
	}
	
	private static void batchUpdateTransactional(OffersDAO offersDao) {
		List <Offer> offers = new ArrayList <Offer>();
		offers.add(new Offer(44, "David","david@mail.com","sql"));
		offers.add(new Offer(45, "Joe","joe@mail.com","JUnit expert"));
		offers.add(new Offer(44, "Susanne","susane@mail.com","elegent web design"));
		
		int [] rvals = offersDao.createTransactional(offers);
		
		for (int value : rvals) {
			System.out.println("DB Updated: " + value);
		}
	}

	private static void runUpdate(OffersDAO offersDao) {
		// user constructor with Id, this offer to be used to test update method
		Offer updateOffer = new Offer(37, "Jimmy", "jimmy@mail.com", "jim 'il fix it");
		
		if(offersDao.update(updateOffer)) {
			System.out.println("updated with offer 3");
		} else {
			System.out.println("not able to update with offer 3");
		}
	}

	private static void createInitialOffers(OffersDAO offersDao) {
		/*boolean result = offersDao.delete(4);
		
		System.out.println("result of delete: " + result);*/
		
		Offer offer1 = new Offer("Dave", "dave@mail.com", "coding Java");
		Offer offer2 = new Offer("Karen", "karen@mail.com", "software testing and QAT");
		
		if(offersDao.create(offer1)) {
			System.out.println("create offer 1");
		}
		
		if(offersDao.create(offer2)) {
			System.out.println("create offer 2");
		}
		
		retrieveRecord(offersDao);
	}

	private static void retrieveRecord(OffersDAO offersDao) {
		Offer offer = offersDao.getOffer(2);
		System.out.println("Should be Mike: " + offer);
	}

	private static void printDb(OffersDAO offersDao) {
		List <Offer> offers = offersDao.getOffers();
		
		for (Offer offer : offers) {
			System.out.println(offer.toString());
		}
	}
}
