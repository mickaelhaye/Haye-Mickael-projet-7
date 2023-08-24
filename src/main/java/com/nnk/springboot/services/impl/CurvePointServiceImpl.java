package com.nnk.springboot.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;

/**
 * this class is the service for the entity CurvePoint.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Service
public class CurvePointServiceImpl implements CurvePointService {
	private static Logger logger = LoggerFactory.getLogger(CurvePointServiceImpl.class);
	@Autowired
	private CurvePointRepository curvePointRepository;

	/**
	 * this method recovers all CurvePoint.
	 * 
	 * @return return a list of CurvePoint.
	 */
	@Override
	public Iterable<CurvePoint> getCurvePoints() {
		logger.debug("getCurvePoints");
		return curvePointRepository.findAll();
	}

	/**
	 * this method saves or updates a CurvePoint.
	 * 
	 * @param curvePoint saves or updates.
	 * @return CurvePoint with id.
	 */
	@Override
	public CurvePoint addCurvePoint(CurvePoint curvePoint) {
		logger.debug("addCurvePoint");
		return curvePointRepository.save(curvePoint);
	}

	/**
	 * this method recovers a CurvePoint by this id.
	 * 
	 * @param id of the CurvePoint.
	 * @return return the CurvePoint by id.
	 * @throws Exception CurvePoint not found with this id.
	 */
	@Override
	public CurvePoint getCurvePointById(Integer id) throws Exception {
		logger.debug("getCurvePointById");
		CurvePoint curvePoint = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid CurvePoint Id:" + id));
		return curvePoint;
	}

	/**
	 * this method deletes a CurvePoint.
	 * 
	 * @param curvePoint deletes.
	 */
	@Override
	public void delCurvePoint(CurvePoint curvePoint) {
		logger.debug("delCurvePoint");
		curvePointRepository.delete(curvePoint);
	}

}
