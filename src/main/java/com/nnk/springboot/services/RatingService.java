package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;

/**
 * this interface is the service for the entity Rating.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface RatingService {

	/**
	 * this method recovers all Rating.
	 * 
	 * @return return a list of Rating.
	 */
	public Iterable<Rating> getRatings();

	/**
	 * this method saves or updates a Rating.
	 * 
	 * @param rating saves or updates.
	 * @return Rating with id.
	 */
	public Rating addRating(Rating rating);

	/**
	 * this method recovers a CurvePoint by this id.
	 * 
	 * @param id of the Rating.
	 * @return return the Rating by id.
	 * @throws Exception Rating not found with this id.
	 */
	public Rating getRatingById(Integer id) throws Exception;

	/**
	 * this method deletes a CurvePoint.
	 * 
	 * @param rating deletes.
	 */
	public void delRating(Rating rating);

}
