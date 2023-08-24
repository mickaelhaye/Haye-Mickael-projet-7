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
	private static Logger logger = LoggerFactory.getLogger(BidListController.class);
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
		logger.debug("home");
		model.addAttribute("bidLists", bidListService.getBidLists());
		model.addAttribute("httpServletRequest", httpServletRequest);
		logger.info("bidList List: " + bidListService.getBidLists().toString() + "Remote user: "
				+ httpServletRequest.getRemoteUser());
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
		logger.debug("addBidForm");
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
		logger.debug("addBidForm");
		if (!result.hasErrors()) {
			bidListService.addBidList(bid);
			logger.info("bidList added: " + bid.toString());
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
		logger.debug("showUpdateForm");
		BidList bid;
		try {
			bid = bidListService.getBidListById(id);
			model.addAttribute("bidList", bid);
			logger.info("bidList update: " + bid.toString());
			return "bidList/update";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("showUpdateForm" + e);
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
		logger.debug("updateBid");
		if (result.hasErrors()) {
			return "bidList/update";
		}
		bidList.setBidListId(id);
		bidListService.addBidList(bidList);
		logger.info("bidList update: " + bidList.toString());
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
		logger.debug("deleteBid");
		BidList bid;
		try {
			bid = bidListService.getBidListById(id);
			bidListService.delBidList(bid);
			logger.info("bidList delete: " + bid.toString());
			model.addAttribute("bidLists", bidListService.getBidLists());
			return "redirect:/bidList/list";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteBid" + e);
			model.addAttribute("bidLists", bidListService.getBidLists());
			return "redirect:/bidList/list";
		}
	}
}
