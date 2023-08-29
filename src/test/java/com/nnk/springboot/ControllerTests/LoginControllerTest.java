package com.nnk.springboot.ControllerTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * this class is to test the LoginController methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class LoginControllerTest {
	@Autowired
	private MockMvc mockMvc;

	/**
	 * this method is to test the method login with the endpoint get /app/login
	 * 
	 * @throws Exception standard
	 */
	@Test
	void loginTest() throws Exception {
		mockMvc.perform(get("/app/login")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("login"));
	}

	/**
	 * this method is to test the method loginSuccess with the endpoint get
	 * /app/login_success
	 * 
	 * @throws Exception standard
	 */
	@Test
	void loginSuccessTest() throws Exception {
		mockMvc.perform(get("/app/login_success")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("login_success"));
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
				.andExpect(MockMvcResultMatchers.view().name("login_error"));
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
				.andExpect(MockMvcResultMatchers.view().name("user/list"));
	}

	/**
	 * this method is to test the method error with the endpoint get /app/error
	 * 
	 * @throws Exception standard
	 */
	@Test
	void errorTest() throws Exception {
		mockMvc.perform(get("/app/error")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("403"));
	}
}
