package com.nnk.springboot.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	private String userNameUpdating;

	@Override
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Integer id) throws Exception {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
		return user;
	}

	@Override
	public boolean getUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			return true;
		}

		return false;
	}

	@Override
	public void delUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public String getUserNameUpdating() {
		return userNameUpdating;
	}

	@Override
	public void setUserNameUpdating(String userNameUpdating) {
		this.userNameUpdating = userNameUpdating;
	}

}
