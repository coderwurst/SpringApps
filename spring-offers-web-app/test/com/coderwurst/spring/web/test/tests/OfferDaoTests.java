package com.coderwurst.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
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
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		// clean out before running tests
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreate() {
		// given
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.create(offer1);
		
		// then
		List <Offer> offers1 = offersDao.getOffers();
				
		assertEquals("should return offer for 1 users", 1, offers1.size());
		assertEquals("retrieved offer should equal inserted offer", offer1, offers1.get(0));
				
		// continue to create dummy data
		offersDao.create(offer2);
		offersDao.create(offer3);
		offersDao.create(offer4);
		offersDao.create(offer5);
		offersDao.create(offer6);
		offersDao.create(offer7);	// user 4 is not enabled
		
		// then
		List <Offer> offers2 = offersDao.getOffers();
		
		assertEquals("should return offers for ENABLED users", 6, offers2.size());
		
	}
	
	@Test
	public void testCreateUserCreateOffer() {
		// given
		User user = new User("newTestUser22", "newTestUser", "hellohello", "andrew@mail.com", true, "user");
		// no longer needed after adding hibernate assertTrue("user creation should return true", usersDao.create(user));
		usersDao.create(user);
		
		Offer offer = new Offer(user, "this is a test offer");
		
		// then
		// TEST offer has been added to DB
		offersDao.create(offer);	
		
		List <Offer> offers = offersDao.getOffers();
		// TEST only 1 record added
		assertEquals("offers should contain 1 and only 1 user", 1, offers.size());
		
		// compare data added - offer.equals method in Offer Object (ID omitted as can only be determined after adding to DB)
		assertEquals("created offer should match retrieved offer identically", offer, offers.get(0));
		
		// TEST get offer to perform an update
		offer = offers.get(0);
		offer.setText("updated offer text");
		assertTrue("offer updated correctly", offersDao.update(offer));
		
		// retrieve updated offer
		Offer updated = offersDao.getOffer(offer.getId());
		
		assertEquals("updated offer should match retrieved offer", offer, updated);
		
		Offer offer2 = new Offer(user, "This is a test offer");
		offersDao.create(offer2);
		
		// TEST get by username
		List <Offer> userOffers = offersDao.getOffers(user.getUsername());
		assertEquals("should be 2 offers in DB for username", 2, userOffers.size());
		// TODO check offers added for matching text
		
		// TEST get by ID
		List <Offer> secondList = offersDao.getOffers();
		
		for (Offer current : secondList) {
			Offer retrieved = offersDao.getOffer(current.getId());
			assertEquals("both current and retrieved should match", current, retrieved);
		}
		
		// TEST delete
		offersDao.delete(offer.getId());
		List <Offer> empty = offersDao.getOffers();
		
		assertEquals("offers list should be empty", 1, empty.size());
		
	}
}
