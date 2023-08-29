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
 * this class is to test the LogoutController methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class LogoutControllerTest {
	@Autowired
	private MockMvc mockMvc;

	/**
	 * this method is to test the method logoutSuccess with the endpoint get
	 * /logout_success
	 * 
	 * @throws Exception standard
	 */
	@Test
	void logoutSuccessTest() throws Exception {
		mockMvc.perform(get("/logout_success")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("logout_success"));
	}
}
