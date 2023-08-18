package com.nnk.springboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

@Service
public class BidListServiceImpl implements BidListService {

	@Autowired
	private BidListRepository bidListRepository;

	@Override
	public Iterable<BidList> getBidLists() {
		return bidListRepository.findAll();
	}

	@Override
	public BidList addBidList(BidList bid) {
		return bidListRepository.save(bid);
	}

	@Override
	public BidList getBidListById(Integer id) throws Exception {
		BidList bid = bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid BidList Id:" + id));
		return bid;
	}

	@Override
	public void delBidList(BidList bid) {
		bidListRepository.delete(bid);
	}

}
