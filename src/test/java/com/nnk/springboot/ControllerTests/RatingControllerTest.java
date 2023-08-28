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

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class RatingControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RatingService ratingService;

	@BeforeEach
	public void setUp() {
		Rating rating = new Rating();
		rating.setSandPRating("newSandPrating");
		ratingService.addRating(rating);
	}

	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/rating/list")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("rating/list"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("ratings"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("httpServletRequest"))
				.andExpect(MockMvcResultMatchers.content().string(containsString("newSandPrating")));

	}

	@Test
	void addRatingFormTest() throws Exception {

		mockMvc.perform(get("/rating/add")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("rating/add"));
	}

	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/rating/validate")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));
	}

	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/rating/update/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));

		mockMvc.perform(get("/rating/update/1")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("rating/update"));

	}

	@Test
	void updateRatingTest() throws Exception {
		mockMvc.perform(post("/rating/update/1")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));

	}

	@Test
	void deleteRatingTest() throws Exception {

		mockMvc.perform(get("/rating/delete/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));

		mockMvc.perform(get("/rating/delete/1")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/rating/list"));
	}

}
