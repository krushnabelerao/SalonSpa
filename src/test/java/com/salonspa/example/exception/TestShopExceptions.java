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

import com.example.utils.MasterData;
import com.salonspa.example.controller.ShopController;
import com.salonspa.example.dto.ShopDto;
import com.salonspa.example.entity.ExceptionResponse;
import com.salonspa.example.service.ShopService;

@Order(2)
@WebMvcTest(ShopController.class)
@AutoConfigureMockMvc
public class TestShopExceptions {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ShopService shopService;

	
	
	
		// Exception
		@Test
		public void testDataValidationCheckIsAddedInController() throws Exception {
			ShopDto shopDto = MasterData.getShopDto();
			shopDto.setAddress("pr");
			Mockito.when(shopService.saveShop(shopDto))
			.thenReturn(shopDto);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/shopservice/add")
					.content(com.example.utils.MasterData.asJsonString(shopDto))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
					
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
			System.out.println(result.getResponse().getStatus());
			yakshaAssert(currentTest(),
					result.getResponse().getStatus() == 400? true : false,
							exceptionTestFile);
					
		}
		
		@Test
		public void testAbleToWorkWithCustomExceptions() throws Exception {
			ShopDto shopDto = com.example.utils.MasterData.getShopDto();
			shopDto.setAddress("pune");
			Mockito.when(shopService.saveShop(shopDto))
			.thenReturn(shopDto);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/shopservice/add")
					.content(com.example.utils.MasterData.asJsonString(shopDto))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
					
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
			
			yakshaAssert(currentTest(),
					result.getResponse().getStatus() == 400? true : false,
							exceptionTestFile);
					
		}
		
		@Test
		public void testExceptionIsThrownAndHandledInCaseOfInvalidData() throws Exception {
			ShopDto shopDto = com.example.utils.MasterData.getShopDto();
			shopDto.setAddress("pune");
			Mockito.when(shopService.saveShop(shopDto))
			.thenReturn(shopDto);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/shopservice/add")
					.content(com.example.utils.MasterData.asJsonString(shopDto))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
					
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
			
			yakshaAssert(currentTest(),
					result.getResponse().getStatus() == 400? true : false,
							exceptionTestFile);
					
		}
		
		@Test
		void testExceptionIsThrownAndHandledIfShopIdIsNotValidWhileDeleting() throws Exception{
			
			ShopDto shopDto = com.example.utils.MasterData.getShopDto();
			Integer id = shopDto.getId();
			
			ExceptionResponse exResponse = new ExceptionResponse("", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/shopservice/delete/" + id)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
					
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			yakshaAssert(currentTest(),
					result.getResponse().getContentAsString().contains(exResponse.getMessage())? true : false,exceptionTestFile);
			
		}
	
		@Test
		void testExceptionIsThrownAndHandledIfShopIdIsNotValidWhileGettingNoteById() throws Exception{
			ShopDto shopDto = com.example.utils.MasterData.getShopDto();
			Integer id = shopDto.getId();
			
			ExceptionResponse exResponse = new ExceptionResponse("", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopservice/getall/" + id)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
					
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			yakshaAssert(currentTest(),
					(result.getResponse().getContentAsString().contains(exResponse.getMessage())? true : false),exceptionTestFile);
			
		}
		
	
}
