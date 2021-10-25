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
import com.salonspa.example.controller.AdminController;
import com.salonspa.example.dto.AdminDto;
import com.salonspa.example.service.AdminService;

@Order(1)
@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc
public class AdminControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AdminService adminService;
	
	@Test
	void testAdminRestEndpointForFindAllAdminIsExposedAndWorking() throws Exception {
		List<AdminDto> list = new ArrayList<>();
		list.add(MasterData.getAdminDto());
		Mockito.when(adminService.getAdmins()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/adminservice/getall")
				.content(MasterData.asJsonString(MasterData.getUser()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list))? "true" : "false"),	businessTestFile);
		
	}
	
	@Test
	void testAdminRestEndpointForDeleteAdminsById() throws Exception {
		AdminDto adminDto = MasterData.getAdminDto();
		int id = adminDto.getAdminId();
		Mockito.when(adminService.deleteAdmin(id)).thenReturn(adminDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/adminservice/delete/"+id)
				.content(MasterData.asJsonString(MasterData.getAdminDto()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result!=null ? "true" : "false"),	businessTestFile);
	}
	
	@Test
	void testAdminRestEndpointForAddNewAdmin() throws Exception {
		int count[] = new int[1];
		AdminDto AdminDto = com.example.utils.MasterData.getAdminDto();
		Mockito.when(adminService.saveAdmin(AdminDto)).then(new Answer<AdminDto>() {

			@Override
			public AdminDto answer(InvocationOnMock invocation) throws Throwable {
				++count[0];
				return AdminDto;
			}
		});

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adminservice/add")
				.content(com.example.utils.MasterData.asJsonString(AdminDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}
}
