package com.nnk.springboot.ServiceTests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

/**
 * this class is to test the CurvePointService methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */

@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurvePointService curvePointService;

	/**
	 * methods to test save, update, find and delete entity CurvePoint
	 */
	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint();
		curvePoint.setCurve_id(10);
		curvePoint.setTerm(10d);
		curvePoint.setValue(30d);

		// Save
		curvePoint = curvePointService.addCurvePoint(curvePoint);
		assertNotNull(curvePoint.getId());
		assertTrue(curvePoint.getCurve_id() == 10);

		// Update
		curvePoint.setCurve_id(20);
		curvePoint = curvePointService.addCurvePoint(curvePoint);
		assertTrue(curvePoint.getCurve_id() == 20);

		// Find
		List<CurvePoint> listResult = (List<CurvePoint>) curvePointService.getCurvePoints();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = curvePoint.getId();
		CurvePoint curvePointList = null;
		try {
			curvePointList = curvePointService.getCurvePointById(id);
			assertNotNull(curvePointList);
		} catch (Exception e) {
		}
		curvePointService.delCurvePoint(curvePoint);
		curvePointList = null;
		try {
			curvePointList = curvePointService.getCurvePointById(id);
		} catch (Exception e) {
			assertNull(curvePointList);
		}

	}

}
