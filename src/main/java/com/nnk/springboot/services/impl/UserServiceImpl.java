package com.nnk.springboot.services.impl;

import java.util.Optional;

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
		userRepository.delete(user);
	}

	/**
	 * this method sends the updated username.
	 * 
	 * @return the updated username
	 */
	@Override
	public String getUserNameUpdating() {
		return userNameUpdating;
	}

	/**
	 * this method saves the updated username.
	 * 
	 * @param userNameUpdating username updated
	 */
	@Override
	public void setUserNameUpdating(String userNameUpdating) {
		this.userNameUpdating = userNameUpdating;
	}

}
