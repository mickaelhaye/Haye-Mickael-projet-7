package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.dto.UserAddDto;
import com.nnk.springboot.services.UserService;

import jakarta.validation.Valid;

/**
 * this class is the controller for the entity User.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * this endpoint is to display the list of the user.
	 * 
	 * @param model contains all user.
	 * @return return the url with the list of the user.
	 */
	@GetMapping("/user/list")
	public String home(Model model) {
		model.addAttribute("users", userService.getUsers());
		return "user/list";
	}

	/**
	 * this endpoint is to create a new User.
	 * 
	 * @param bid contains the information for the new User.
	 * @return return the url to create a new User with the new User.
	 */
	@GetMapping("/user/add")
	public String addUser(UserAddDto bid) {
		userService.setUserNameUpdating("");
		return "user/add";
	}

	/**
	 * this endpoint is to recover the information of the new User.
	 * 
	 * @param userAddDto contains the information of the new User.
	 * @param result     contains the information to check the data.
	 * @param model      contains all user.
	 * @return return the url with the list of the user if the datas are OK or the
	 *         url to create a new User if the data is not OK.
	 */
	@PostMapping("/user/validate")
	public String validate(@Valid UserAddDto userAddDto, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			User user = new User(userAddDto.getId(), userAddDto.getUsername(), userAddDto.getPassword(),
					userAddDto.getFullname(), userAddDto.getRole());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			userService.addUser(user);
			model.addAttribute("users", userService.getUsers());
			return "redirect:/user/list";
		}
		return "user/add";
	}

	/**
	 * this endpoint is to update a User with this id.
	 * 
	 * @param id    contains the id of the User.
	 * @param model contain the User.
	 * @return return the url to update a User if the id is OK or the url with the
	 *         list of the user if the id is not OK.
	 */
	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {

		User user;
		try {
			user = userService.getUserById(id);
			userService.setUserNameUpdating(user.getUsername());
			user.setPassword("");
			UserAddDto userAddDto = new UserAddDto(user.getId(), user.getUsername(), user.getPassword(),
					user.getFullname(), user.getRole());
			model.addAttribute("userAddDto", userAddDto);
			return "user/update";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("users", userService.getUsers());
			return "redirect:/user/list";
		}
	}

	/**
	 * this endpoint is to retrieve the information of the updated User.
	 * 
	 * @param id         contains the id of the User.
	 * @param userAddDto contains the information for the updated User.
	 * @param result     contains the information to check the data.
	 * @param model      contains all user.
	 * @return return the url with the list of the user if the datas are OK or the
	 *         url to update a User if the data is not OK.
	 */
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable Integer id, @Valid UserAddDto userAddDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "user/update";
		}
		User user = new User(userAddDto.getId(), userAddDto.getUsername(), userAddDto.getPassword(),
				userAddDto.getFullname(), userAddDto.getRole());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setId(id);
		userService.addUser(user);
		model.addAttribute("users", userService.getUsers());
		return "redirect:/user/list";
	}

	/**
	 * this endpoint is to delete a User with this id.
	 * 
	 * @param id    contains the id of the User
	 * @param model contains all user.
	 * @return return the url with the list of the user
	 */
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable Integer id, Model model) {
		User user;
		try {
			user = userService.getUserById(id);
			userService.delUser(user);
			model.addAttribute("users", userService.getUsers());
			return "redirect:/user/list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("curvePoints", userService.getUsers());
			return "redirect:/user/list";
		}
	}
}
