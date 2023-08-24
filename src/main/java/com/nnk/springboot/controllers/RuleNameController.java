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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * this class is the controller for the entity RuleName.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Controller
public class RuleNameController {
	@Autowired
	private RuleNameService ruleNameService;

	/**
	 * this endpoint is to display the list of the ruleName.
	 * 
	 * @param model              contains all ruleName.
	 * @param httpServletRequest contains the userRemote.
	 * @return return the url with the list of the ruleName.
	 */
	@GetMapping("/ruleName/list")
	public String home(Model model, HttpServletRequest httpServletRequest) {
		model.addAttribute("ruleNames", ruleNameService.getRuleNames());
		model.addAttribute("httpServletRequest", httpServletRequest);
		return "ruleName/list";
	}

	/**
	 * this endpoint is to create a new RuleName.
	 * 
	 * @param ruleName contains the information for the new RuleName.
	 * @return return the url to create a new RuleName with the new RuleName.
	 */
	@GetMapping("/ruleName/add")
	public String addRuleNameForm(RuleName ruleName) {
		return "ruleName/add";
	}

	/**
	 * this endpoint is to retrieve the information of the new RuleName.
	 * 
	 * @param ruleName contains the information for the new RuleName.
	 * @param result   contains the information to check the data.
	 * @param model    contains all ruleName.
	 * @return return the url with the list of the ruleName if the datas are OK or
	 *         the url to create a new RuleName if the data is not OK.
	 */
	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			ruleNameService.addRuleName(ruleName);
			model.addAttribute("ruleNames", ruleNameService.getRuleNames());
			return "redirect:/ruleName/list";
		}
		return "ruleName/add";
	}

	/**
	 * this endpoint is to update a RuleName with this id.
	 * 
	 * @param id    contains the id of the RuleName.
	 * @param model contain the RuleName.
	 * @return return the url to update a RuleName if the id is OK or the url with
	 *         the list of the ruleName if the id is not OK.
	 */
	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		RuleName ruleName;
		try {
			ruleName = ruleNameService.getRuleNameById(id);
			model.addAttribute("ruleName", ruleName);
			return "ruleName/update";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("ruleNames", ruleNameService.getRuleNames());
			return "redirect:/ruleName/list";
		}
	}

	/**
	 * this endpoint is to retrieve the information of the updated RuleName.
	 * 
	 * @param id       contains the id of the RuleName.
	 * @param ruleName contains the information for the updated RuleName.
	 * @param result   contains the information to check the data.
	 * @param model    contains all ruleName.
	 * @return return the url with the list of the ruleName if the datas are OK or
	 *         the url to update a RuleName if the data is not OK.
	 */
	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "ruleName/update";
		}
		ruleName.setId(id);
		ruleNameService.addRuleName(ruleName);
		model.addAttribute("ruleNames", ruleNameService.getRuleNames());
		return "redirect:/ruleName/list";
	}

	/**
	 * this endpoint is to delete a RuleName with this id.
	 * 
	 * @param id    contains the id of the RuleName
	 * @param model contains all ruleName.
	 * @return return the url with the list of the ruleName
	 */
	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable Integer id, Model model) {
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
