package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.BidList;

/**
 * this interface is to manage the exchange with the database for the entity
 * BidList.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
