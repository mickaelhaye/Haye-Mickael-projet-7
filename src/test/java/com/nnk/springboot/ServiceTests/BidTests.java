package com.nnk.springboot.ServiceTests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

/**
 * this class is to test the BidListService methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */

@SpringBootTest
public class BidTests {

	@Autowired
	private BidListService BidListservice;

	/**
	 * methods to test save, update, find and delete BidList
	 */
	@Test
	public void bidListTest() {
		BidList bid = new BidList();
		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(10d);

		// Save
		bid = BidListservice.addBidList(bid);
		assertNotNull(bid.getBidListId());
		assertEquals(bid.getBidQuantity(), 10d);

		// Update
		bid.setBidQuantity(20d);
		bid = BidListservice.addBidList(bid);
		assertEquals(bid.getBidQuantity(), 20d);

		// Find
		List<BidList> listResult = (List<BidList>) BidListservice.getBidLists();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getBidListId();
		BidList bidList = null;
		try {
			bidList = BidListservice.getBidListById(id);
			assertNotNull(bidList);
		} catch (Exception e) {
		}
		BidListservice.delBidList(bid);
		bidList = null;
		try {
			bidList = BidListservice.getBidListById(id);
		} catch (Exception e) {
			assertNull(bidList);
		}

	}
}
