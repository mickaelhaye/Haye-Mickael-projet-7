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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.RuleNameService;
import com.nnk.springboot.services.UserService;

/**
 * this class is to test the RuleNameContoller methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@AutoConfigureMockMvc
@SpringBootTest
class RuleNameControllerTest {
	private User userTestADMIN = new User();
	private User userTestUSER = new User();
	private RuleName ruleName = new RuleName();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RuleNameService ruleNameService;

	@Autowired
	private UserService userService;

	/**
	 * this method is to add a RuleNameList in the dataBase
	 */
	@BeforeEach
	public void setUp() {

		ruleName.setName("newName");
		ruleName.setDescription("newDescription");
		ruleName.setJson("newJson");
		ruleName.setTemplate("newTemplate");
		ruleName.setSql("newSql");
		ruleName.setSqlPart("newSqlpart");
		ruleNameService.addRuleName(ruleName);

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
	 * this method is to test the method home with the endpoint get /ruleName/list
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/ruleName/list").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("ruleName/list")).andExpect(model().attributeExists("ruleNames"))
				.andExpect(model().attributeExists("httpServletRequest"));

		mockMvc.perform(get("/ruleName/list").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

	/**
	 * this method is to test the method addRuleNameForm with the endpoint get
	 * /ruleName/add
	 * 
	 * @throws Exception standard
	 */
	@Test
	void addRuleNameFormTest() throws Exception {

		mockMvc.perform(get("/ruleName/add").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("ruleName/add"));

		mockMvc.perform(get("/ruleName/add").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

	/**
	 * this method is to test the method validate with the endpoint post
	 * /ruleName/validate
	 * 
	 * @throws Exception standard
	 */
	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/ruleName/validate").with(user(userTestADMIN)).with(csrf()))
				.andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(view().name("redirect:/ruleName/list"));

		mockMvc.perform(post("/ruleName/validate").with(user(userTestUSER)).with(csrf()))
				.andExpect(status().is4xxClientError()).andDo(print());
	}

	/**
	 * this method is to test the method showUpdateForm with the endpoint get
	 * /ruleName/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/ruleName/update/0").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/ruleName/list"));

		mockMvc.perform(get("/ruleName/update/1").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("ruleName/update"));

		mockMvc.perform(get("/ruleName/update/0").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());

		mockMvc.perform(get("/ruleName/update/1").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());

	}

	/**
	 * this method is to test the method updateRuleName with the endpoint post
	 * /ruleName/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void updateRuleNameTest() throws Exception {
		mockMvc.perform(post("/ruleName/update/1").with(user(userTestADMIN)).with(csrf()))
				.andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(view().name("redirect:/ruleName/list"));

		mockMvc.perform(post("/ruleName/update/1").with(user(userTestUSER)).with(csrf()))
				.andExpect(status().is4xxClientError()).andDo(print());
	}

	/**
	 * this method is to test the method deleteRuleName with the endpoint post
	 * /ruleName/delete/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void deleteRuleNameTest() throws Exception {
		mockMvc.perform(get("/ruleName/delete/0").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/ruleName/list"));

		mockMvc.perform(get("/ruleName/delete/1").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/ruleName/list"));

		mockMvc.perform(get("/ruleName/delete/0").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());

		mockMvc.perform(get("/ruleName/delete/1").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

}