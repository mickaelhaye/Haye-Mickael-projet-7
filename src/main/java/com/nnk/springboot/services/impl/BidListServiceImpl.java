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
}
