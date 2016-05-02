package com.coderwurst.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

import com.coderwurst.spring.web.dao.Message;
import com.coderwurst.spring.web.dao.MessagesDao;
import com.coderwurst.spring.web.dao.Offer;
import com.coderwurst.spring.web.dao.OffersDao;
import com.coderwurst.spring.web.dao.User;
import com.coderwurst.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations={"classpath:com/coderwurst/spring/web/config/dao-context.xml", 
		"classpath:com/coderwurst/spring/web/config/security-context.xml",
		"classpath:com/coderwurst/spring/web/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTests {
	
	@Autowired
	private OffersDao offersDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private MessagesDao messagesDao;
	
	@Autowired
	private DataSource dataSource;
	
	// dummy data
	private User user1 = new User("newTestUser1", "newTestUser1", "hellohello", "testUser1@mail.com", true, "ROLE_USER");
	private User user2 = new User("newTestUser2", "newTestUser2", "hellohello", "testUser2@mail.com", true, "ROLE_USER");
	private User user3 = new User("newTestUser3", "newTestUser3", "hellohello", "testUser3@mail.com", true, "ROLE_USER");
	private User user4 = new User("newTestUser4", "newTestUser4", "hellohello", "testUser4@mail.com", false, "ROLE_USER");
	
	// connect and prepare test DB before running tests
	@Before
	public void setup() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		// clean out before running tests
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
		
		createUsers();
		
	}
	
	// TODO add more tests
	
	@Test
	public void testCreateRetrieve() {
		// given
		// users and offers created in @Before
		
		// then
		Message message1 = new Message("test subject 1", "test content 1", "issac newtown", "issac@mail.com", 
				user1.getUsername());
		
		messagesDao.saveOrUpdate(message1);
				
	}
	
	// utility method to generate users
	private void createUsers() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
	}
		
}
