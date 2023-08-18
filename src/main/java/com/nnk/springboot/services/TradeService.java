package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;

public interface TradeService {

	public Iterable<Trade> getTrades();

	public Trade addTrade(Trade trade);

	public Trade getTradeById(Integer id) throws Exception;

	public void delTrade(Trade trade);

}
