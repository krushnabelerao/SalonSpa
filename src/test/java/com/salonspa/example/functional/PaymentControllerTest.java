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
import com.salonspa.example.controller.PaymentController;
import com.salonspa.example.dto.PaymentDto;
import com.salonspa.example.service.PaymentService;

@Order(1)
@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc
public class PaymentControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private PaymentService paymentService;
	
	@Test
	void testPaymentRestEndpointForFindAllPaymentIsExposedAndWorking() throws Exception {
		List<PaymentDto> list = new ArrayList<>();
		list.add(MasterData.getPaymentDto());
		Mockito.when(paymentService.getPayments()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/paymentservice/getall")
				.content(MasterData.asJsonString(MasterData.getPaymentDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? "true" : "false"),	businessTestFile);
		
	}
	
	@Test
	void testPaymentRestEndpointForDeletePaymentById() throws Exception {
		PaymentDto paymentDto = MasterData.getPaymentDto();
		int id = paymentDto.getPaymentId();
		Mockito.when(paymentService.deletePayment(id)).thenReturn(paymentDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/paymentservice/delete/"+id)
				.content(MasterData.asJsonString(MasterData.getPaymentDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result!=null? "true" : "false"),	businessTestFile);
	}
	
	@Test
	void testPaymentRestEndpointForAddNewPayment() throws Exception {
		int count[] = new int[1];
		PaymentDto paymentDto = com.example.utils.MasterData.getPaymentDto();
		Mockito.when(paymentService.savePayment(paymentDto)).then(new Answer<PaymentDto>() {

			@Override
			public PaymentDto answer(InvocationOnMock invocation) throws Throwable {
				++count[0];
				return paymentDto;
			}
		});

		RequestBuilder paymentservice = MockMvcRequestBuilders.post("/paymentservice/add")
				.content(com.example.utils.MasterData.asJsonString(paymentDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		yakshaAssert(currentTest(), paymentservice != null ? true : false, businessTestFile);
	}
}
