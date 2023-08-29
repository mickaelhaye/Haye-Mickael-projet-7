package com.nnk.springboot.ControllerTests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class CurveControllerTest {
	private User userTest = new User();
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

		userTest.setFullname("newFullname");
		userTest.setUsername("newUsername");
		userTest.setPassword("Info06/17");
		userTest.setRole("ROLE_ADMIN");
		userService.addUser(userTest);
	}

	/**
	 * this method is to test the method home with the endpoint get /curvePoint/list
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/curvePoint/list").with(user(userTest))).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("curvePoint/list"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("curvePoints"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("httpServletRequest"));
	}

	/**
	 * this method is to test the method addCurveForm with the endpoint get
	 * /curvePoint/add
	 * 
	 * @throws Exception standard
	 */
	@Test
	void addCurveFormTest() throws Exception {

		mockMvc.perform(get("/curvePoint/add").with(user(userTest))).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("curvePoint/add"));
	}

	/**
	 * this method is to test the method validate with the endpoint post
	 * /curvePoint/validate
	 * 
	 * @throws Exception standard
	 */
	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/curvePoint/validate").with(user(userTest))).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/curvePoint/list"));
	}

	/**
	 * this method is to test the method showUpdateForm with the endpoint get
	 * /curvePoint/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/curvePoint/update/0").with(user(userTest))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(MockMvcResultMatchers.view().name("redirect:/curvePoint/list"));

		mockMvc.perform(get("/curvePoint/update/1").with(user(userTest))).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("curvePoint/update"));

	}

	/**
	 * this method is to test the method updateCurve with the endpoint post
	 * /curvePoint/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void updateCurveTest() throws Exception {
		mockMvc.perform(post("/curvePoint/update/1").with(user(userTest))).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/curvePoint/list"));

	}

	/**
	 * this method is to test the method deleteCurve with the endpoint post
	 * /curvePoint/delete/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void deleteCurveTest() throws Exception {
		mockMvc.perform(get("/curvePoint/delete/0").with(user(userTest))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(MockMvcResultMatchers.view().name("redirect:/curvePoint/list"));

		mockMvc.perform(get("/curvePoint/delete/1").with(user(userTest))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(MockMvcResultMatchers.view().name("redirect:/curvePoint/list"));
	}

}
