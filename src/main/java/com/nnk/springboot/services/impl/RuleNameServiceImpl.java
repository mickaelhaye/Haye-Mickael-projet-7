package com.nnk.springboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;

/**
 * this class is the service for the entity RuleName.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Service
public class RuleNameServiceImpl implements RuleNameService {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	/**
	 * this method is to recover all RuleName.
	 * 
	 * @return return a list of RuleName.
	 */
	@Override
	public Iterable<RuleName> getRuleNames() {
		return ruleNameRepository.findAll();
	}

	/**
	 * this method is to save or to update a RuleName
	 * 
	 * @param ruleName saves or updates.
	 * @return RuleName with id.
	 */
	@Override
	public RuleName addRuleName(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}

	/**
	 * this method is to recover a RuleName by this id
	 * 
	 * @param id of the RuleName.
	 * @return return the RuleName by id.
	 * @throws Exception RuleName not found with this id.
	 */
	@Override
	public RuleName getRuleNameById(Integer id) throws Exception {
		RuleName ruleName = ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
		return ruleName;
	}

	/**
	 * this method is to delete a RuleName.
	 * 
	 * @param ruleName deletes.
	 */
	@Override
	public void delRuleName(RuleName ruleName) {
		ruleNameRepository.delete(ruleName);
	}

}
