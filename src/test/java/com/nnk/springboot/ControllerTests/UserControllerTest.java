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

/**
 * this class is to test the UserContoller methods.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	/**
	 * this method is to add a UserList in the dataBase
	 */
	@BeforeEach
	public void setUp() {
		User user = new User();
		user.setFullname("newFullname");
		user.setUsername("newUsername");
		user.setPassword("Info06/17");
		userService.addUser(user);

	}

	/**
	 * this method is to test the method home with the endpoint get /user/list
	 * 
	 * @throws Exception standard
	 */
	@Test
	void homeTest() throws Exception {
		mockMvc.perform(get("/user/list")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("user/list"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("users"))
				.andExpect(MockMvcResultMatchers.content().string(containsString("newFullname")));

	}

	/**
	 * this method is to test the method addUserForm with the endpoint get /user/add
	 * 
	 * @throws Exception standard
	 */
	@Test
	void addUserFormTest() throws Exception {

		mockMvc.perform(get("/user/add")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("user/add"));
	}

	/**
	 * this method is to test the method validate with the endpoint post
	 * /user/validate
	 * 
	 * @throws Exception standard
	 */
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

	/**
	 * this method is to test the method showUpdateForm with the endpoint get
	 * /user/update/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void showUpdateFormTest() throws Exception {

		mockMvc.perform(get("/user/update/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/user/list"));

		mockMvc.perform(get("/user/update/1")).andExpect(status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("user/update"));

	}

	/**
	 * this method is to test the method updateUser with the endpoint post
	 * /user/update/{id}
	 * 
	 * @throws Exception standard
	 */
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

	/**
	 * this method is to test the method deleteUser with the endpoint post
	 * /user/delete/{id}
	 * 
	 * @throws Exception standard
	 */
	@Test
	void deleteUserTest() throws Exception {
		mockMvc.perform(get("/user/delete/0")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/user/list"));

		mockMvc.perform(get("/user/delete/1")).andExpect(status().is3xxRedirection()).andDo(print())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/user/list"));
	}

}
