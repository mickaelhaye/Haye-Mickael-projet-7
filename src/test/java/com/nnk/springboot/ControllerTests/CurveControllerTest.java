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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.UserService;

/**
 * this class is to test the CurveContoller methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@AutoConfigureMockMvc
@SpringBootTest
class CurveControllerTest {
	private User userTestADMIN = new User();
	private User userTestUSER = new User();
	private CurvePoint curvePoint = new CurvePoint();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CurvePointService curvePointService;

	@Autowired
	private UserService userService;

	/**
	 * this method is to add a CurveList in the dataBase
	 */
	@BeforeEach
	public void setUp() {

		curvePoint.setTerm(10d);
		curvePoint.setValue(20d);
		curvePointService.addCurvePoint(curvePoint);

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
	 * this method is to test the method home with the endpoint get /curvePoint/list
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/curvePoint/list").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("curvePoint/list")).andExpect(model().attributeExists("curvePoints"))
				.andExpect(model().attributeExists("httpServletRequest"));

		mockMvc.perform(get("/curvePoint/list").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

	/**
	 * this method is to test the method addCurveForm with the endpoint get
	 * /curvePoint/add
	 * 
	 * @throws Exception standard
	 */
	@Test
	void addCurveFormTest() throws Exception {

		mockMvc.perform(get("/curvePoint/add").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("curvePoint/add"));

		mockMvc.perform(get("/curvePoint/add").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

	/**
	 * this method is to test the method validate with the endpoint post
	 * /curvePoint/validate
	 * 
	 * @throws Exception standard
	 */
	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/curvePoint/validate").with(user(userTestADMIN)).with(csrf()))
				.andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(view().name("redirect:/curvePoint/list"));

		mockMvc.perform(post("/curvePoint/validate").with(user(userTestUSER)).with(csrf()))
				.andExpect(status().is4xxClientError()).andDo(print());
	}

	/**
	 * this method is to test the method showUpdateForm with the endpoint get
	 * /curvePoint/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/curvePoint/update/0").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/curvePoint/list"));

		mockMvc.perform(get("/curvePoint/update/1").with(user(userTestADMIN))).andExpect(status().isOk()).andDo(print())
				.andExpect(view().name("curvePoint/update"));

		mockMvc.perform(get("/curvePoint/update/0").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());

		mockMvc.perform(get("/curvePoint/update/1").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());

	}

	/**
	 * this method is to test the method updateCurve with the endpoint post
	 * /curvePoint/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void updateCurveTest() throws Exception {
		mockMvc.perform(post("/curvePoint/update/1").with(user(userTestADMIN)).with(csrf()))
				.andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(view().name("redirect:/curvePoint/list"));

		mockMvc.perform(post("/curvePoint/update/1").with(user(userTestUSER)).with(csrf()))
				.andExpect(status().is4xxClientError()).andDo(print());
	}

	/**
	 * this method is to test the method deleteCurve with the endpoint post
	 * /curvePoint/delete/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void deleteCurveTest() throws Exception {
		mockMvc.perform(get("/curvePoint/delete/0").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/curvePoint/list"));

		mockMvc.perform(get("/curvePoint/delete/1").with(user(userTestADMIN))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(view().name("redirect:/curvePoint/list"));

		mockMvc.perform(get("/curvePoint/delete/0").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());

		mockMvc.perform(get("/curvePoint/delete/1").with(user(userTestUSER))).andExpect(status().is4xxClientError())
				.andDo(print());
	}

}
