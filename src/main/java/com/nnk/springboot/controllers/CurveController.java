package com.nnk.springboot.controllers;

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

@Controller
public class CurveController {
	@Autowired
	private CurvePointService curvePointService;

	@GetMapping("/curvePoint/list")
	public String home(Model model, HttpServletRequest httpServletRequest) {
		model.addAttribute("curvePoints", curvePointService.getCurvePoints());
		model.addAttribute("httpServletRequest", httpServletRequest);
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return Curve list
		if (!result.hasErrors()) {
			curvePointService.addCurvePoint(curvePoint);
			model.addAttribute("curvePoints", curvePointService.getCurvePoints());
			return "redirect:/curvePoint/list";
		}
		return "curvePoint/add";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		// TODO: get CurvePoint by Id and to model then show to the form
		CurvePoint curvePoint;
		try {
			curvePoint = curvePointService.getCurvePointById(id);
			model.addAttribute("curvePoint", curvePoint);
			return "curvePoint/update";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("curvePoints", curvePointService.getCurvePoints());
			return "redirect:/curvePoint/list";
		}
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable Integer id, @Valid CurvePoint curvePoint, BindingResult result, Model model) {
		// TODO: check required fields, if valid call service to update Curve and return
		// Curve list
		if (result.hasErrors()) {
			return "curvePoint/update";
		}
		curvePoint.setId(id);
		curvePointService.addCurvePoint(curvePoint);
		model.addAttribute("curvePoints", curvePointService.getCurvePoints());
		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable Integer id, Model model) {
		// TODO: Find Curve by Id and delete the Curve, return to Curve list
		CurvePoint curvePoint;
		try {
			curvePoint = curvePointService.getCurvePointById(id);
			curvePointService.delCurvePoint(curvePoint);
			model.addAttribute("curvePoints", curvePointService.getCurvePoints());
			return "redirect:/curvePoint/list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("curvePoints", curvePointService.getCurvePoints());
			return "redirect:/curvePoint/list";
		}
	}
}
