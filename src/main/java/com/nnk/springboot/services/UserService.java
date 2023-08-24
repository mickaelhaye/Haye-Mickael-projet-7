package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;

/**
 * this class is the service for the entity User.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface UserService {
	/**
	 * this method is to recover all User.
	 * 
	 * @return return a list of User.
	 */
	public Iterable<User> getUsers();

	/**
	 * this method is to save or to update an User
	 * 
	 * @param user saves or updates.
	 * @return User with id.
	 */
	public User addUser(User user);

	/**
	 * this method is to recover a User by this id
	 * 
	 * @param id of the User.
	 * @return return the User by id.
	 * @throws Exception User not found with this id.
	 */
	public User getUserById(Integer id) throws Exception;

	/**
	 * this method recovers a User by this Username.
	 * 
	 * @param username of the user.
	 * @return the user by username.
	 */
	public boolean getUserByUsername(String username);

	/**
	 * this method is to delete a User.
	 * 
	 * @param user deletes.
	 */
	public void delUser(User user);

	/**
	 * this method sends the updated username.
	 * 
	 * @return the updated username
	 */
	public String getUserNameUpdating();

	/**
	 * this method saves the updated username.
	 * 
	 * @param userNameUpdating username updated
	 */
	public void setUserNameUpdating(String userNameUpdating);

}
