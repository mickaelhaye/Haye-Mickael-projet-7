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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

/**
 * this class is to test the RatingContoller methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class RatingControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RatingService ratingService;

	/**
	 * this method is to add a RatingList in the dataBase
	 */
	@BeforeEach
	public void setUp() {
		Rating rating = new Rating();
		rating.setSandPRating("newSandPrating");
		ratingService.addRating(rating);
	}

	/**
	 * this method is to test the method home with the endpoint get /rating/list
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/rating/list")).andExpect(status().isOk()).andDo(print())
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

		mockMvc.perform(get("/rating/add")).andExpect(status().isOk()).andDo(print())
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
		mockMvc.perform(post("/rating/validate")).andExpect(status().is3xxRedirection())
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

		mockMvc.perform(get("/rating/update/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));

		mockMvc.perform(get("/rating/update/1")).andExpect(status().isOk()).andDo(print())
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
		mockMvc.perform(post("/rating/update/1")).andExpect(status().is3xxRedirection())
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

		mockMvc.perform(get("/rating/delete/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));

		mockMvc.perform(get("/rating/delete/1")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));
	}

}
