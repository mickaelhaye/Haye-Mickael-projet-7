package com.nnk.springboot.ControllerTests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.TradeService;
import com.nnk.springboot.services.UserService;

/**
 * this class is to test the TradeContoller methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@AutoConfigureMockMvc
@SpringBootTest
class TradeControllerTest {
	private User userTestADMIN = new User();
	private User userTestUSER = new User();
	private Trade trade = new Trade();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TradeService tradeService;

	@Autowired
	private UserService userService;

	/**
	 * this method is to add a TradeList in the dataBase
	 */
	@BeforeEach
	public void setUp() {

		trade.setAccount("newAccount");
		trade.setType("newType");
		trade.setBuyQuantity(10d);
		tradeService.addTrade(trade);

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
	 * this method is to test the method home with the endpoint get /trade/list
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/trade/list").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("trade/list")).andExpect(model().attributeExists("trades"))
				.andExpect(model().attributeExists("httpServletRequest"));

		mockMvc.perform(get("/trade/list").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

	/**
	 * this method is to test the method addTradeForm with the endpoint get
	 * /trade/add
	 * 
	 * @throws Exception standard
	 */
	@Test
	void addTradeFormTest() throws Exception {

		mockMvc.perform(get("/trade/add").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("trade/add"));

		mockMvc.perform(get("/trade/add").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

	/**
	 * this method is to test the method validate with the endpoint post
	 * /trade/validate
	 * 
	 * @throws Exception standard
	 */
	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/trade/validate").with(user(userTestADMIN)).with(csrf()))
				.andExpect(status().is3xxRedirection()).andDo(print()).andExpect(view().name("redirect:/trade/list"));

		mockMvc.perform(post("/trade/validate").with(user(userTestUSER)).with(csrf()))
				.andExpect(status().is4xxClientError()).andDo(print());
	}

	/**
	 * this method is to test the method showUpdateForm with the endpoint get
	 * /trade/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/trade/update/0").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/trade/list"));

		mockMvc.perform(get("/trade/update/1").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("trade/update"));

		mockMvc.perform(get("/trade/update/0").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());

		mockMvc.perform(get("/trade/update/1").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());

	}

	/**
	 * this method is to test the method updateTrade with the endpoint post
	 * /trade/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void updateTradeTest() throws Exception {
		mockMvc.perform(post("/trade/update/1").with(user(userTestADMIN)).with(csrf()))
				.andExpect(status().is3xxRedirection()).andDo(print()).andExpect(view().name("redirect:/trade/list"));

		mockMvc.perform(post("/trade/update/1").with(user(userTestUSER)).with(csrf()))
				.andExpect(status().is4xxClientError()).andDo(print());
	}

	/**
	 * this method is to test the method deleteTrade with the endpoint post
	 * /trade/delete/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void deleteTradeTest() throws Exception {
		mockMvc.perform(get("/trade/delete/0").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/trade/list"));

		mockMvc.perform(get("/trade/delete/1").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/trade/list"));

		mockMvc.perform(get("/trade/delete/0").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());

		mockMvc.perform(get("/trade/delete/1").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

}