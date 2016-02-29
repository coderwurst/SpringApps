package com.coderwurst.spring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OffersController {
	
	// root of application localhost:8080/
	@RequestMapping("/")
	public String showHome(Model model) {
		model.addAttribute("name", "<b>Jane</b>");
		
		return "home";
	}
	
	
	
	/*
 	@RequestMapping("/")
	public ModelAndView showHome() {
		ModelAndView mv = new ModelAndView("home");		// takes display as a param
		Map <String, Object> model = mv.getModel();
		model.put("name", "jonathan");
		return mv;
	}
	 */

}
