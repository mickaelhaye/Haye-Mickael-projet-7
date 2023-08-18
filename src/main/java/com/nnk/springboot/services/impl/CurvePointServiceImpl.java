package com.nnk.springboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;

@Service
public class CurvePointServiceImpl implements CurvePointService {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Override
	public Iterable<CurvePoint> getCurvePoints() {
		return curvePointRepository.findAll();
	}

	@Override
	public CurvePoint addCurvePoint(CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}

	@Override
	public CurvePoint getCurvePointById(Integer id) throws Exception {
		CurvePoint curvePoint = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
		return curvePoint;
	}

	@Override
	public void delCurvePoint(CurvePoint curvePoint) {
		curvePointRepository.delete(curvePoint);
	}

}
