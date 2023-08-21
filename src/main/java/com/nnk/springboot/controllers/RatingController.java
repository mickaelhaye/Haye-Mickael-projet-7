package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class RatingController {
	@Autowired
	private RatingService ratingService;

	@GetMapping("/rating/list")
	public String home(Model model, HttpServletRequest httpServletRequest) {
		model.addAttribute("ratings", ratingService.getRatings());
		model.addAttribute("httpServletRequest", httpServletRequest);
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return Rating list
		if (!result.hasErrors()) {
			ratingService.addRating(rating);
			model.addAttribute("ratings", ratingService.getRatings());
			return "redirect:/rating/list";
		}
		return "rating/add";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		// TODO: get Rating by Id and to model then show to the form
		Rating rating;
		try {
			rating = ratingService.getRatingById(id);
			model.addAttribute("rating", rating);
			return "rating/update";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("ratings", ratingService.getRatings());
			return "redirect:/rating/list";
		}
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable Integer id, @Valid Rating rating, BindingResult result, Model model) {
		// TODO: check required fields, if valid call service to update Rating and
		// return Rating list
		if (result.hasErrors()) {
			return "rating/update";
		}
		rating.setId(id);
		ratingService.addRating(rating);
		model.addAttribute("ratings", ratingService.getRatings());
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable Integer id, Model model) {
		// TODO: Find Rating by Id and delete the Rating, return to Rating list
		Rating rating;
		try {
			rating = ratingService.getRatingById(id);
			ratingService.delRating(rating);
			model.addAttribute("ratings", ratingService.getRatings());
			return "redirect:/rating/list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("curvePoints", ratingService.getRatings());
			return "redirect:/rating/list";
		}
	}
}
