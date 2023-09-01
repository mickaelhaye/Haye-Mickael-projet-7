package com.nnk.springboot.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

/**
 * this class is the service for the entity Rating.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Service
public class RatingServiceImpl implements RatingService {
	private static Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);
	@Autowired
	private RatingRepository ratingRepository;

	/**
	 * this method recovers all Rating.
	 * 
	 * @return return a list of Rating.
	 */
	@Override
	public Iterable<Rating> getRatings() {
		logger.debug("getRatings");
		return ratingRepository.findAll();
	}

	/**
	 * this method saves or updates a Rating.
	 * 
	 * @param rating saves or updates.
	 * @return Rating with id.
	 */
	@Override
	public Rating addRating(Rating rating) {
		logger.debug("addRating");
		return ratingRepository.save(rating);
	}

	/**
	 * this method recovers a CurvePoint by this id.
	 * 
	 * @param id of the Rating.
	 * @return return the Rating by id.
	 * @throws Exception Rating not found with this id.
	 */
	@Override
	public Rating getRatingById(Integer id) throws Exception {
		logger.debug("getRatingById");
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id:" + id));
		return rating;
	}

	/**
	 * this method deletes a CurvePoint.
	 * 
	 * @param rating deletes.
	 */
	@Override
	public void delRating(Rating rating) {
		logger.debug("delRating");
		ratingRepository.delete(rating);
	}

}
