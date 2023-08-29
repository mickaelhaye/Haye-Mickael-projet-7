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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

/**
 * this class is to test the TradeContoller methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class TradeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TradeService tradeService;

	/**
	 * this method is to add a TradeList in the dataBase
	 */
	@BeforeEach
	public void setUp() {
		Trade trade = new Trade();
		trade.setAccount("newAccount");
		tradeService.addTrade(trade);
	}

	/**
	 * this method is to test the method home with the endpoint get /trade/list
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/trade/list")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("trade/list"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("trades"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("httpServletRequest"))
				.andExpect(MockMvcResultMatchers.content().string(containsString("newAccount")));

	}

	/**
	 * this method is to test the method addTradeForm with the endpoint get
	 * /trade/add
	 * 
	 * @throws Exception standard
	 */
	@Test
	void addTradeFormTest() throws Exception {

		mockMvc.perform(get("/trade/add")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("trade/add"));
	}

	/**
	 * this method is to test the method validate with the endpoint post
	 * /trade/validate
	 * 
	 * @throws Exception standard
	 */
	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/trade/validate")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/trade/list"));
	}

	/**
	 * this method is to test the method showUpdateForm with the endpoint get
	 * /trade/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/trade/update/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/trade/list"));

		mockMvc.perform(get("/trade/update/1")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("trade/update"));

	}

	/**
	 * this method is to test the method updateTrade with the endpoint post
	 * /trade/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void updateTradeTest() throws Exception {
		mockMvc.perform(post("/trade/update/1")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/trade/list"));

	}

	/**
	 * this method is to test the method deleteTrade with the endpoint post
	 * /trade/delete/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void deleteTradeTest() throws Exception {
		mockMvc.perform(get("/trade/delete/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/trade/list"));

		mockMvc.perform(get("/trade/delete/1")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/trade/list"));
	}

}
