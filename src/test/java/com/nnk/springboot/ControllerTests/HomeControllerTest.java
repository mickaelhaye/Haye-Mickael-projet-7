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
 * this class is to test the HomeController methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;

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
		mockMvc.perform(get("/admin/home")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/bidList/list"));
	}
}
