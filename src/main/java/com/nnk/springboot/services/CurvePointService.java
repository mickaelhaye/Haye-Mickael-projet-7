package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointService {

	Iterable<CurvePoint> getCurvePoints();

	CurvePoint addCurvePoint(CurvePoint curvePoint);

	CurvePoint getCurvePointById(Integer id) throws Exception;

	void delCurvePoint(CurvePoint curvePoint);

}
