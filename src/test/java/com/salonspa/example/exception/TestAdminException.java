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
		AdminDto adminDto = com.example.utils.MasterData.getAdminDto();
		Mockito.when(adminService.saveAdmin(adminDto)).thenReturn(adminDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adiminservice/add")
				.content(com.example.utils.MasterData.asJsonString(adminDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);

	}
	
	@Test
	void testAdminExceptionIsThrownAndHandledIfAdminNotAvailableWhileGettingAllAdmins() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse(
				"Data Not Found", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(adminService.getAdmins())
				.thenThrow(new CommonException("Data Not Found"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allocationservice/getall")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 404 && exResponse!=null ? true : false),
				exceptionTestFile);

	}

	@Test
	void testAdminExceptionIsThrownAndHandledIfAdminIdIsNotValidWhileDeleting() throws Exception {

		AdminDto adminDto = com.example.utils.MasterData.getAdminDto();
		Integer id = adminDto.getAdminId();

		ExceptionResponse exResponse = new ExceptionResponse(
				"Admin with Id - " + id + " not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(this.adminService.deleteAdmin(id))
				.thenThrow(new CommonException("Admin with Id - " + id + " not found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/adiminservice/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 404 && exResponse!=null) ? true : false,
				exceptionTestFile);
	}
}
