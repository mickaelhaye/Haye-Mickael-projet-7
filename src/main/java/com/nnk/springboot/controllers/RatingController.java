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

/**
 * this class is the controller for the entity Rating.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Controller
public class RatingController {
	@Autowired
	private RatingService ratingService;

	/**
	 * this endpoint is to display the list of the rating.
	 * 
	 * @param model              contains all rating.
	 * @param httpServletRequest contains the userRemote.
	 * @return return the url with the list of the rating.
	 */
	@GetMapping("/rating/list")
	public String home(Model model, HttpServletRequest httpServletRequest) {
		model.addAttribute("ratings", ratingService.getRatings());
		model.addAttribute("httpServletRequest", httpServletRequest);
		return "rating/list";
	}

	/**
	 * this endpoint is to create a new Rating.
	 * 
	 * @param rating contains the information for the new Rating.
	 * @return return the url to create a new Rating with the new Rating.
	 */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	/**
	 * this endpoint is to retrieve the information of the new Rating.
	 * 
	 * @param rating contains the information for the new Rating.
	 * @param result contains the information to check the data.
	 * @param model  contains all rating.
	 * @return return the url with the list of the rating if the datas are OK or the
	 *         url to create a new Rating if the data is not OK.
	 */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			ratingService.addRating(rating);
			model.addAttribute("ratings", ratingService.getRatings());
			return "redirect:/rating/list";
		}
		return "rating/add";
	}

	/**
	 * this endpoint is to update a Rating with this id.
	 * 
	 * @param id    contains the id of the Rating.
	 * @param model contain the Rating.
	 * @return return the url to update a Rating if the id is OK or the url with the
	 *         list of the rating if the id is not OK.
	 */
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		Rating rating;
		try {
			rating = ratingService.getRatingById(id);
			model.addAttribute("rating", rating);
			return "rating/update";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("ratings", ratingService.getRatings());
			return "redirect:/rating/list";
		}
	}

	/**
	 * this endpoint is to retrieve the information of the updated Rating.
	 * 
	 * @param id     contains the id of the Rating.
	 * @param rating contains the information for the updated Rating.
	 * @param result contains the information to check the data.
	 * @param model  contains all rating.
	 * @return return the url with the list of the rating if the datas are OK or the
	 *         url to update a Rating if the data is not OK.
	 */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable Integer id, @Valid Rating rating, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "rating/update";
		}
		rating.setId(id);
		ratingService.addRating(rating);
		model.addAttribute("ratings", ratingService.getRatings());
		return "redirect:/rating/list";
	}

	/**
	 * this endpoint is to delete a Rating with this id.
	 * 
	 * @param id    contains the id of the Rating
	 * @param model contains all rating.
	 * @return return the url with the list of the rating
	 */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable Integer id, Model model) {
		Rating rating;
		try {
			rating = ratingService.getRatingById(id);
			ratingService.delRating(rating);
			model.addAttribute("ratings", ratingService.getRatings());
			return "redirect:/rating/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("ratings", ratingService.getRatings());
			return "redirect:/rating/list";
		}
	}
}
