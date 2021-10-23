package com.salonspa.example.exception;

import static com.example.utils.TestUtils.currentTest;
import static com.example.utils.TestUtils.exceptionTestFile;
import static com.example.utils.TestUtils.yakshaAssert;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import com.example.utils.MasterData;
import com.salonspa.example.controller.AdminController;
import com.salonspa.example.dto.AdminDto;
import com.salonspa.example.entity.ExceptionResponse;
import com.salonspa.example.service.AdminService;
@Order(2)
@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc
public class TestAdminException {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminService adminService;
	
	@Test
	public void testAdminDataValidationCheckIsAddedInController() throws Exception {
		AdminDto adminDto = MasterData.getAdminDto();
		adminDto.setAddress("pr");
		Mockito.when(adminService.saveAdmin(adminDto))
		.thenReturn(adminDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adminservice/add")
				.content(com.example.utils.MasterData.asJsonString(adminDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(),
				result.getResponse().getStatus() == 200? true : false,
						exceptionTestFile);
				
	}
	
	@Test
	void testAdminExceptionIsThrownAndHandledIfAdminIdIsNotValidWhileDeleting() throws Exception{
		
		AdminDto adminDto = com.example.utils.MasterData.getAdminDto();
		int id = adminDto.getAdminId();
		
		ExceptionResponse exResponse = new ExceptionResponse("", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/adminservice/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contains(exResponse.getMessage())? true : false,exceptionTestFile);
		
	}

	@Test
	void testAdminExceptionIsThrownAndHandledIfIsNotValidWhileGettingAdmin() throws Exception{
		ExceptionResponse exResponse = new ExceptionResponse("", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/adminservice/getall")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? true : false),exceptionTestFile);
		
	}

}
