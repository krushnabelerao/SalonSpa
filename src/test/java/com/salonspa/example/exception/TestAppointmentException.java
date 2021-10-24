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

import com.salonspa.example.controller.AppointmentController;
import com.salonspa.example.dto.AppointmentDto;
import com.salonspa.example.entity.ExceptionResponse;
import com.salonspa.example.service.AppointmentService;

@Order(2)
@WebMvcTest(AppointmentController.class)
@AutoConfigureMockMvc
public class TestAppointmentException {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AppointmentService appointmentService;
	
	@Test
	public void testAppointmentDataValidationCheckIsAddedInController() throws Exception {
		AppointmentDto appointmentDto = com.example.utils.MasterData.getAppointmentDto();
		ExceptionResponse exResponse = new ExceptionResponse(
				"Appointment Not added", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(appointmentService.saveAppointment(appointmentDto))
				.thenThrow(new CommonException("Appointment Not added"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/appointmentservice/add")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 405 && exResponse!=null ? true : false),
				exceptionTestFile);

	}
	
	@Test
	void testAppointmentExceptionIsThrownAndHandledIfAppointmentNotAvailableWhileGettingAllAppointments() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse(
				"Data Not Found", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(appointmentService.getAppointments())
				.thenThrow(new CommonException("Data Not Found"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/appointmentservice/getall")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 400 && exResponse!=null ? true : false),
				exceptionTestFile);

	}

	@Test
	void testAppointmentExceptionIsThrownAndHandledIfAppointmentIdIsNotValidWhileDeleting() throws Exception {

		AppointmentDto appointmentDto = com.example.utils.MasterData.getAppointmentDto();
		Integer id = appointmentDto.getAppointmentId();

		ExceptionResponse exResponse = new ExceptionResponse(
				"Appointment with Id - " + id + " not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(appointmentService.deleteAppointment(id))
				.thenThrow(new CommonException("Appointment with Id - " + id + " not found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/appointmentservice/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 404 && exResponse!=null) ? true : false,
				exceptionTestFile);
	}
}
