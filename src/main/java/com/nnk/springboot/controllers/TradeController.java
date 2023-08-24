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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * this class is the controller for the entity Trade.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Controller
public class TradeController {
	private static Logger logger = LoggerFactory.getLogger(TradeController.class);
	@Autowired
	private TradeService tradeService;

	/**
	 * this endpoint is to display the list of the trade.
	 * 
	 * @param model              contains all trade.
	 * @param httpServletRequest contains the userRemote.
	 * @return return the url with the list of the trade.
	 */
	@GetMapping("/trade/list")
	public String home(Model model, HttpServletRequest httpServletRequest) {
		logger.debug("home");
		model.addAttribute("trades", tradeService.getTrades());
		model.addAttribute("httpServletRequest", httpServletRequest);
		logger.info("trades List: " + tradeService.getTrades().toString() + "Remote user: "
				+ httpServletRequest.getRemoteUser());
		return "trade/list";
	}

	/**
	 * this endpoint is to create a new Trade.
	 * 
	 * @param trade contains the information for the new Trade.
	 * @return return the url to create a new Trade with the new Trade.
	 */
	@GetMapping("/trade/add")
	public String addTradeForm(Trade trade) {
		logger.debug("addTradeForm");
		return "trade/add";
	}

	/**
	 * this endpoint is to retrieve the information of the new Trade.
	 * 
	 * @param trade  contains the information for the new Trade.
	 * @param result contains the information to check the data.
	 * @param model  contains all trade.
	 * @return return the url with the list of the trade if the datas are OK or the
	 *         url to create a new Trade if the data is not OK.
	 */
	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {
		logger.debug("validate");
		if (!result.hasErrors()) {
			tradeService.addTrade(trade);
			logger.info("trade added: " + trade.toString());
			model.addAttribute("trades", tradeService.getTrades());
			return "redirect:/trade/list";
		}
		return "trade/add";
	}

	/**
	 * this endpoint is to update a Trade with this id.
	 * 
	 * @param id    contains the id of the Trade.
	 * @param model contain the Trade.
	 * @return return the url to update a Trade if the id is OK or the url with the
	 *         list of the trade if the id is not OK.
	 */
	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		logger.debug("showUpdateForm");
		Trade trade;
		try {
			trade = tradeService.getTradeById(id);
			model.addAttribute("trade", trade);
			logger.info("trade update: " + trade.toString());
			return "trade/update";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("showUpdateForm" + e);
			model.addAttribute("trades", tradeService.getTrades());
			return "redirect:/trade/list";
		}
	}

	/**
	 * this endpoint is to retrieve the information of the updated Trade.
	 * 
	 * @param id     contains the id of the Trade.
	 * @param trade  contains the information for the updated Trade.
	 * @param result contains the information to check the data.
	 * @param model  contains all trade.
	 * @return return the url with the list of the trade if the datas are OK or the
	 *         url to update a Trade if the data is not OK.
	 */
	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable Integer id, @Valid Trade trade, BindingResult result, Model model) {
		logger.debug("updateTrade");
		if (result.hasErrors()) {
			return "trade/update";
		}
		trade.setId(id);
		tradeService.addTrade(trade);
		logger.info("trade update: " + trade.toString());
		model.addAttribute("trades", tradeService.getTrades());
		return "redirect:/trade/list";
	}

	/**
	 * this endpoint is to delete a Trade with this id.
	 * 
	 * @param id    contains the id of the Trade
	 * @param model contains all trade.
	 * @return return the url with the list of the trade
	 */
	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable Integer id, Model model) {
		logger.debug("deleteTrade");
		Trade trade;
		try {
			trade = tradeService.getTradeById(id);
			tradeService.delTrade(trade);
			logger.info("trade delete: " + trade.toString());
			model.addAttribute("trades", tradeService.getTrades());
			return "redirect:/trade/list";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteTrade" + e);
			model.addAttribute("curvePoints", tradeService.getTrades());
			return "redirect:/trade/list";
		}
	}
}
