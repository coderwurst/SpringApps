package com.coderwurst.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("offersDao")
public class OffersDAO {
	
	private NamedParameterJdbcTemplate jdbc;

	/*public OffersDAO () {
		System.out.println("Success fully loaded Offers DAO");
	}*/
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List <Offer> getOffers() {
		
		return jdbc.query("select * from offers", new RowMapper<Offer>() {

			// anon method - ResultSet is the data brought back from the DB
			public Offer mapRow(ResultSet rs, int arg1) throws SQLException {
				// the job of this method is to map the result of a query to a single Offer object
				Offer offer = new Offer();
				
				// each property is taken from the result set - grabbed via the column name
				offer.setId(rs.getInt("id"));
				offer.setEmail(rs.getString("email"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				
				return offer;
			}
			
		});
	}
	
	public int[] create (List<Offer> offers) {
		
		SqlParameterSource [] batchValues = SqlParameterSourceUtils.createBatch(offers.toArray());
		
		return jdbc.batchUpdate("insert into offers (name, text, email) values (:name, :text, :email)", batchValues);
		
	}
	
	@Transactional
	public int[] createTransactional (List<Offer> offers) {
		
		SqlParameterSource [] batchValues = SqlParameterSourceUtils.createBatch(offers.toArray());
		
		return jdbc.batchUpdate("insert into offers (id, name, text, email) values (:id, :name, :text, :email)", batchValues);
		
	}
	
	public boolean create(Offer offer) {
		
		System.out.println(">>> adding to database");
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("insert into offers (name, text, email) values (:name, :text, :email)", params) == 1;
		
	}
	
	public boolean update(Offer offer) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);

		return jdbc.update("update offers set name=:name, text=:text, email=:email where id=:id", params) == 1;
	}
	
	public Offer getOffer(int id) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.queryForObject("select * from offers where id = :id", params, new RowMapper<Offer>() {

			// anon method - ResultSet is the data brought back from the DB
			public Offer mapRow(ResultSet rs, int arg1) throws SQLException {
				// the job of this method is to map the result of a query to a single Offer object
				Offer offer = new Offer();
				
				// each property is taken from the result set - grabbed via the column name
				offer.setId(rs.getInt("id"));
				offer.setEmail(rs.getString("email"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				
				return offer;
			}
			
		});
	}
	
	
	public boolean delete(int id) {
		
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from offers where id = :id", params) == 1;
		
	}

}
