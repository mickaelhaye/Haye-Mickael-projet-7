package com.nnk.springboot.ServiceTests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

/**
 * this class is to test for the entity RuleName.
 * 
 * @author mickael hayé
 * @version 1.0
 */

@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameService ruleNameService;

	/**
	 * methods to test save, update, find and delete entity RuleName
	 */
	@Test
	public void ruleTest() {
		RuleName rule = new RuleName();
		rule.setName("Rule Name");
		rule.setDescription("Description");
		rule.setJson("Json");
		rule.setTemplate("Template");
		rule.setSql("SQL");
		rule.setSqlPart("SQL Part");

		// Save
		rule = ruleNameService.addRuleName(rule);
		assertNotNull(rule.getId());
		assertTrue(rule.getName().equals("Rule Name"));

		// Update
		rule.setName("Rule Name Update");
		rule = ruleNameService.addRuleName(rule);
		assertTrue(rule.getName().equals("Rule Name Update"));

		// Find
		List<RuleName> listResult = (List<RuleName>) ruleNameService.getRuleNames();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rule.getId();
		RuleName ruleList = null;
		try {
			ruleList = ruleNameService.getRuleNameById(id);
			assertNotNull(ruleList);
		} catch (Exception e) {

		}
		ruleNameService.delRuleName(rule);
		ruleList = null;
		try {
			ruleList = ruleNameService.getRuleNameById(id);
		} catch (Exception e) {
			assertNull(ruleList);
		}
	}
}
