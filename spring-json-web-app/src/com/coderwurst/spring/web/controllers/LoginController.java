package com.coderwurst.spring.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderwurst.spring.web.dao.FormValidationGroup;
import com.coderwurst.spring.web.dao.Message;
import com.coderwurst.spring.web.dao.User;
import com.coderwurst.spring.web.service.UsersService;

@Controller
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);
	
	private UsersService usersService;
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	// http GET request, produces json formatted file
	@RequestMapping(value="/getMessages", method=RequestMethod.GET, produces="application/json")
	@ResponseBody		// determines what format to use in response - finds org.codehaus-jackson dependencies
	public Map <String, Object> getMessages(Principal principle) {
		List <Message> messages = null;
		if (principle == null) {
			messages = new ArrayList <Message>();
		} else {
			String username = principle.getName();
			messages = usersService.getMessages(username);
		}
		
		Map <String, Object> data = new HashMap <String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());
		
		return data;
	}

	// http POST request, received json formatted file and returns JSON reply
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody // determines what format to use in response - finds org.codehaus-jackson dependencies
	public Map<String, Object> sendMessage(Principal principle, @RequestBody Map<String, Object> data) {

		String name = (String) data.get("name");
		String email = (String) data.get("email");
		String contents = (String) data.get("text");
		int target = (Integer) data.get("target");

		System.out.println("<<< Map contents: name = " + name + ", email = " + 
		email + ", contents: " + contents + ", target = " + target);

		Map<String, Object> returnMessage = new HashMap<String, Object>();
		returnMessage.put("success", true);
		returnMessage.put("target", target);

		return returnMessage;
	}

	@RequestMapping("/login")
	public String showLogin() {
		logger.info("showing login page");
		return "login";
	}
	
	@RequestMapping("/logout")
	public String showLogout() {
		logger.info("attempting logout");
		return "logout";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		
		model.addAttribute("user", new User());
		return "newaccount";
	}
	
	// admin area
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		
		List<User> users = usersService.getAllUsers();
		// users can be accessed directly in jsp using j core tags forEach loop
		model.addAttribute("users", users);

		return "admin";
	}
	
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping("/messages")
	public String showMessages() {
		return "messages";
	}


	// used to point to web form
	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createAccount(@Validated(FormValidationGroup.class) User user, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(">>> user create form does not validate:");

			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}

			return "newaccount";

		} else {
			System.out.println("<<< user create form validated");
			
			user.setAuthority("ROLE_USER");
			user.setEnabled(true);

			System.out.println(user);
			
			if(usersService.exists(user.getUsername())) {
				System.out.println("<<< Caught duplicate username");
				result.rejectValue("username", "DuplicateKey.user.username");
				return "newaccount";
			}
			
			
			try {
				usersService.create(user);
			} catch (DuplicateKeyException e) {		// Spring wraps all DB primary key exceptions to this
				// param 1: name of control associated with error
				// param 2: key to look up error message in prop file, DuplicateKey.class.field
				// param 3: duplicate message
				result.rejectValue("username", "DuplicateKey.user.username");
				return "newaccount";
			}
			
			return "accountcreated";
		}
	}

}
