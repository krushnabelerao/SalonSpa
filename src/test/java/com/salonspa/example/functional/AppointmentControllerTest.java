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
import com.salonspa.example.controller.AppointmentController;
import com.salonspa.example.dto.AppointmentDto;
import com.salonspa.example.service.AppointmentService;

@Order(1)
@WebMvcTest(AppointmentController.class)
@AutoConfigureMockMvc
public class AppointmentControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AppointmentService appointmentService;
	
	@Test
	void testAppointmentRestEndpointForFindAllAppointmentIsExposedAndWorking() throws Exception {
		List<AppointmentDto> list = new ArrayList<>();
		list.add(MasterData.getAppointmentDto());
		Mockito.when(appointmentService.getAppointments()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/appointmentservice/getall")
				.content(MasterData.asJsonString(MasterData.getAppointmentDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? "true" : "false"),	businessTestFile);
		
	}
	
	@Test
	void testAppointmentRestEndpointForDeleteAppointmentById() throws Exception {
		AppointmentDto appointmentDto = MasterData.getAppointmentDto();
		int id = appointmentDto.getAppointmentId();
		Mockito.when(appointmentService.deleteAppointment(id)).thenReturn(appointmentDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/appointmentservice/delete/"+id)
				.content(MasterData.asJsonString(MasterData.getAppointmentDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result!=null? "true" : "false"),	businessTestFile);
	}
	
	@Test
	void testAppointmentRestEndpointForAddNewAppointment() throws Exception {
		int count[] = new int[1];
		AppointmentDto appointmentDto = com.example.utils.MasterData.getAppointmentDto();
		Mockito.when(appointmentService.saveAppointment(appointmentDto)).then(new Answer<AppointmentDto>() {

			@Override
			public AppointmentDto answer(InvocationOnMock invocation) throws Throwable {
				++count[0];
				return appointmentDto;
			}
		});

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/appointmentservice/add")
				.content(com.example.utils.MasterData.asJsonString(appointmentDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		yakshaAssert(currentTest(), requestBuilder != null ? true : false, businessTestFile);
	}
}
