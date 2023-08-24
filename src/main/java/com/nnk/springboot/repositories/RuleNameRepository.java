package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.RuleName;

/**
 * this interface is to manage the exchange with the database for the entity
 * RuleName.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
