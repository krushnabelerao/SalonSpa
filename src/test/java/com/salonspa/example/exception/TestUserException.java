package com.salonspa.example.exception;

import static com.example.utils.TestUtils.currentTest;
import static com.example.utils.TestUtils.exceptionTestFile;
import static com.example.utils.TestUtils.yakshaAssert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.salonspa.example.controller.UserController;
import com.salonspa.example.dto.UserDto;
import com.salonspa.example.entity.ExceptionResponse;
import com.salonspa.example.service.UserService;

@Order(2)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class TestUserException {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
		// Exception
		@Test
		public void testUserDataValidationCheckIsAddedInController() throws Exception {
			
			ExceptionResponse exResponse = new ExceptionResponse("", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/userservice/add")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
					
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			yakshaAssert(currentTest(),
					result.getResponse().getContentAsString().contains(exResponse.getMessage())? true : false,exceptionTestFile);
		}
		
		
		
		@Test
		void testUserExceptionIsThrownAndHandledIfUserIdIsNotValidWhileDeleting() throws Exception{
			
			UserDto userDto = com.example.utils.MasterData.getUserDto();
			Integer id = userDto.getUserId();
			
			ExceptionResponse exResponse = new ExceptionResponse("", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/userservice/delete/" + id)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
					
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			yakshaAssert(currentTest(),
					result.getResponse().getContentAsString().contains(exResponse.getMessage())? true : false,exceptionTestFile);
			
		}
	
		@Test
		void testUserExceptionIsThrownAndHandledIfUserIdIsNotValidWhileGettingNoteById() throws Exception{
			
			ExceptionResponse exResponse = new ExceptionResponse("", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userservice/getall")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
					
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			yakshaAssert(currentTest(),
					(result.getResponse().getContentAsString().contains(exResponse.getMessage())? true : false),exceptionTestFile);
			
		}
		
	

}
