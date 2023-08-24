package com.nnk.springboot.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

/**
 * this class is the service for the entity User.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	private String userNameUpdating;

	/**
	 * this method is to recover all User.
	 * 
	 * @return return a list of User.
	 */
	@Override
	public Iterable<User> getUsers() {
		logger.debug("getUsers");
		return userRepository.findAll();
	}

	/**
	 * this method is to save or to update an User
	 * 
	 * @param user saves or updates.
	 * @return User with id.
	 */
	@Override
	public User addUser(User user) {
		logger.debug("addUser");
		return userRepository.save(user);
	}

	/**
	 * this method is to recover a User by this id
	 * 
	 * @param id of the User.
	 * @return return the User by id.
	 * @throws Exception User not found with this id.
	 */
	@Override
	public User getUserById(Integer id) throws Exception {
		logger.debug("getUserById");
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
		return user;
	}

	/**
	 * this method recovers a User by this Username.
	 * 
	 * @param username of the user.
	 * @return the user by username.
	 */
	@Override
	public boolean getUserByUsername(String username) {
		logger.debug("getUserByUsername");
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			return true;
		}

		return false;
	}

	/**
	 * this method is to delete a User.
	 * 
	 * @param user deletes.
	 */
	@Override
	public void delUser(User user) {
		logger.debug("delUser");
		userRepository.delete(user);
	}

	/**
	 * this method sends the updated username.
	 * 
	 * @return the updated username
	 */
	@Override
	public String getUserNameUpdating() {
		logger.debug("getUserNameUpdating");
		return userNameUpdating;
	}

	/**
	 * this method saves the updated username.
	 * 
	 * @param userNameUpdating username updated
	 */
	@Override
	public void setUserNameUpdating(String userNameUpdating) {
		logger.debug("setUserNameUpdating");
		this.userNameUpdating = userNameUpdating;
	}

}
