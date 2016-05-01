package com.coderwurst.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void create (User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);	
	}
	
	public boolean exists(String username) {
		// Hibernate Object criteria - takes the object being returned as a param
		Criteria crit = session().createCriteria(User.class);
		
		// line 73 currently returns all user objects, need to trim this down using Restrictions
		// "username" refers to field in User object, username refers to passed in string
		// crit.add(Restrictions.eq("username", username));
		
		crit.add(Restrictions.idEq(username));
		
		// instead of storing a list, we can use a unique result
		User user = (User) crit.uniqueResult();
		
		return user != null;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		// query in Hibernate Query Language (HQL)
		return session().createQuery("from User").list();
	}
	
}
