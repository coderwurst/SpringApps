package com.coderwurst.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("messagesDao")
public class MessagesDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List <Message> getMessages() {
		Criteria crit = session().createCriteria(Message.class);		
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List <Message> getMessages(String username) {
		Criteria crit = session().createCriteria(Message.class);
		
		crit.add(Restrictions.eq("u.username", username));
		
		return crit.list();
	}
	
	public void saveOrUpdate(Message message) {
		session().saveOrUpdate(message);
	}
	
	public Message getMessage(int id) {
		
		Criteria crit = session().createCriteria(Message.class);

		crit.add(Restrictions.idEq(id));

		return (Message) crit.uniqueResult();		// to return a Message object
		
	}
	
	public boolean delete(int id) {
		
		Query query = session().createQuery("delete from message where id=:id");
		
		query.setLong("id", id);		// replaces the placeholder 'id' with the value
		
		return query.executeUpdate() == 1;
		
	}

}
