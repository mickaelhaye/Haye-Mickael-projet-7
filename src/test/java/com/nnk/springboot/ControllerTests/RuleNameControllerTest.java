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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class RuleNameControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RuleNameService ruleNameService;

	@BeforeEach
	public void setUp() {
		RuleName ruleName = new RuleName();
		ruleName.setName("newName");
		ruleNameService.addRuleName(ruleName);
	}

	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/ruleName/list")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("ruleName/list"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("ruleNames"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("httpServletRequest"))
				.andExpect(MockMvcResultMatchers.content().string(containsString("newName")));

	}

	@Test
	void addRuleNameFormTest() throws Exception {

		mockMvc.perform(get("/ruleName/add")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("ruleName/add"));
	}

	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/ruleName/validate")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/ruleName/list"));
	}

	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/ruleName/update/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/ruleName/list"));

		mockMvc.perform(get("/ruleName/update/1")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("ruleName/update"));

	}

	@Test
	void updateRuleNameTest() throws Exception {
		mockMvc.perform(post("/ruleName/update/1")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/ruleName/list"));

	}

	@Test
	void deleteRuleNameTest() throws Exception {
		mockMvc.perform(get("/ruleName/delete/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/ruleName/list"));

		mockMvc.perform(get("/ruleName/delete/1")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/ruleName/list"));
	}

}
