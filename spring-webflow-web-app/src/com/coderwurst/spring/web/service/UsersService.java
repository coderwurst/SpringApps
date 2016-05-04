package com.coderwurst.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.coderwurst.spring.web.dao.Message;
import com.coderwurst.spring.web.dao.MessagesDao;
import com.coderwurst.spring.web.dao.User;
import com.coderwurst.spring.web.dao.UsersDao;

@Service("usersService")
public class UsersService {
	
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private MessagesDao messagesDao;

	public void create(User user) {
		// more complicated code - authentication, etc.
		usersDao.create(user);
	}

	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	public User getUser(String username) {
		return usersDao.getUser(username);
	}
	
	public void sendMessage(Message message) {
		messagesDao.saveOrUpdate(message);
	}
}
