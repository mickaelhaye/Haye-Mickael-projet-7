package com.nnk.springboot.ControllerTests;

import static org.hamcrest.CoreMatchers.containsString;
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

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.dto.UserAddDto;
import com.nnk.springboot.services.UserService;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@BeforeEach
	public void setUp() {
		User user = new User();
		user.setFullname("newFullname");
		user.setUsername("newUsername");
		user.setPassword("Info06/17");
		userService.addUser(user);

	}

	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/user/list")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("user/list"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("users"))
				.andExpect(MockMvcResultMatchers.content().string(containsString("newFullname")));

	}

	@Test
	void addUserFormTest() throws Exception {

		mockMvc.perform(get("/user/add")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("user/add"));
	}

	@Test
	void validateTest() throws Exception {

		UserAddDto userAddDto = new UserAddDto();
		userAddDto.setFullname("newFullname");
		userAddDto.setPassword("Info06/17");
		userAddDto.setRole("newRole");
		userAddDto.setUsername("new");
		mockMvc.perform(post("/user/validate").flashAttr("userAddDto", userAddDto))
				.andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/user/list"));

		mockMvc.perform(post("/user/validate").flashAttr("userAddDto", userAddDto)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("user/add"));

	}

	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/user/update/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/user/list"));

		mockMvc.perform(get("/user/update/1")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("user/update"));

	}

	@Test
	void updateUserTest() throws Exception {
		User user = userService.getUserById(1);
		UserAddDto userAddDto = new UserAddDto();
		userAddDto.setFullname(user.getFullname());
		userAddDto.setPassword(user.getPassword());
		userAddDto.setRole(user.getRole());
		userAddDto.setUsername("newUsername2");
		mockMvc.perform(post("/user/update/1").flashAttr("userAddDto", userAddDto))
				.andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/user/list"));
		userAddDto.setUsername("newUsername2");
		mockMvc.perform(post("/user/update/1").flashAttr("userAddDto", userAddDto)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("user/update"));

	}

	@Test
	void deleteUserTest() throws Exception {
		mockMvc.perform(get("/user/delete/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/user/list"));

		mockMvc.perform(get("/user/delete/1")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/user/list"));
	}

}
