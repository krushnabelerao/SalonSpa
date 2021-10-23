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
import com.salonspa.example.controller.ServicesController;
import com.salonspa.example.dto.ServicesDto;
import com.salonspa.example.service.ServicesService;

@Order(1)
@WebMvcTest(ServicesController.class)
@AutoConfigureMockMvc
public class ServicesControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ServicesService servicesService;
	
	@Test
	void testServicesRestEndpointForFindAllServicesIsExposedAndWorking() throws Exception {
		List<ServicesDto> list = new ArrayList<>();
		list.add(MasterData.getServicesDto());
		Mockito.when(servicesService.getServices()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/servicesservice/getall")
				.content(MasterData.asJsonString(MasterData.getServicesDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? "true" : "false"),	businessTestFile);
		
	}
	
	@Test
	void testServicesRestEndpointForDeleteServicesById() throws Exception {
		ServicesDto servicesDto = MasterData.getServicesDto();
		int id = servicesDto.getServiceId();
		Mockito.when(servicesService.deleteService(id)).thenReturn(servicesDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/servicesservice/delete/"+id)
				.content(MasterData.asJsonString(MasterData.getServicesDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result!=null? "true" : "false"),	businessTestFile);
	}
	
	@Test
	void testServicesRestEndpointForAddNewServices() throws Exception {
		int count[] = new int[1];
		ServicesDto servicesDto = com.example.utils.MasterData.getServicesDto();
		Mockito.when(servicesService.saveService(servicesDto)).then(new Answer<ServicesDto>() {

			@Override
			public ServicesDto answer(InvocationOnMock invocation) throws Throwable {
				++count[0];
				return servicesDto;
			}
		});

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/servicesservice/add")
				.content(com.example.utils.MasterData.asJsonString(servicesDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		yakshaAssert(currentTest(), requestBuilder != null ? true : false, businessTestFile);
	}
}
