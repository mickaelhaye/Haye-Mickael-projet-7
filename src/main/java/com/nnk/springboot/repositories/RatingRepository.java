package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.Rating;

/**
 * this interface is to manage the exchange with the database for the entity
 * Rating.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
