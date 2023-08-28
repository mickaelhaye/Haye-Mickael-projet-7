package com.nnk.springboot.ControllerTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class CurveControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CurvePointService curvePointService;

	@BeforeEach
	public void setUp() {
		CurvePoint curvePoint = new CurvePoint();
		curvePoint.setTerm(10d);
		curvePointService.addCurvePoint(curvePoint);
	}

	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/curvePoint/list")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("curvePoint/list"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("curvePoints"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("httpServletRequest"));
	}

	@Test
	void addBidFormTest() throws Exception {

		mockMvc.perform(get("/curvePoint/add")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("curvePoint/add"));
	}

	@Test
	void validateTest() throws Exception {
		mockMvc.perform(post("/curvePoint/validate")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/curvePoint/list"));
	}

	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/curvePoint/update/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/curvePoint/list"));

		mockMvc.perform(get("/curvePoint/update/1")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("curvePoint/update"));

	}

	@Test
	void updateCurveTest() throws Exception {
		mockMvc.perform(post("/curvePoint/update/1")).andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/curvePoint/list"));

	}

	@Test
	void deleteCurveTest() throws Exception {
		mockMvc.perform(get("/curvePoint/delete/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/curvePoint/list"));

		mockMvc.perform(get("/curvePoint/delete/1")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/curvePoint/list"));
	}

}
