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

import com.salonspa.example.controller.ProductController;
import com.salonspa.example.dto.ProductDto;
import com.salonspa.example.entity.ExceptionResponse;
import com.salonspa.example.service.ProductService;

@Order(2)
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class TestProductEcxception {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;
	
	@Test
	public void testProductDataValidationCheckIsAddedInController() throws Exception {
		ProductDto ProductDto = com.example.utils.MasterData.getProductDto();
		ExceptionResponse exResponse = new ExceptionResponse(
				"Product Not added", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(productService.saveProduct(ProductDto))
				.thenThrow(new CommonException("Product Not added"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/productservice/add")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 405 && exResponse!=null ? true : false),
				exceptionTestFile);

	}
	
	@Test
	void testProductExceptionIsThrownAndHandledIfProductNotAvailableWhileGettingAllProducts() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse(
				"Data Not Found", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(productService.getProducts())
				.thenThrow(new CommonException("Data Not Found"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/productservice/getall")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 400 && exResponse!=null ? true : false),
				exceptionTestFile);

	}

	@Test
	void testProductExceptionIsThrownAndHandledIfProductIdIsNotValidWhileDeleting() throws Exception {

		ProductDto ProductDto = com.example.utils.MasterData.getProductDto();
		Integer id = ProductDto.getProductId();

		ExceptionResponse exResponse = new ExceptionResponse(
				"Product with Id - " + id + " not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(this.productService.deleteProduct(id))
				.thenThrow(new CommonException("Product with Id - " + id + " not found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/productservice/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == 400 && exResponse!=null) ? true : false,
				exceptionTestFile);
	}
}
