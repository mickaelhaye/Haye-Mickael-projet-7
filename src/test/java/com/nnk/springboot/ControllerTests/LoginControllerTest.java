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
 * this class is to test the LoginController methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@AutoConfigureMockMvc()
@SpringBootTest
class LoginControllerTest {
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

		userTestUSER.setFullname("newFullname");
		userTestUSER.setUsername("newUsernameUSER");
		userTestUSER.setPassword("Info06/17");
		userTestUSER.setRole("ROLE_USER");
		userService.addUser(userTestUSER);
	}

	/**
	 * this method is to test the method login with the endpoint get /app/login
	 * 
	 * @throws Exception standard
	 */
	@Test
	void loginTest() throws Exception {
		mockMvc.perform(get("/app/login")).andExpect(status().isOk()).andDo(print()).andExpect(view().name("login"));
	}

	/**
	 * this method is to test the method loginSuccess with the endpoint get
	 * /app/login_success
	 * 
	 * @throws Exception standard
	 */
	@Test
	void loginSuccessTest() throws Exception {
		mockMvc.perform(get("/app/login_success").with(user(userTestUSER))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("login_success"));
		mockMvc.perform(get("/app/login_success")).andExpect(status().isOk()).andDo(print());

	}

	/**
	 * this method is to test the method loginError with the endpoint get
	 * /app/login_error
	 * 
	 * @throws Exception standard
	 */
	@Test
	void loginErrorTest() throws Exception {
		mockMvc.perform(get("/app/login_error")).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("login_error"));
	}

	/**
	 * this method is to test the method getAllUserArticles with the endpoint get
	 * /app/secure/article-details
	 * 
	 * @throws Exception standard
	 */
	@Test
	void getAllUserArticlesTest() throws Exception {
		mockMvc.perform(get("/app/secure/article-details")).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("user/list"));
	}

	/**
	 * this method is to test the method error with the endpoint get /app/error
	 * 
	 * @throws Exception standard
	 */
	@Test
	void errorTest() throws Exception {
		mockMvc.perform(get("/app/error")).andExpect(status().isOk()).andDo(print()).andExpect(view().name("403"));
	}
}
