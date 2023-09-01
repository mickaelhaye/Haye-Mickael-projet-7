package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.CurvePoint;

/**
 * this interface is to manage the exchange with the database for the entity
 * CurvePoint.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
