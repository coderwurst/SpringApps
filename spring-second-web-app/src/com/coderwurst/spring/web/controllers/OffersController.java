package com.coderwurst.spring.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderwurst.spring.web.dao.Offer;
import com.coderwurst.spring.web.service.OffersService;

@Controller
public class OffersController {

	private OffersService offersService;

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id")String id) {
		// if id is not present an error will be thrown
		System.out.println("ID is: " + id);
		return "home";
	}

	// root of application localhost:8080/offers
	@RequestMapping("/offers")
	public String showOffers(Model model) {

		List<Offer> offers = offersService.getCurrent();

		model.addAttribute("offers", offers);

		return "offers"; // offers.jsp
	}

	// root of application localhost:8080/offers
	@RequestMapping("/createoffer")
	public String createOffer(Model model) {

		return "createoffer"; // createoffer.jsp
	}

}
