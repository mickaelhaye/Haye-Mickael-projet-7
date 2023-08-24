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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * this class is the controller for the entity BidList.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Controller
public class BidListController {
	@Autowired
	private BidListService bidListService;

	/**
	 * this endpoint is to display the list of the bidList.
	 * 
	 * @param model              contains all bidList.
	 * @param httpServletRequest contains the userRemote.
	 * @return return the url with the list of the bidList.
	 */
	@GetMapping("/bidList/list")
	public String home(Model model, HttpServletRequest httpServletRequest) {
		model.addAttribute("bidLists", bidListService.getBidLists());
		model.addAttribute("httpServletRequest", httpServletRequest);
		return "bidList/list";
	}

	/**
	 * this endpoint is to create a new BidList.
	 * 
	 * @param bid contains the information for the new BidList.
	 * @return return the url to create a new BidList with the new BidList.
	 */
	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return "bidList/add";
	}

	/**
	 * this endpoint is to retrieve the information of the new BidList.
	 * 
	 * @param bid    contains the information for the new BidList.
	 * @param result contains the information to check the data.
	 * @param model  contains all bidList.
	 * @return return the url with the list of the bidList if the datas are OK or
	 *         the url to create a new BidList if the data is not OK.
	 */
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			bidListService.addBidList(bid);
			model.addAttribute("bidLists", bidListService.getBidLists());
			return "redirect:/bidList/list";
		}
		return "bidList/add";
	}

	/**
	 * this endpoint is to update a BidList with this id.
	 * 
	 * @param id    contains the id of the BidList.
	 * @param model contain the BidList.
	 * @return return the url to update a BidList if the id is OK or the url with
	 *         the list of the bidList if the id is not OK.
	 */
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		BidList bid;
		try {
			bid = bidListService.getBidListById(id);
			model.addAttribute("bidList", bid);
			return "bidList/update";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("bidLists", bidListService.getBidLists());
			return "redirect:/bidList/list";
		}

	}

	/**
	 * this endpoint is to retrieve the information of the updated BidList.
	 * 
	 * @param id      contains the id of the BidList.
	 * @param bidList contains the information for the updated BidList.
	 * @param result  contains the information to check the data.
	 * @param model   contains all bidList.
	 * @return return the url with the list of the bidList if the datas are OK or
	 *         the url to update a BidList if the data is not OK.
	 */
	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "bidList/update";
		}
		bidList.setBidListId(id);
		bidListService.addBidList(bidList);
		model.addAttribute("bidLists", bidListService.getBidLists());
		return "redirect:/bidList/list";
	}

	/**
	 * this endpoint is to delete a BidList with this id.
	 * 
	 * @param id    contains the id of the BidList
	 * @param model contains all bidList.
	 * @return return the url with the list of the bidList
	 */
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable Integer id, Model model) {
		BidList bid;
		try {
			bid = bidListService.getBidListById(id);
			bidListService.delBidList(bid);
			model.addAttribute("bidLists", bidListService.getBidLists());
			return "redirect:/bidList/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("bidLists", bidListService.getBidLists());
			return "redirect:/bidList/list";
		}
	}
}
