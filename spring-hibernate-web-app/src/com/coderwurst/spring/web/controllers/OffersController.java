package com.coderwurst.spring.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderwurst.spring.web.dao.FormValidationGroup;
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

		// force exception for testing
		// offersService.throwTestException();
		
		List<Offer> offers = offersService.getCurrent();

		model.addAttribute("offers", offers);

		return "offers"; // offers.jsp
	}

	// root of application localhost:8080/offers
	@RequestMapping("/createoffer")
	public String createOffer(Model model, Principal principal) {

		Offer offer = null;
		
		// does user have any current offers
		if (principal != null) {
			String username = principal.getName();
			
			offer = offersService.getOffer(username);
		}
		
		// null check
		if (offer == null) {
			offer = new Offer();		// creates a blank offer
		}
		
		// add to model to be added to form on screen
		model.addAttribute("offer", offer);
		
		return "createoffer"; // createoffer.jsp
	}
	
	// used to point to web form
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Validated(value=FormValidationGroup.class) Offer offer, BindingResult result
			, Principal principal, @RequestParam(value="delete", required = false)String delete) {
		
		if(result.hasErrors()) {
			System.out.println(">>> form does not validate:");
			
			List <ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			
			return "createoffer";		// createoffer.jsp
			
		} else {
			System.out.println("<<< form validated");
			System.out.println(offer);
			
			if (delete == null) {
				
				String username = principal.getName();
				offer.getUser().setUsername(username);
				offersService.saveOrUpdate(offer);
				return "offercreated";
			} else {
				
				offersService.delete(offer.getId());
				return "offerdeleted";
			}
			

			
		}		
	}	

}
