package com.coderwurst.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	private DataSource dataSource;
	
	
	private User user1 = new User("newTestUser1", "newTestUser1", "hellohello", "testUser1@mail.com", true, "user");
	private User user2 = new User("newTestUser2", "newTestUser2", "hellohello", "testUser2@mail.com", true, "user");
	private User user3 = new User("newTestUser3", "newTestUser3", "hellohello", "testUser3@mail.com", true, "user");
	private User user4 = new User("newTestUser4", "newTestUser4", "hellohello", "testUser4@mail.com", true, "user");
	
	
	// connect and prepare test DB before running tests
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		// clean out before running tests
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreateUserCreateUser() {
		// given
		User user = new User("newTestUser11", "newTestUser", "hellohello", "andrew@mail.com", true, "user");
		
		// then
		// user has been added
		// no longer applies after adding hibernate assertTrue("user creation should return true", usersDao.create(user));	
		usersDao.create(user);
		
		List <User> users = usersDao.getAllUsers();
		// only 1 record added
		assertEquals("users should contain 1 and only 1 user", 1, users.size());
		
		// user exists
		assertTrue("user exists in DB", usersDao.exists(user.getUsername()));
		
		// compare data added - uses .equals method in User Object
		assertEquals("created user should match retrieved user identically", user, users.get(0));
		
	}
	
	// JUnits after Hibernate
	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		
		List <User> users1 = usersDao.getAllUsers();
		
		assertEquals("1 item in returned list", 1, users1.size());		// ensure user added
		assertEquals("users should match", user1, users1.get(0));		// ensure right object matches
		
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		List <User> users2 = usersDao.getAllUsers();
		
		assertEquals("4 items in returned list", 4, users2.size());		// ensure user added
	}
	
	@Test
	@Ignore
	public void getAllUsers() {
		
	}
	
	@Test
	@Ignore
	public void exists() {
		
	}

}
