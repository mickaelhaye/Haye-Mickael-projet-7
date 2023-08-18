package com.nnk.springboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;

@Service
public class RuleNameServiceImpl implements RuleNameService {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Override
	public Iterable<RuleName> getRuleNames() {
		return ruleNameRepository.findAll();
	}

	@Override
	public RuleName addRuleName(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}

	@Override
	public RuleName getRuleNameById(Integer id) throws Exception {
		RuleName ruleName = ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
		return ruleName;
	}

	@Override
	public void delRuleName(RuleName ruleName) {
		ruleNameRepository.delete(ruleName);
	}

}
