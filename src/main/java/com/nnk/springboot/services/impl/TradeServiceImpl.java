package com.nnk.springboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepository tradeRepository;

	@Override
	public Iterable<Trade> getTrades() {
		return tradeRepository.findAll();
	}

	@Override
	public Trade addTrade(Trade trade) {
		return tradeRepository.save(trade);
	}

	@Override
	public Trade getTradeById(Integer id) throws Exception {
		Trade trade = tradeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Trade Id:" + id));
		return trade;
	}

	@Override
	public void delTrade(Trade trade) {
		tradeRepository.delete(trade);
	}

}
