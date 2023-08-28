package com.nnk.springboot.ServiceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

/**
 * this class is to test for the entity Trade.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

	@Autowired
	private UserService userService;

	/**
	 * methods to test save, update, find and delete entity User
	 */
	@Test
	public void userTest() {
		User user = new User();
		user.setUsername("username");

		// Save
		user = userService.addUser(user);
		assertNotNull(user.getId());
		assertTrue(user.getUsername().equals("username"));

		// Update
		user.setUsername("username Update");
		user = userService.addUser(user);
		assertTrue(user.getUsername().equals("username Update"));

		// Find
		List<User> listResult = (List<User>) userService.getUsers();
		assertTrue(listResult.size() > 0);
		assertTrue(userService.getUserByUsername("username Update"));
		assertFalse(userService.getUserByUsername("username"));

		// Delete
		Integer id = user.getId();
		User userList = null;
		try {
			userList = userService.getUserById(id);
			assertNotNull(userList);
		} catch (Exception e) {

		}
		userService.delUser(user);
		userList = null;
		try {
			userList = userService.getUserById(id);
		} catch (Exception e) {
			assertNull(userList);
		}

		// UserNameUpdating
		userService.setUserNameUpdating("username Update");
		assertEquals("username Update", userService.getUserNameUpdating());
	}
}
