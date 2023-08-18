package com.nnk.springboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Iterable<Rating> getRatings() {
		return ratingRepository.findAll();
	}

	@Override
	public Rating addRating(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public Rating getRatingById(Integer id) throws Exception {
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id:" + id));
		return rating;
	}

	@Override
	public void delRating(Rating rating) {
		ratingRepository.delete(rating);
	}

}
