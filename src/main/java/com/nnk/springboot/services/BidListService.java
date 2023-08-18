package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

public interface BidListService {
	public Iterable<BidList> getBidLists();

	public BidList addBidList(BidList bid);

	public BidList getBidListById(Integer id);

	public void delBidList(BidList bid);

}
