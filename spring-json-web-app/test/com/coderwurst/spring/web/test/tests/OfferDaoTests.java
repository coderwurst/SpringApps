package com.coderwurst.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
public class OfferDaoTests {
	
	@Autowired
	private OffersDao offersDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private DataSource dataSource;
	
	// dummy data
	private User user1 = new User("newTestUser1", "newTestUser1", "hellohello", "testUser1@mail.com", true, "ROLE_USER");
	private User user2 = new User("newTestUser2", "newTestUser2", "hellohello", "testUser2@mail.com", true, "ROLE_USER");
	private User user3 = new User("newTestUser3", "newTestUser3", "hellohello", "testUser3@mail.com", true, "ROLE_USER");
	private User user4 = new User("newTestUser4", "newTestUser4", "hellohello", "testUser4@mail.com", false, "ROLE_USER");
	
	private Offer offer1 = new Offer(user1, "This is test offer 1 for user 1");
	private Offer offer2 = new Offer(user1, "This is test offer 2 for user 1");
	private Offer offer3 = new Offer(user2, "This is test offer 1 for user 2");
	private Offer offer4 = new Offer(user3, "This is test offer 1 for user 3");
	private Offer offer5 = new Offer(user3, "This is test offer 2 for user 3");
	private Offer offer6 = new Offer(user3, "This is test offer 3 for user 3");
	private Offer offer7 = new Offer(user4, "This is test offer 1 for user 4");
	
	// connect and prepare test DB before running tests
	@Before
	public void setup() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		// clean out before running tests
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
		
		createUsers();
		createOffers();
		
	}
	
	@Test
	public void testCreateRetrieve() {
		// given
		// users and offers created in @Before
		
		// then
		List <Offer> offers = offersDao.getOffers();
		
		assertEquals("should return all offers created for ENABLED users", 6, offers.size());
		
	}
	
	@Test
	public void testGetOffersWithUsername() {
				
		// user 2 should only have 1 offer
		List <Offer> offersForUser2 = offersDao.getOffers(user2.getUsername());
		
		assertEquals("user 2 should have 1 offer", 1, offersForUser2.size());
		
		// test user 3 who has 3 offers
		List <Offer> offersForUser3 = offersDao.getOffers(user3.getUsername());
		
		assertEquals("user 3 should have 3 offers", 3, offersForUser3.size());	
		
	}
	
	@Test
	public void testUpdate() {
		
		String updatedString = "new, updated text for offer 3";
		
		offer3.setText(updatedString);
		offersDao.saveOrUpdate(offer3);
		
		Offer retrieved = offersDao.getOffer(offer3.getId());
		
		assertEquals("offer 3 text should have been updated", updatedString, retrieved.getText());
			
	}
	
	@Test
	public void testDelete() {
		
		List <Offer> offersStart = offersDao.getOffers();
		
		assertEquals("should be created with all offers", 6, offersStart.size());
		
		offersDao.delete(offer2.getId());
		
		Offer retrieved1 = offersDao.getOffer(offer2.getId());
		
		assertNull("offer should be null/ deleted", retrieved1);
		
	}
	
	@Test
	public void getOffer() {
				
		Offer retrieved1 = offersDao.getOffer(offer4.getId());
		
		assertEquals("retieved should match initial offer", offer4, retrieved1);
		
		Offer retrieved2 = offersDao.getOffer(offer7.getId());
		
		assertNull("user is disabled therefore should not return", retrieved2);
		
	}
	
	// utility method to generate users
	private void createUsers() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
	}
	
	// utility method to generate offers
	private void createOffers() {
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		// user 4 is not enabled so will not count
		offersDao.saveOrUpdate(offer7);
	}
	
}
