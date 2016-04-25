package com.coderwurst.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
@Component("offersDao")
public class OffersDao {
	
	private NamedParameterJdbcTemplate jdbc;

	/*public OffersDAO () {
		System.out.println("Success fully loaded Offers DAO");
	}*/
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List <Offer> getOffers() {
		
		return jdbc.query("select * from offers, users where offers.username=users.username and users.enabled=true", 
				new OfferRowMapper());
	}
	
	public List <Offer> getOffers(String username) {
		
		return jdbc.query("select * from offers, users where offers.username=users.username and users.enabled=true and offers.username=:username", 
				new MapSqlParameterSource("username", username), new OfferRowMapper());
	}
	
	public int[] create (List<Offer> offers) {
		
		SqlParameterSource [] batchValues = SqlParameterSourceUtils.createBatch(offers.toArray());
		
		return jdbc.batchUpdate("insert into offers (name, text, email) values (:name, :text, :email)", batchValues);
		
	}
	
	@Transactional
	public int[] createTransactional (List<Offer> offers) {
		
		SqlParameterSource [] batchValues = SqlParameterSourceUtils.createBatch(offers.toArray());
		
		return jdbc.batchUpdate("insert into offers (username, text) values (:username, :text)", batchValues);
		
	}
	
	/* public boolean create(Offer offer) {
		
		System.out.println(">>> adding to database");
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("insert into offers (username, text) values (:username, :text)", params) == 1;
		
	} */
	
	public void create(Offer offer) {
		
		session().save(offer);
	}
	
	public boolean update(Offer offer) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);

		return jdbc.update("update offers set text=:text where id=:id", params) == 1;
	}
	
	public Offer getOffer(int id) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.queryForObject("select * from offers, users where offers.username=users.username and users.enabled=true and id=:id", 
				params, new OfferRowMapper());
	}
	
	
	public boolean delete(int id) {
		
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from offers where id = :id", params) == 1;
		
	}

}
