package com.nnk.springboot.ControllerTests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

/**
 * this class is to test the BidListContoller methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class BidListControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BidListService bidListService;

	/**
	 * this method is to add a BidList in the dataBase
	 */
	@BeforeEach
	public void setUp() {
		BidList bidList = new BidList();
		bidList.setAccount("newAccount");
		bidListService.addBidList(bidList);
	}

	/**
	 * this method is to test the method home with the endpoint get /bidList/list
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/bidList/list")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("bidList/list"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("bidLists"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("httpServletRequest"))
				.andExpect(MockMvcResultMatchers.content().string(containsString("newAccount")));

	}

	/**
	 * this method is to test the method addBidForm with the endpoint get
	 * /bidList/add
	 * 
	 * @throws Exception standard
	 */
	@Test
	void addBidFormTest() throws Exception {
		mockMvc.perform(get("/bidList/add")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("bidList/add"));
	}

	/**
	 * this method is to test the method validate with the endpoint post
	 * /bidList/validate
	 * 
	 * @throws Exception standard
	 */
	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/bidList/validate")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/bidList/list"));
	}

	/**
	 * this method is to test the method showUpdateForm with the endpoint get
	 * /bidList/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void showUpdateFormTest() throws Exception {
		mockMvc.perform(get("/bidList/update/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/bidList/list"));
		mockMvc.perform(get("/bidList/update/1")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("bidList/update"));
	}

	/**
	 * this method is to test the method updateBid with the endpoint post
	 * /bidList/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void updateBidTest() throws Exception {
		mockMvc.perform(post("/bidList/update/1")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/bidList/list"));

	}

	/**
	 * this method is to test the method deleteBid with the endpoint post
	 * /bidList/delete/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void deleteBidTest() throws Exception {
		mockMvc.perform(get("/bidList/delete/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/bidList/list"));

		mockMvc.perform(get("/bidList/delete/1")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/bidList/list"));
	}

}
