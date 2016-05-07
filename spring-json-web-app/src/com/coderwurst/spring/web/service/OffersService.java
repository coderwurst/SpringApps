package com.coderwurst.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.coderwurst.spring.web.dao.Offer;
import com.coderwurst.spring.web.dao.OffersDao;

@Service("offersService")
public class OffersService {

	private OffersDao offersDAO;

	@Autowired
	public void setOffersDAO(OffersDao offersDAO) {
		this.offersDAO = offersDAO;
	}

	public List<Offer> getCurrent() {
		return offersDAO.getOffers();
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void create(Offer offer) {
		// more complicated code - authentication, etc.
		offersDAO.saveOrUpdate(offer);
	}

	// test method to throw exception
	public void throwTestException() {
		offersDAO.getOffer(99999);
	}

	public boolean hasOffer(String name) {
		if (name == null) {
			return false;
		}

		List<Offer> offers = offersDAO.getOffers(name);

		if (offers.size() == 0) {
			return false;
		}

		return true;

	}

	public Offer getOffer(String username) {

		// incorrect username check
		if (username == null) {
			return null;
		}

		List<Offer> offers = offersDAO.getOffers(username);

		// empty list check
		if (offers.size() == 0) {
			return null;
		}

		return offers.get(0);
	}

	public void saveOrUpdate(Offer offer) {

		offersDAO.saveOrUpdate(offer);

	}

	public void delete(int id) {
		offersDAO.delete(id);
	}

}
