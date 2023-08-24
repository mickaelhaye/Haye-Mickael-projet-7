package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

/**
 * this interface is the service for the entity BidList.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface BidListService {
	/**
	 * this method is to recover all BidList.
	 * 
	 * @return return a list of BidList.
	 */
	public Iterable<BidList> getBidLists();

	/**
	 * this method is to save or to update a BidList
	 * 
	 * @param bid saves or updates.
	 * @return BidList with id.
	 */
	public BidList addBidList(BidList bid);

	/**
	 * this method is to recover a BidList by this id
	 * 
	 * @param id of the BidList.
	 * @return return the BidList by id.
	 * @throws Exception BidList not found with this id.
	 */
	public BidList getBidListById(Integer id) throws Exception;

	/**
	 * this method is to delete a BidList.
	 * 
	 * @param bid deletes.
	 */
	public void delBidList(BidList bid);

}
