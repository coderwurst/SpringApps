package com.coderwurst.spring.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coderwurst.spring.web.dao.Offer;
import com.coderwurst.spring.web.dao.OffersDao;
import com.coderwurst.spring.web.dao.User;
import com.coderwurst.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations={"classpath:com/coderwurst/spring/web/config/dao-context.xml", 
		"classpath:com/coderwurst/spring/web/config/security-context.xml",
		"classpath:com/coderwurst/spring/web/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private OffersDao offersDao;
	
	@Autowired
	private DataSource dataSource;
	
	// connect and prepare test DB before running tests
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		// clean out before running tests
		jdbc.execute("delete from users");
		jdbc.execute("delete from authorities");
		jdbc.execute("delete from offers");
	}
	
	@Test
	public void testCreateUserCreateUser() {
		// given
		User user = new User("andrew", "hellohello", "andrew@mail.com", true, "user");
		
		// then
		// user has been added
		assertTrue("user creation should return true", usersDao.create(user));	
		
		List <User> users = usersDao.getAllUsers();
		// only 1 record added
		assertEquals("users should contain 1 and only 1 user", 1, users.size());
		
		// user exists
		assertTrue("user exists in DB", usersDao.exists(user.getUsername()));
		
		// compare data added - uses .equals method in User Object
		assertEquals("created user should match retrieved user identically", user, users.get(0));
		
	}
	
	@Test
	public void testCreateUserCreateOffer() {
		// given
		Offer offer = new Offer("jimmy", "jimmy@mail.com", "this is a test offer");
		
		// then
		// offer has been added to DB
		assertTrue("offer creation should return true", offersDao.create(offer));	
		
		List <Offer> offers = offersDao.getOffers();
		// only 1 record added
		assertEquals("offers should contain 1 and only 1 user", 1, offers.size());
		
		// compare data added - offer.equals method in Offer Object (ID omitted as can only be determined after adding to DB)
		assertEquals("created offer should match retrieved offer identically", offer, offers.get(0));
		
		// get offer to perform an update
		offer = offers.get(0);
		offer.setText("updated offer text");
		assertTrue("offer updated correctly", offersDao.update(offer));
		
		// retrieve updated offer
		Offer updated = offersDao.getOffer(offer.getId());
		
		assertEquals("updated offer should match retrieved offer", offer, updated);
		
		offersDao.delete(offer.getId());
		List <Offer> empty = offersDao.getOffers();
		
		assertEquals("offers list should be empty", 0, empty.size());
		
	}

}
