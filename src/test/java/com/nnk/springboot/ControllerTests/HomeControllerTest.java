package com.nnk.springboot.ControllerTests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
	private User userTestADMIN = new User();
	private User userTestUSER = new User();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	/**
	 * this method is to add some Users in the dataBase
	 */
	@BeforeEach
	public void setUp() {

		userTestADMIN.setFullname("newFullname");
		userTestADMIN.setUsername("newUsernameADMIN");
		userTestADMIN.setPassword("Info06/17");
		userTestADMIN.setRole("ROLE_ADMIN");
		userService.addUser(userTestADMIN);

		userTestUSER.setFullname("newFullname");
		userTestUSER.setUsername("newUsernameUSER");
		userTestUSER.setPassword("Info06/17");
		userTestUSER.setRole("ROLE_USER");
		userService.addUser(userTestUSER);
	}

	/**
	 * this method is to test the method home with the endpoint get /
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("home"));
		mockMvc.perform(get("/").with(user(userTestUSER))).andExpect(status().isOk()).andDo(print());
	}

	/**
	 * this method is to test the method adminHome with the endpoint get /admin/home
	 * 
	 * @throws Exception standard
	 */

	@Test
	void adminHomeTest() throws Exception {
		mockMvc.perform(get("/admin/home").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/bidList/list"));
		mockMvc.perform(get("/admin/home").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}
}
