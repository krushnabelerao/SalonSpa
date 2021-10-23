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
import com.salonspa.example.controller.ShopController;
import com.salonspa.example.dto.ShopDto;
import com.salonspa.example.service.ShopService;


@Order(1)
@WebMvcTest(ShopController.class)
@AutoConfigureMockMvc
class ShopControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ShopService shopService;
	
	@Test
	void testRestEndpointForFindAllShopIsExposedAndWorking() throws Exception {
		List<ShopDto> list = new ArrayList<ShopDto>();
		list.add(MasterData.getShopDto());
		Mockito.when(shopService.getShops()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopservice/getall")
				.content(MasterData.asJsonString(MasterData.getShop()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? "true" : "false"),	businessTestFile);
		
	}
	
	@Test
	void testAbleToDefineAppropriateClassesAndObjectsForAGivenScenario() throws Exception {
		List<ShopDto> list = new ArrayList<ShopDto>();
		list.add(MasterData.getShopDto());
		Mockito.when(shopService.getShops()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopservice/getall")
				.content(MasterData.asJsonString(MasterData.getShop()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? "true" : "false"),	businessTestFile);
		
	}
	
	@Test
	void testAbleToConfigureAndConnectToDatabase() throws Exception {
		List<ShopDto> list = new ArrayList<ShopDto>();
		list.add(MasterData.getShopDto());
		Mockito.when(shopService.getShops()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopservice/getall")
				.content(MasterData.asJsonString(MasterData.getShop()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? "true" : "false"),	businessTestFile);
		
	}
	
	@Test
	void testRestEndpointForFindAllShopIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];
		List<ShopDto> list = new ArrayList<ShopDto>();
		list.add(MasterData.getShopDto());
		Mockito.when(shopService.getShops()).then(new Answer<List<ShopDto>>() {

			@Override
			public List<ShopDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				
				count[0]++;
				return list;
			}
		});
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopservice/getall")
				.content(MasterData.asJsonString(MasterData.getShop()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(),count[0] == 1? true : false,businessTestFile);
		
	}
	
	
	
	@Test
	void testRestEndpointForFindingShopByIdIsExposedAndWorking() throws Exception{
		List<ShopDto> list = new ArrayList<ShopDto>();
		list.add(MasterData.getShopDto());
		Mockito.when(shopService.getShops())
		.thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopservice/getall")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? true : false),businessTestFile);
		
	}

	@Test
	void testRestEndpointForFindingShopByIdIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];
		List<ShopDto> list = new ArrayList<ShopDto>();
		list.add(MasterData.getShopDto());
		Mockito.when(shopService.getShops()).then(new Answer<List<ShopDto>>() {

			@Override
			public List<ShopDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				
				count[0]++;
				return list;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopservice/getall")
				.content(com.example.utils.MasterData.asJsonString(list))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
	
		yakshaAssert(currentTest(),count[0] == 1? true : false,businessTestFile);
				
	}
	
	
}
