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

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class LoginControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void loginTest() throws Exception {
		mockMvc.perform(get("/app/login")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("login"));
	}

	@Test
	void loginSuccessTest() throws Exception {
		mockMvc.perform(get("/app/login_success")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("login_success"));
	}

	@Test
	void loginErrorTest() throws Exception {
		mockMvc.perform(get("/app/login_error")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("login_error"));
	}

	@Test
	void getAllUserArticlesTest() throws Exception {
		mockMvc.perform(get("/app/secure/article-details")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("user/list"));
	}

	@Test
	void errorTest() throws Exception {
		mockMvc.perform(get("/app/error")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("403"));
	}
}
