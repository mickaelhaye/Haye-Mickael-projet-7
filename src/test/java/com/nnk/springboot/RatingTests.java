package com.nnk.springboot;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * this class is to test for the entity Rating.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@SpringBootTest
public class RatingTests {

	/*
	 * @Autowired private RatingRepository ratingRepository;
	 * 
	 * @Test public void ratingTest() { Rating rating = new Rating("Moodys Rating",
	 * "Sand PRating", "Fitch Rating", 10);
	 * 
	 * // Save rating = ratingRepository.save(rating);
	 * Assert.assertNotNull(rating.getId());
	 * Assert.assertTrue(rating.getOrderNumber() == 10);
	 * 
	 * // Update rating.setOrderNumber(20); rating = ratingRepository.save(rating);
	 * Assert.assertTrue(rating.getOrderNumber() == 20);
	 * 
	 * // Find List<Rating> listResult = ratingRepository.findAll();
	 * Assert.assertTrue(listResult.size() > 0);
	 * 
	 * // Delete Integer id = rating.getId(); ratingRepository.delete(rating);
	 * Optional<Rating> ratingList = ratingRepository.findById(id);
	 * Assert.assertFalse(ratingList.isPresent()); }
	 */
}
