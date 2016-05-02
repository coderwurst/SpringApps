package com.coderwurst.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OfferRowMapper implements RowMapper <Offer> {

	@Override
	public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
		// set user object to be added to offer
		User user = new User();
		user.setAuthority(rs.getString("authority"));
		user.setEmail(rs.getString("email"));
		user.setEnabled(true);
		user.setName(rs.getString("name"));
		user.setUsername(rs.getString("username"));
		
		// the job of this method is to map the result of a query to a single Offer object
		Offer offer = new Offer();
		
		// each property is taken from the result set - grabbed via the column name
		offer.setId(rs.getInt("id"));
		offer.setText(rs.getString("text"));
		offer.setUser(user);
		
		return offer;
	}

}
