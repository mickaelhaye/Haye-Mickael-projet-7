package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("app")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	@GetMapping("login_success")
	public ModelAndView loginSuccess(HttpServletRequest httpServletRequest) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("httpServletRequest", httpServletRequest);
		mav.setViewName("login_success");
		return mav;
	}

	@GetMapping("login_error")
	public ModelAndView loginError() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login_error");
		return mav;
	}

	@GetMapping("secure/article-details")
	public ModelAndView getAllUserArticles() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", userService.getUsers());
		mav.setViewName("user/list");
		return mav;
	}

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
