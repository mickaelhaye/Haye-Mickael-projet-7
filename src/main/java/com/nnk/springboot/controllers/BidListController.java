package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

import jakarta.validation.Valid;

@Controller
public class BidListController {
	@Autowired
	private BidListService bidListService;

	@GetMapping("/bidList/list")
	public String home(Model model) {
		model.addAttribute("bidLists", bidListService.getBidLists());
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return bid list
		if (!result.hasErrors()) {
			bidListService.addBidList(bid);
			model.addAttribute("bidLists", bidListService.getBidLists());
			return "redirect:/bidList/list";
		}
		return "bidList/add";
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		// TODO: get Bid by Id and to model then show to the form
		BidList bid;
		try {
			bid = bidListService.getBidListById(id);
			model.addAttribute("bidList", bid);
			return "bidList/update";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("bidLists", bidListService.getBidLists());
			return "redirect:/bidList/list";
		}

	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		// TODO: check required fields, if valid call service to update Bid and return
		// list Bid
		if (result.hasErrors()) {
			return "bidList/update";
		}
		bidList.setBidListId(id);
		bidListService.addBidList(bidList);
		model.addAttribute("bidLists", bidListService.getBidLists());
		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable Integer id, Model model) {
		// TODO: Find Bid by Id and delete the bid, return to Bid list
		BidList bid;
		try {
			bid = bidListService.getBidListById(id);
			bidListService.delBidList(bid);
			model.addAttribute("bidLists", bidListService.getBidLists());
			return "redirect:/bidList/list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("bidLists", bidListService.getBidLists());
			return "redirect:/bidList/list";
		}
	}
}
