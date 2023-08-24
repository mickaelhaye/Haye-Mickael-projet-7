package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * this class is the controller for the logout.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Controller
public class LogoutController {

	/**
	 * this endpoint is to display the success logout page.
	 * 
	 * @return the url of the success logout page.
	 */
	@GetMapping("logout_success")
	public ModelAndView loginSuccess() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("logout_success");
		return mav;
	}
}
