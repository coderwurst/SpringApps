package com.coderwurst.spring.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coderwurst.spring.web.dao.User;
import com.coderwurst.spring.web.service.UsersService;

@Controller
public class LoginController {

	private UsersService usersService;
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
		
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {

		model.addAttribute("user", new User());
		return "newaccount";
	}


	// used to point to web form
	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(">>> user create form does not validate:");

			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}

			return "newaccount";

		} else {
			System.out.println("<<< user create form validated");
			
			user.setAuthority("user");
			user.setEnabled(true);

			System.out.println(user);
			usersService.create(user);

			return "accountcreated";
		}
	}

}
