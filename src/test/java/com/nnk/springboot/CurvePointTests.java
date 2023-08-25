package com.nnk.springboot;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

/**
 * this class is to test for the entity CurvePoint.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurvePointService curvePointService;

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
		curvePointService.delCurvePoint(curvePoint);
		CurvePoint curvePointList = null;
		try {
			curvePointList = curvePointService.getCurvePointById(id);
		} catch (Exception e) {
			assertNull(curvePointList);
		}

	}

}
