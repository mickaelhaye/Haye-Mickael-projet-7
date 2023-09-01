package com.nnk.springboot.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

/**
 * this class is the service for the entity Trade.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Service
public class TradeServiceImpl implements TradeService {
	private static Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);
	@Autowired
	private TradeRepository tradeRepository;

	/**
	 * this method recovers all Trade.
	 * 
	 * @return return a list of Trade.
	 */
	@Override
	public Iterable<Trade> getTrades() {
		logger.debug("getTrades");
		return tradeRepository.findAll();
	}

	/**
	 * this method saves or updates a Trade.
	 * 
	 * @param trade saves or updates.
	 * @return trade with id.
	 */
	@Override
	public Trade addTrade(Trade trade) {
		logger.debug("addTrade");
		return tradeRepository.save(trade);
	}

	/**
	 * this method recovers a Trade by this id.
	 * 
	 * @param id of the Trade.
	 * @return return the trade by id.
	 * @throws Exception Trade not found with this id.
	 */
	@Override
	public Trade getTradeById(Integer id) throws Exception {
		logger.debug("getTradeById");
		Trade trade = tradeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Trade Id:" + id));
		return trade;
	}

	/**
	 * this method deletes a Trade.
	 * 
	 * @param trade deletes.
	 */
	@Override
	public void delTrade(Trade trade) {
		logger.debug("delTrade");
		tradeRepository.delete(trade);
	}

}
