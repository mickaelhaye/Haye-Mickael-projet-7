package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * this class is the controller for the login.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Controller
@RequestMapping("app")
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * this endpoint is to display the login page.
	 * 
	 * @return return the url login page.
	 */
	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	/**
	 * this endpoint is to display the success login page.
	 * 
	 * @param httpServletRequest contains the userRemote.
	 * @return the url of the success login page.
	 */
	@GetMapping("login_success")
	public ModelAndView loginSuccess(HttpServletRequest httpServletRequest) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("httpServletRequest", httpServletRequest);
		mav.setViewName("login_success");
		return mav;
	}

	/**
	 * this endpoint is to display the error login page.
	 * 
	 * @return the url of the error login page.
	 */
	@GetMapping("login_error")
	public ModelAndView loginError() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login_error");
		return mav;
	}

	/**
	 * this endpoint is to display the user list page.
	 * 
	 * @return the url of the user list page.
	 */
	@GetMapping("secure/article-details")
	public ModelAndView getAllUserArticles() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", userService.getUsers());
		mav.setViewName("user/list");
		return mav;
	}

	/**
	 * this endpoint is to display the error 403 page.
	 * 
	 * @param httpServletRequest httpServletRequest contains the userRemote.
	 * @return the url of the 403 error page.
	 */
	@GetMapping("error")
	public ModelAndView error(HttpServletRequest httpServletRequest) {
		ModelAndView mav = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data.";
		mav.addObject("errorMsg", errorMessage);
		mav.addObject("httpServletRequest", httpServletRequest);
		mav.setViewName("403");
		return mav;
	}
}
