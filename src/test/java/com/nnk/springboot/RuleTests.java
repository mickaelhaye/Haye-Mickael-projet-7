package com.nnk.springboot;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * this class is to test for the entity RuleName.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@SpringBootTest
public class RuleTests {

	/*
	 * @Autowired private RuleNameRepository ruleNameRepository;
	 * 
	 * @Test public void ruleTest() { RuleName rule = new RuleName("Rule Name",
	 * "Description", "Json", "Template", "SQL", "SQL Part");
	 * 
	 * // Save rule = ruleNameRepository.save(rule);
	 * Assert.assertNotNull(rule.getId());
	 * Assert.assertTrue(rule.getName().equals("Rule Name"));
	 * 
	 * // Update rule.setName("Rule Name Update"); rule =
	 * ruleNameRepository.save(rule);
	 * Assert.assertTrue(rule.getName().equals("Rule Name Update"));
	 * 
	 * // Find List<RuleName> listResult = ruleNameRepository.findAll();
	 * Assert.assertTrue(listResult.size() > 0);
	 * 
	 * // Delete Integer id = rule.getId(); ruleNameRepository.delete(rule);
	 * Optional<RuleName> ruleList = ruleNameRepository.findById(id);
	 * Assert.assertFalse(ruleList.isPresent()); }
	 */
}
