package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

import jakarta.validation.Valid;

@Controller
public class RuleNameController {
	@Autowired
	private RuleNameService ruleNameService;

	@GetMapping("/ruleName/list")
	public String home(Model model) {
		model.addAttribute("ruleNames", ruleNameService.getRuleNames());
		return "ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleNameForm(RuleName ruleName) {
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		// TODO: check data valid and save to db, after saving return RuleName list
		if (!result.hasErrors()) {
			ruleNameService.addRuleName(ruleName);
			model.addAttribute("ruleNames", ruleNameService.getRuleNames());
			return "redirect:/ruleName/list";
		}
		return "ruleName/add";
	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		// TODO: get RuleName by Id and to model then show to the form
		RuleName ruleName;
		try {
			ruleName = ruleNameService.getRuleNameById(id);
			model.addAttribute("ruleName", ruleName);
			return "ruleName/update";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("ruleNames", ruleNameService.getRuleNames());
			return "redirect:/ruleName/list";
		}
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		// TODO: check required fields, if valid call service to update RuleName and
		// return RuleName list
		if (result.hasErrors()) {
			return "ruleName/update";
		}
		ruleName.setId(id);
		ruleNameService.addRuleName(ruleName);
		model.addAttribute("ruleNames", ruleNameService.getRuleNames());
		return "redirect:/ruleName/list";
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable Integer id, Model model) {
		// TODO: Find RuleName by Id and delete the RuleName, return to RuleName list
		RuleName ruleName;
		try {
			ruleName = ruleNameService.getRuleNameById(id);
			ruleNameService.delRuleName(ruleName);
			model.addAttribute("ruleNames", ruleNameService.getRuleNames());
			return "redirect:/ruleName/list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("curvePoints", ruleNameService.getRuleNames());
			return "redirect:/ruleName/list";
		}
	}
}
