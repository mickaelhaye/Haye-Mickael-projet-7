package com.nnk.springboot.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

/**
 * this class is the service for the entity BidList.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Service
public class BidListServiceImpl implements BidListService {
	private static Logger logger = LoggerFactory.getLogger(BidListServiceImpl.class);
	@Autowired
	private BidListRepository bidListRepository;

	/**
	 * this method is to recover all BidList.
	 * 
	 * @return return a list of BidList.
	 */
	@Override
	public Iterable<BidList> getBidLists() {
		logger.debug("getBidLists");
		return bidListRepository.findAll();
	}

	/**
	 * this method is to save or to update a BidList
	 * 
	 * @param bid saves or updates.
	 * @return BidList with id.
	 */
	@Override
	public BidList addBidList(BidList bid) {
		logger.debug("addBidList");
		return bidListRepository.save(bid);
	}

	/**
	 * this method is to recover a BidList by this id
	 * 
	 * @param id of the BidList.
	 * @return return the BidList by id.
	 * @throws Exception BidList not found with this id.
	 */
	@Override
	public BidList getBidListById(Integer id) throws Exception {
		logger.debug("getBidListById");
		BidList bid = bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid BidList Id:" + id));
		return bid;
	}

	/**
	 * this method is to delete a BidList.
	 * 
	 * @param bid deletes.
	 */
	@Override
	public void delBidList(BidList bid) {
		logger.debug("delBidList");
		bidListRepository.delete(bid);
	}

}
