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
import com.salonspa.example.controller.BeauticianController;
import com.salonspa.example.dto.BeauticianDto;
import com.salonspa.example.service.BeauticianService;

@Order(1)
@WebMvcTest(BeauticianController.class)
@AutoConfigureMockMvc
public class BeauticianControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BeauticianService beauticianService;
	
	@Test
	void testBeauticianRestEndpointForFindAllBeauticianIsExposedAndWorking() throws Exception {
		List<BeauticianDto> list = new ArrayList<>();
		list.add(MasterData.getBeauticianDto());
		Mockito.when(beauticianService.getBeauticians()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beauticianservice/getall")
				.content(MasterData.asJsonString(MasterData.getBeauticianDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? "true" : "false"),	businessTestFile);
		
	}
	
	@Test
	void testBeauticianRestEndpointForDeleteBeauticianById() throws Exception {
		BeauticianDto beauticianDto = MasterData.getBeauticianDto();
		int id = beauticianDto.getBeauticianId();
		Mockito.when(beauticianService.deleteBeautician(id)).thenReturn(beauticianDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/beauticianservice/delete/"+id)
				.content(MasterData.asJsonString(MasterData.getBeauticianDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result!=null? "true" : "false"),	businessTestFile);
	}
	
	@Test
	void testBeauticianRestEndpointForAddNewBeautician() throws Exception {
		int count[] = new int[1];
		BeauticianDto beauticianDto = com.example.utils.MasterData.getBeauticianDto();
		Mockito.when(beauticianService.saveBeautician(beauticianDto)).then(new Answer<BeauticianDto>() {

			@Override
			public BeauticianDto answer(InvocationOnMock invocation) throws Throwable {
				++count[0];
				return beauticianDto;
			}
		});

		RequestBuilder paymentservice = MockMvcRequestBuilders.post("/beauticianservice/add")
				.content(com.example.utils.MasterData.asJsonString(beauticianDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		yakshaAssert(currentTest(), paymentservice != null ? true : false, businessTestFile);
	}
}
