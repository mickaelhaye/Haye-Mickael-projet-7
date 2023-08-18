package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;

public interface RatingService {

	public Iterable<Rating> getRatings();

	public Rating addRating(Rating rating);

	public Rating getRatingById(Integer id) throws Exception;

	public void delRating(Rating rating);

}
