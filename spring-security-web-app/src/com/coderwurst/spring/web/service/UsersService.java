package com.coderwurst.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderwurst.spring.web.dao.User;
import com.coderwurst.spring.web.dao.UsersDao;

@Service("usersService")
public class UsersService {
	
	private UsersDao usersDao;
	
	@Autowired
	public void setOffersDAO(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void create(User user) {
		// more complicated code - authentication, etc.
		usersDao.create(user);
	}
}
