package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

/**
 * this interface is the service for the entity CurvePoint.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public interface CurvePointService {
	/**
	 * this method recovers all CurvePoint.
	 * 
	 * @return return a list of CurvePoint.
	 */
	public Iterable<CurvePoint> getCurvePoints();

	/**
	 * this method saves or updates a CurvePoint.
	 * 
	 * @param curvePoint saves or updates.
	 * @return CurvePoint with id.
	 */
	public CurvePoint addCurvePoint(CurvePoint curvePoint);

	/**
	 * this method recovers a CurvePoint by this id.
	 * 
	 * @param id of the CurvePoint.
	 * @return return the CurvePoint by id.
	 * @throws Exception CurvePoint not found with this id.
	 */
	public CurvePoint getCurvePointById(Integer id) throws Exception;

	/**
	 * this method deletes a CurvePoint.
	 * 
	 * @param curvePoint deletes.
	 */
	public void delCurvePoint(CurvePoint curvePoint);

}
