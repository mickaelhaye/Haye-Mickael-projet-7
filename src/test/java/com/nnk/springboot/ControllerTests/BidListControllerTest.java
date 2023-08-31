package com.nnk.springboot.ControllerTests;

import static org.hamcrest.CoreMatchers.containsString;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.UserService;

/**
 * this class is to test the BidListContoller methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */

@SpringBootTest
@AutoConfigureMockMvc
class BidListControllerTest {
	private User userTestADMIN = new User();
	private User userTestUSER = new User();
	private BidList bidList = new BidList();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BidListService bidListService;

	@Autowired
	private UserService userService;

	/**
	 * this method is to add a BidList in the dataBase
	 */
	@BeforeEach
	public void setUp() {

		bidList.setAccount("newAccount");
		bidList.setType("newType");
		bidList.setBidQuantity(10d);
		bidListService.addBidList(bidList);

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
	 * this method is to test the method home with the endpoint get /bidList/list
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/bidList/list").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("bidList/list")).andExpect(model().attributeExists("bidLists"))
				.andExpect(model().attributeExists("httpServletRequest"))
				.andExpect(MockMvcResultMatchers.content().string(containsString("newAccount")));

		mockMvc.perform(get("/bidList/list").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

	/**
	 * this method is to test the method addBidForm with the endpoint get
	 * /bidList/add
	 * 
	 * @throws Exception standard
	 */
	@Test
	void addBidFormTest() throws Exception {
		mockMvc.perform(get("/bidList/add").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("bidList/add"));

		mockMvc.perform(get("/bidList/add").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

	/**
	 * this method is to test the method validate with the endpoint post
	 * /bidList/validate
	 * 
	 * @throws Exception standard
	 */
	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/bidList/validate").with(user(userTestADMIN)).with(csrf()))
				.andExpect(status().is3xxRedirection()).andDo(print()).andExpect(view().name("redirect:/bidList/list"));

		mockMvc.perform(post("/bidList/validate").with(user(userTestUSER)).with(csrf()))
				.andExpect(status().is4xxClientError()).andDo(print());
	}

	/**
	 * this method is to test the method showUpdateForm with the endpoint get
	 * /bidList/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void showUpdateFormTest() throws Exception {
		mockMvc.perform(get("/bidList/update/0").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/bidList/list"));

		mockMvc.perform(get("/bidList/update/1").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("bidList/update"));

		mockMvc.perform(get("/bidList/update/0").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());

		mockMvc.perform(get("/bidList/update/1").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

	/**
	 * this method is to test the method updateBid with the endpoint post
	 * /bidList/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void updateBidTest() throws Exception {
		mockMvc.perform(post("/bidList/update/1").with(user(userTestADMIN)).with(csrf()))
				.andExpect(status().is3xxRedirection()).andDo(print()).andExpect(view().name("redirect:/bidList/list"));

		mockMvc.perform(post("/bidList/update/1").with(user(userTestUSER)).with(csrf()))
				.andExpect(status().is4xxClientError()).andDo(print());
	}

	/**
	 * this method is to test the method deleteBid with the endpoint post
	 * /bidList/delete/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void deleteBidTest() throws Exception {
		mockMvc.perform(get("/bidList/delete/0").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/bidList/list"));

		mockMvc.perform(get("/bidList/delete/1").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/bidList/list"));

		mockMvc.perform(get("/bidList/delete/0").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());

		mockMvc.perform(get("/bidList/delete/1").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

}
