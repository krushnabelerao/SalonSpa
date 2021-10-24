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

import com.salonspa.example.controller.PaymentController;
import com.salonspa.example.dto.PaymentDto;
import com.salonspa.example.entity.ExceptionResponse;
import com.salonspa.example.service.PaymentService;

@Order(2)
@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc
public class TestPaymentException {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PaymentService paymentService;
	
	@Test
	public void testPaymentDataValidationCheckIsAddedInController() throws Exception {
		PaymentDto paymentDto = com.example.utils.MasterData.getPaymentDto();
		ExceptionResponse exResponse = new ExceptionResponse(
				"Payment Not added", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(paymentService.savePayment(paymentDto))
				.thenThrow(new CommonException("Payment Not added"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/paymentservice/add")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 405 && exResponse!=null ? true : false),
				exceptionTestFile);

	}
	
	@Test
	void testPaymentExceptionIsThrownAndHandledIfPaymentNotAvailableWhileGettingAllPayments() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse(
				"Data Not Found", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(paymentService.getPayments())
				.thenThrow(new CommonException("Data Not Found"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/paymentservice/getall")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 400 && exResponse!=null ? true : false),
				exceptionTestFile);

	}

	@Test
	void testPaymentExceptionIsThrownAndHandledIfPaymentIdIsNotValidWhileDeleting() throws Exception {

		PaymentDto paymentDto = com.example.utils.MasterData.getPaymentDto();
		Integer id = paymentDto.getPaymentId();

		ExceptionResponse exResponse = new ExceptionResponse(
				"Payment with Id - " + id + " not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(this.paymentService.deletePayment(id))
				.thenThrow(new CommonException("Payment with Id - " + id + " not found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/paymentservice/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 404 && exResponse!=null) ? true : false,
				exceptionTestFile);
	}
}
