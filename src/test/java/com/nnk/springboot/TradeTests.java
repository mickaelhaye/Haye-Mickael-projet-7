package com.nnk.springboot;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

/**
 * this class is to test for the entity Trade.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeService tradeService;

	@Test
	public void tradeTest() {
		Trade trade = new Trade();
		trade.setAccount("Trade Account");
		trade.setType("Type");

		// Save
		trade = tradeService.addTrade(trade);
		assertNotNull(trade.getId());
		assertTrue(trade.getAccount().equals("Trade Account"));

		// Update
		trade.setAccount("Trade Account Update");
		trade = tradeService.addTrade(trade);
		assertTrue(trade.getAccount().equals("Trade Account Update"));

		// Find
		List<Trade> listResult = (List<Trade>) tradeService.getTrades();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = trade.getId();
		tradeService.delTrade(trade);
		Trade tradeList = null;
		try {
			tradeList = tradeService.getTradeById(id);
		} catch (Exception e) {
			assertNull(tradeList);
		}
	}
}
