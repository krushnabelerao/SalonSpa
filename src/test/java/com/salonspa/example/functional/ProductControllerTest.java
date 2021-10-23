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
import com.salonspa.example.controller.ProductController;
import com.salonspa.example.dto.ProductDto;
import com.salonspa.example.service.ProductService;

@Order(1)
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductService productService;
	
	@Test
	void testProductRestEndpointForFindAllProductIsExposedAndWorking() throws Exception {
		List<ProductDto> list = new ArrayList<>();
		list.add(MasterData.getProductDto());
		Mockito.when(productService.getProducts()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/productservice/getall")
				.content(MasterData.asJsonString(MasterData.getProductDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? "true" : "false"),	businessTestFile);
		
	}
	
	@Test
	void testProductRestEndpointForDeleteProductById() throws Exception {
		ProductDto productDto = MasterData.getProductDto();
		int id = productDto.getProductId();
		Mockito.when(productService.deleteProduct(id)).thenReturn(productDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/productservice/delete/"+id)
				.content(MasterData.asJsonString(MasterData.getProductDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result!=null ? "true" : "false"),	businessTestFile);
	}
	
	@Test
	void testProductRestEndpointForAddNewProduct() throws Exception {
		int count[] = new int[1];
		ProductDto productDto = com.example.utils.MasterData.getProductDto();
		Mockito.when(productService.saveProduct(productDto)).then(new Answer<ProductDto>() {

			@Override
			public ProductDto answer(InvocationOnMock invocation) throws Throwable {
				++count[0];
				return productDto;
			}
		});

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/productservice/add")
				.content(com.example.utils.MasterData.asJsonString(productDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		yakshaAssert(currentTest(), requestBuilder!=null ? true : false, businessTestFile);
	}
}
