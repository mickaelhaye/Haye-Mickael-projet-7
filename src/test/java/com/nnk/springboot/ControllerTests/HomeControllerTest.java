package com.nnk.springboot.ControllerTests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

/**
 * this class is to test the HomeController methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc()
class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	/**
	 * this method is to test the method home with the endpoint get /
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("home"));
	}

	/**
	 * this method is to test the method adminHome with the endpoint get /admin/home
	 * 
	 * @throws Exception standard
	 */

	@Test
	void adminHomeTest() throws Exception {
		User userTest = new User();
		userTest.setFullname("newFullname");
		userTest.setUsername("newUsername");
		userTest.setPassword("Info06/17");
		userTest.setRole("ROLE_ADMIN");
		userService.addUser(userTest);
		mockMvc.perform(get("/admin/home").with(user(userTest))).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/bidList/list"));
	}
}
