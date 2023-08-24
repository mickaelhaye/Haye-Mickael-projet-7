package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;

/**
 * this class is the service for the entity Trade.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface TradeService {
	/**
	 * this method recovers all Trade.
	 * 
	 * @return return a list of Trade.
	 */
	public Iterable<Trade> getTrades();

	/**
	 * this method saves or updates a Trade.
	 * 
	 * @param trade saves or updates.
	 * @return trade with id.
	 */
	public Trade addTrade(Trade trade);

	/**
	 * this method recovers a Trade by this id.
	 * 
	 * @param id of the Trade.
	 * @return return the trade by id.
	 * @throws Exception Trade not found with this id.
	 */
	public Trade getTradeById(Integer id) throws Exception;

	/**
	 * this method deletes a Trade.
	 * 
	 * @param trade deletes.
	 */
	public void delTrade(Trade trade);

}
