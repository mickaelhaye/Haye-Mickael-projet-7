package com.nnk.springboot.controllers;

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

@Controller
public class TradeController {
	@Autowired
	private TradeService tradeService;

	@GetMapping("/trade/list")
	public String home(Model model, HttpServletRequest httpServletRequest) {
		model.addAttribute("trades", tradeService.getTrades());
		model.addAttribute("httpServletRequest", httpServletRequest);
		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addTradeForm(Trade trade) {
		return "trade/add";
	}

	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return Trade list
		if (!result.hasErrors()) {
			tradeService.addTrade(trade);
			model.addAttribute("trades", tradeService.getTrades());
			return "redirect:/trade/list";
		}
		return "trade/add";
	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		// TODO: get Trade by Id and to model then show to the form
		Trade trade;
		try {
			trade = tradeService.getTradeById(id);
			model.addAttribute("trade", trade);
			return "trade/update";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("trades", tradeService.getTrades());
			return "redirect:/trade/list";
		}
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable Integer id, @Valid Trade trade, BindingResult result, Model model) {
		// TODO: check required fields, if valid call service to update Trade and
		// return Trade list
		if (result.hasErrors()) {
			return "trade/update";
		}
		trade.setId(id);
		tradeService.addTrade(trade);
		model.addAttribute("trades", tradeService.getTrades());
		return "redirect:/trade/list";
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable Integer id, Model model) {
		// TODO: Find Trade by Id and delete the Trade, return to Trade list
		Trade trade;
		try {
			trade = tradeService.getTradeById(id);
			tradeService.delTrade(trade);
			model.addAttribute("trades", tradeService.getTrades());
			return "redirect:/trade/list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("curvePoints", tradeService.getTrades());
			return "redirect:/trade/list";
		}
	}
}
