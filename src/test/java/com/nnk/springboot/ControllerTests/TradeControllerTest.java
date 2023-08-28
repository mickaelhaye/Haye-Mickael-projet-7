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

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class TradeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TradeService tradeService;

	@BeforeEach
	public void setUp() {
		Trade trade = new Trade();
		trade.setAccount("newAccount");
		tradeService.addTrade(trade);
	}

	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/trade/list")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("trade/list"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("trades"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("httpServletRequest"))
				.andExpect(MockMvcResultMatchers.content().string(containsString("newAccount")));

	}

	@Test
	void addTradeFormTest() throws Exception {

		mockMvc.perform(get("/trade/add")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("trade/add"));
	}

	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/trade/validate")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/trade/list"));
	}

	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/trade/update/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/trade/list"));

		mockMvc.perform(get("/trade/update/1")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("trade/update"));

	}

	@Test
	void updateTradeTest() throws Exception {
		mockMvc.perform(post("/trade/update/1")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/trade/list"));

	}

	@Test
	void deleteTradeTest() throws Exception {
		mockMvc.perform(get("/trade/delete/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/trade/list"));

		mockMvc.perform(get("/trade/delete/1")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/trade/list"));
	}

}
