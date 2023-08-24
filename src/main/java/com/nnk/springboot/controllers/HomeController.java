package com.nnk.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * this class is the controller to manage home endpoints.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Controller
public class HomeController {
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * This endpoint is to display the home page.
	 * 
	 * @param model not used.
	 * @return return the url of the home page.
	 */
	@GetMapping("/")
	public String home(Model model) {
		logger.debug("home");
		return "home";
	}

	/**
	 * this endpoint is to diplay the admin homepage.
	 * 
	 * @param model not used.
	 * @return return the url of the bidList page.
	 */
	@GetMapping("/admin/home")
	public String adminHome(Model model) {
		logger.debug("adminHome");
		return "redirect:/bidList/list";
	}

}
