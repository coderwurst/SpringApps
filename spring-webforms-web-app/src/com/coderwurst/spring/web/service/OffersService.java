package com.coderwurst.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderwurst.spring.web.dao.Offer;
import com.coderwurst.spring.web.dao.OffersDAO;

@Service("offersService")
public class OffersService {
	
	private OffersDAO offersDAO;
	
	@Autowired
	public void setOffersDAO(OffersDAO offersDAO) {
		this.offersDAO = offersDAO;
	}

	public List <Offer> getCurrent() {
		
		return offersDAO.getOffers();
	}

	public void create(Offer offer) {
		// more complicated code - authentication, etc.
		offersDAO.create(offer);
	}

	// test method to throw exception
	public void throwTestException() {
		offersDAO.getOffer(99999);
	}

}
