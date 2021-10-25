package com.salonspa.example.functional;

import static com.example.utils.TestUtils.asJsonString;
import static com.example.utils.TestUtils.businessTestFile;
import static com.example.utils.TestUtils.currentTest;
import static com.example.utils.TestUtils.yakshaAssert;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.utils.MasterData;
import com.salonspa.example.controller.UserController;
import com.salonspa.example.dto.UserDto;
import com.salonspa.example.service.UserService;

@Order(1)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userService;
	
	@Test
	void testUserRestEndpointForFindAllUsersIsExposedAndWorking() throws Exception {
		List<UserDto> list = new ArrayList<>();
		list.add(MasterData.getUserDto());
		Mockito.when(userService.getUsers()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userservice/getall")
				.content(MasterData.asJsonString(MasterData.getUser()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? "true" : "false"),	businessTestFile);
		
	}
	
	@Test
	void testUserRestEndpointForDeleteUsersById() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		int id = userDto.getUserId();
		Mockito.when(userService.deleteUser(id)).thenReturn(userDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/userservice/delete/"+id)
				.content(MasterData.asJsonString(MasterData.getUser()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(userDto))? "true" : "false"),	businessTestFile);
	}
	
	@Test
	void testUserRestEndpointForAddNewUser() throws Exception {
		int count[] = new int[1];
		UserDto userDto = com.example.utils.MasterData.getUserDto();
		Mockito.when(userService.saveUser(userDto)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				++count[0];
				return userDto;
			}
		});

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/userservice/add")
				.content(com.example.utils.MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}
}
