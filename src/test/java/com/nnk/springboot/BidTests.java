package com.nnk.springboot;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * this class is to test for the entity Bidlist.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@SpringBootTest
public class BidTests {

	/*
	 * @Autowired private BidListRepository bidListRepository;
	 * 
	 * @Test public void bidListTest() { BidList bid = new BidList("Account Test",
	 * "Type Test", 10d);
	 * 
	 * // Save bid = bidListRepository.save(bid);
	 * Assert.assertNotNull(bid.getBidListId());
	 * Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);
	 * 
	 * // Update bid.setBidQuantity(20d); bid = bidListRepository.save(bid);
	 * Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);
	 * 
	 * // Find List<BidList> listResult = bidListRepository.findAll();
	 * Assert.assertTrue(listResult.size() > 0);
	 * 
	 * // Delete Integer id = bid.getBidListId(); bidListRepository.delete(bid);
	 * Optional<BidList> bidList = bidListRepository.findById(id);
	 * Assert.assertFalse(bidList.isPresent()); }
	 */
}
