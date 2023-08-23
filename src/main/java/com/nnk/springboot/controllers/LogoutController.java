package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	@GetMapping("logout_success")
	public ModelAndView loginSuccess() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("logout_success");
		return mav;
	}
}
