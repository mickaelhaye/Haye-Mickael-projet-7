package com.nnk.springboot.controllers;

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
	/**
	 * This endpoint is to display the home page.
	 * 
	 * @param model not used.
	 * @return return the url of the home page.
	 */
	@GetMapping("/")
	public String home(Model model) {
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
		return "redirect:/bidList/list";
	}

}
