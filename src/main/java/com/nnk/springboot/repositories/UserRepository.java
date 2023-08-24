package com.nnk.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.nnk.springboot.domain.User;

/**
 * this interface is to manage the exchange with the database for the entity
 * User.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
	/**
	 * this method is to recover an user with this username;
	 * 
	 * @param username of the user
	 * @return the user by username
	 */
	Optional<User> findByUsername(String username);
}
