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
	
	// private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/*@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	} */
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	/*@Transactional		// both sql statements in this method will succeed or neither will succeed
	public boolean create (User user) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();		// allows manual setting of params compared to object as seen with BeanPropertySqlParameter Source
		params.addValue("username", user.getUsername());
		// params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("password", user.getPassword());
		params.addValue("email", user.getEmail());
		params.addValue("name", user.getName());
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority());		// needed for 2nd query

		// BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		return jdbc.update("insert into users (username, name, password, email, enabled, authority) values (:username, :name, :password, :email, :enabled, :authority)", params) == 1;
			
	}*/
	
	@Transactional
	public void create (User user) {
		// user.setPassword(passwordEncoder.encode(user.getPassword()));		ENCRYPTION VALIDATING ON 2ND LOGIN ???
		session().save(user);	
	}

	/*public boolean exists(String username) {
		// query db to find username, inc. paramSource info for username, to return an int value
		return jdbc.queryForObject("select count(*) from users where username=:username",
				new MapSqlParameterSource("username", username), Integer.class) > 0;
	}*/
	
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

	/* public List<User> getAllUsers() {
		// query db to return all users and authorites (matching pairs) and store in BeanPropertyRowMapper object
		return jdbc.query("select * from users",
				BeanPropertyRowMapper.newInstance(User.class));
	} */
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		// query in Hibernate Query Language (HQL)
		return session().createQuery("from User").list();
	}
	
}
