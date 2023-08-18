package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointService {

	public Iterable<CurvePoint> getCurvePoints();

	public CurvePoint addCurvePoint(CurvePoint curvePoint);

	public CurvePoint getCurvePointById(Integer id) throws Exception;

	public void delCurvePoint(CurvePoint curvePoint);

}
