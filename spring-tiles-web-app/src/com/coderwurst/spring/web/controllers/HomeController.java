package com.coderwurst.spring.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private static Logger logger = Logger.getLogger(HomeController.class);
	
	// root of application localhost:8080/
	@RequestMapping("/")
	public String showHome(Model model) {	
		logger.info("showing home page");
		return "home";
	}

}
