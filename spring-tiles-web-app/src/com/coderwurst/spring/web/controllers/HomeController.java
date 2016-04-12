package com.coderwurst.spring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	// root of application localhost:8080/
	@RequestMapping("/")
	public String showHome(Model model) {	
		return "home";
	}

}
