package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;

/**
 * this interface is the service for the entity RuleName.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface RuleNameService {

	/**
	 * this method is to recover all RuleName.
	 * 
	 * @return return a list of RuleName.
	 */
	public Iterable<RuleName> getRuleNames();

	/**
	 * this method is to save or to update a RuleName
	 * 
	 * @param ruleName saves or updates.
	 * @return RuleName with id.
	 */
	public RuleName addRuleName(RuleName ruleName);

	/**
	 * this method is to recover a RuleName by this id
	 * 
	 * @param id of the RuleName.
	 * @return return the RuleName by id.
	 * @throws Exception RuleName not found with this id.
	 */
	public RuleName getRuleNameById(Integer id) throws Exception;

	/**
	 * this method is to delete a RuleName.
	 * 
	 * @param ruleName deletes.
	 */
	public void delRuleName(RuleName ruleName);
}
