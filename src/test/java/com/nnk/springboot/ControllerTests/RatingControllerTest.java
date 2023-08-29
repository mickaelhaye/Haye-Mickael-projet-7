package com.nnk.springboot.ControllerTests;

import static org.hamcrest.CoreMatchers.containsString;
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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.UserService;

/**
 * this class is to test the RatingContoller methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class RatingControllerTest {
	private User userTest = new User();
	private Rating rating = new Rating();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RatingService ratingService;

	@Autowired
	private UserService userService;

	/**
	 * this method is to add a RatingList in the dataBase
	 */
	@BeforeEach
	public void setUp() {
		rating.setMoodysRating("newMoodysRating");
		rating.setSandPRating("newSandPrating");
		rating.setFitchRating("newFitchPrating");
		rating.setOrder(3);
		ratingService.addRating(rating);

		userTest.setFullname("newFullname");
		userTest.setUsername("newUsername");
		userTest.setPassword("Info06/17");
		userTest.setRole("ROLE_ADMIN");
		userService.addUser(userTest);
	}

	/**
	 * this method is to test the method home with the endpoint get /rating/list
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/rating/list").with(user(userTest))).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("rating/list"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("ratings"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("httpServletRequest"))
				.andExpect(MockMvcResultMatchers.content().string(containsString("newSandPrating")));
	}

	/**
	 * this method is to test the method addRatingForm with the endpoint get
	 * /rating/add
	 * 
	 * @throws Exception standard
	 */
	@Test
	void addRatingFormTest() throws Exception {

		mockMvc.perform(get("/rating/add").with(user(userTest))).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("rating/add"));
	}

	/**
	 * this method is to test the method validate with the endpoint post
	 * /rating/validate
	 * 
	 * @throws Exception standard
	 */
	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/rating/validate").with(user(userTest))).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));
	}

	/**
	 * this method is to test the method showUpdateForm with the endpoint get
	 * /rating/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/rating/update/0").with(user(userTest))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));

		mockMvc.perform(get("/rating/update/1").with(user(userTest))).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("rating/update"));

	}

	/**
	 * this method is to test the method updateRating with the endpoint post
	 * /rating/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void updateRatingTest() throws Exception {
		mockMvc.perform(post("/rating/update/1").with(user(userTest))).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));

	}

	/**
	 * this method is to test the method deleteRating with the endpoint post
	 * /rating/delete/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void deleteRatingTest() throws Exception {

		mockMvc.perform(get("/rating/delete/0").with(user(userTest))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));

		mockMvc.perform(get("/rating/delete/1").with(user(userTest))).andExpect(status().is3xxRedirection())
				.andDo(print()).andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));
	}

}
