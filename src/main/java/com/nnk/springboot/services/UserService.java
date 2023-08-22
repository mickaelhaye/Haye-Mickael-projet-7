package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;

public interface UserService {

	public Iterable<User> getUsers();

	public User addUser(User user);

	public User getUserById(Integer id) throws Exception;

	public void delUser(User user);

	public boolean getUserByUsername(String username);

	public String getUserNameUpdating();

	public void setUserNameUpdating(String userNameUpdating);

}
