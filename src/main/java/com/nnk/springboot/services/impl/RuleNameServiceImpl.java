package com.nnk.springboot.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static Logger logger = LoggerFactory.getLogger(RuleNameServiceImpl.class);
	@Autowired
	private RuleNameRepository ruleNameRepository;

	/**
	 * this method is to recover all RuleName.
	 * 
	 * @return return a list of RuleName.
	 */
	@Override
	public Iterable<RuleName> getRuleNames() {
		logger.debug("getRuleNames");
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
		logger.debug("addRuleName");
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
		logger.debug("getRuleNameById");
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
		logger.debug("delRuleName");
		ruleNameRepository.delete(ruleName);
	}

}
