package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;

public interface RuleNameService {

	public Iterable<RuleName> getRuleNames();

	public RuleName addRuleName(RuleName ruleName);

	public RuleName getRuleNameById(Integer id) throws Exception;

	public void delRuleName(RuleName ruleName);
}
