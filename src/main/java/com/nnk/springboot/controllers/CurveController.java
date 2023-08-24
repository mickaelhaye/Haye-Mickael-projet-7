package com.nnk.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * this class is the controller for the entity CurvePoint.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Controller
public class CurveController {
	private static Logger logger = LoggerFactory.getLogger(CurveController.class);
	@Autowired
	private CurvePointService curvePointService;

	/**
	 * this endpoint is to display the list of the curvePoint.
	 * 
	 * @param model              contains all curvePoint.
	 * @param httpServletRequest contains the userRemote.
	 * @return return the url with the list of the curvePoint.
	 */
	@GetMapping("/curvePoint/list")
	public String home(Model model, HttpServletRequest httpServletRequest) {
		logger.debug("home");
		model.addAttribute("curvePoints", curvePointService.getCurvePoints());
		model.addAttribute("httpServletRequest", httpServletRequest);
		logger.info("curvePoints List: " + curvePointService.getCurvePoints().toString() + "Remote user: "
				+ httpServletRequest.getRemoteUser());
		return "curvePoint/list";
	}

	/**
	 * this endpoint is to create a new CurvePoint.
	 * 
	 * @param bid contains the information for the new CurvePoint.
	 * @return return the url to create a new CurvePoint with the new CurvePoint.
	 */
	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		logger.debug("addBidForm");
		return "curvePoint/add";
	}

	/**
	 * this endpoint is to retrieve the information of the new CurvePoint.
	 * 
	 * @param curvePoint contains the information for the new CurvePoint.
	 * @param result     contains the information to check the data.
	 * @param model      contains all curvePoint.
	 * @return return the url with the list of the curvePoint if the datas are OK or
	 *         the url to create a new CurvePoint if the data is not OK.
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		logger.debug("validate");
		if (!result.hasErrors()) {
			curvePointService.addCurvePoint(curvePoint);
			logger.info("curvePoint added: " + curvePoint.toString());
			model.addAttribute("curvePoints", curvePointService.getCurvePoints());
			return "redirect:/curvePoint/list";
		}
		return "curvePoint/add";
	}

	/**
	 * this endpoint is to update a CurvePoint with this id.
	 * 
	 * @param id    contains the id of the CurvePoint.
	 * @param model contain the CurvePoint.
	 * @return return the url to update a CurvePoint if the id is OK or the url with
	 *         the list of the curvePoint if the id is not OK.
	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		logger.debug("showUpdateForm");
		CurvePoint curvePoint;
		try {
			curvePoint = curvePointService.getCurvePointById(id);
			model.addAttribute("curvePoint", curvePoint);
			logger.info("curvePoint update: " + curvePoint.toString());
			return "curvePoint/update";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("showUpdateForm" + e);
			model.addAttribute("curvePoints", curvePointService.getCurvePoints());
			return "redirect:/curvePoint/list";
		}
	}

	/**
	 * this endpoint is to retrieve the information of the updated CurvePoint.
	 * 
	 * @param id         contains the id of the CurvePoint.
	 * @param curvePoint contains the information for the updated CurvePoint.
	 * @param result     contains the information to check the data.
	 * @param model      contains all curvePoint.
	 * @return return the url with the list of the curvePoint if the datas are OK or
	 *         the url to update a CurvePoint if the data is not OK.
	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable Integer id, @Valid CurvePoint curvePoint, BindingResult result, Model model) {
		logger.debug("updateBid");
		if (result.hasErrors()) {
			return "curvePoint/update";
		}
		curvePoint.setId(id);
		curvePointService.addCurvePoint(curvePoint);
		logger.info("curvePoint update: " + curvePoint.toString());
		model.addAttribute("curvePoints", curvePointService.getCurvePoints());
		return "redirect:/curvePoint/list";
	}

	/**
	 * this endpoint is to delete a CurvePoint with this id.
	 * 
	 * @param id    contains the id of the CurvePoint
	 * @param model contains all curvePoint.
	 * @return return the url with the list of the curvePoint
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable Integer id, Model model) {
		logger.debug("deleteBid");
		CurvePoint curvePoint;
		try {
			curvePoint = curvePointService.getCurvePointById(id);
			curvePointService.delCurvePoint(curvePoint);
			logger.info("curvePoint delete: " + curvePoint.toString());
			model.addAttribute("curvePoints", curvePointService.getCurvePoints());
			return "redirect:/curvePoint/list";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteBid" + e);
			model.addAttribute("curvePoints", curvePointService.getCurvePoints());
			return "redirect:/curvePoint/list";
		}
	}
}
