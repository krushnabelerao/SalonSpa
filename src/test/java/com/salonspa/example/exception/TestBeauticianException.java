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

import com.salonspa.example.controller.BeauticianController;
import com.salonspa.example.dto.BeauticianDto;
import com.salonspa.example.entity.ExceptionResponse;
import com.salonspa.example.service.BeauticianService;

@Order(2)
@WebMvcTest(BeauticianController.class)
@AutoConfigureMockMvc
public class TestBeauticianException {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BeauticianService beauticianService;
	
	@Test
	public void testBeauticianDataValidationCheckIsAddedInController() throws Exception {
		BeauticianDto beauticianDto = com.example.utils.MasterData.getBeauticianDto();
		ExceptionResponse exResponse = new ExceptionResponse(
				"Beautician Not added", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(beauticianService.saveBeautician(beauticianDto))
				.thenThrow(new CommonException("Beautician Not added"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beauticianservice/add")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 405 && exResponse!=null ? true : false),
				exceptionTestFile);

	}
	
	@Test
	void testBeauticianExceptionIsThrownAndHandledIfBeauticianNotAvailableWhileGettingAllBeauticians() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse(
				"Data Not Found", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(beauticianService.getBeauticians())
				.thenThrow(new CommonException("Data Not Found"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beauticianservice/getall")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 400 && exResponse!=null ? true : false),
				exceptionTestFile);

	}

	@Test
	void testBeauticianExceptionIsThrownAndHandledIfBeauticianIdIsNotValidWhileDeleting() throws Exception {

		BeauticianDto beauticianDto = com.example.utils.MasterData.getBeauticianDto();
		Integer id = beauticianDto.getBeauticianId();

		ExceptionResponse exResponse = new ExceptionResponse(
				"Beautician with Id - " + id + " not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(beauticianService.deleteBeautician(id))
				.thenThrow(new CommonException("Beautician with Id - " + id + " not found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/beauticianservice/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 404 && exResponse!=null) ? true : false,
				exceptionTestFile);
	}
}
