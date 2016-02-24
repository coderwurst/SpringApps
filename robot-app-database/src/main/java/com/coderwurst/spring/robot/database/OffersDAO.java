package com.coderwurst.spring.robot.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component("offersDao")
public class OffersDAO {
	
	private JdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new JdbcTemplate(jdbc);
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

}
