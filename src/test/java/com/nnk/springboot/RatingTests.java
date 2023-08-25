package com.nnk.springboot;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

/**
 * this class is to test for the entity Rating.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

	@Autowired
	private RatingService ratingService;

	@Test
	public void ratingTest() {
		Rating rating = new Rating();
		rating.setMoodysRating("Moodys Rating");
		rating.setSandPRating("Sand PRating");
		rating.setFitchRating("Fitch Rating");
		rating.setOrder(10);

		// Save
		rating = ratingService.addRating(rating);
		assertNotNull(rating.getId());
		assertTrue(rating.getOrder() == 10);

		// Update
		rating.setOrder(20);
		rating = ratingService.addRating(rating);
		assertTrue(rating.getOrder() == 20);

		// Find
		List<Rating> listResult = (List<Rating>) ratingService.getRatings();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rating.getId();
		ratingService.delRating(rating);
		Rating ratingList = null;
		try {
			ratingList = ratingService.getRatingById(id);
		} catch (Exception e) {
			assertNull(ratingList);
		}
	}

}
