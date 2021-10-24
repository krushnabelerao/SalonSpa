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

import com.salonspa.example.controller.ServicesController;
import com.salonspa.example.dto.ServicesDto;
import com.salonspa.example.entity.ExceptionResponse;
import com.salonspa.example.service.ServicesService;

@Order(2)
@WebMvcTest(ServicesController.class)
@AutoConfigureMockMvc
public class TestServicesException {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ServicesService servicesService;
	
	@Test
	public void testServicesDataValidationCheckIsAddedInController() throws Exception {
		ServicesDto ServicesDto = com.example.utils.MasterData.getServicesDto();
		ExceptionResponse exResponse = new ExceptionResponse(
				"Services Not added", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(servicesService.saveService(ServicesDto))
				.thenThrow(new CommonException("Services Not added"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/servicesservice/add")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 405 && exResponse!=null ? true : false),
				exceptionTestFile);

	}
	
	@Test
	void testServicesExceptionIsThrownAndHandledIfServicesNotAvailableWhileGettingAllServicess() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse(
				"Data Not Found", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(servicesService.getServices())
				.thenThrow(new CommonException("Data Not Found"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/servicesservice/getall")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 400 && exResponse!=null ? true : false),
				exceptionTestFile);

	}

	@Test
	void testServicesExceptionIsThrownAndHandledIfServicesIdIsNotValidWhileDeleting() throws Exception {

		ServicesDto ServicesDto = com.example.utils.MasterData.getServicesDto();
		Integer id = ServicesDto.getServiceId();

		ExceptionResponse exResponse = new ExceptionResponse(
				"Services with Id - " + id + " not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(this.servicesService.deleteService(id))
				.thenThrow(new CommonException("Services with Id - " + id + " not found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/servicesservice/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 400 && exResponse!=null) ? true : false,
				exceptionTestFile);
	}
}
