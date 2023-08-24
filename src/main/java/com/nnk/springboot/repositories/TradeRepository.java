package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.Trade;

/**
 * this interface is to manage the exchange with the database for the entity
 * Trade.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
