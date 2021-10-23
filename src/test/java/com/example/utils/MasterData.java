package com.example.utils;

import java.io.IOException;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salonspa.example.dto.AdminDto;
import com.salonspa.example.dto.AppointmentDto;
import com.salonspa.example.dto.BeauticianDto;
import com.salonspa.example.dto.PaymentDto;
import com.salonspa.example.dto.ProductDto;
import com.salonspa.example.dto.ServicesDto;
import com.salonspa.example.dto.ShopDto;
import com.salonspa.example.dto.UserDto;
import com.salonspa.example.entity.Admin;
import com.salonspa.example.entity.Payment;
import com.salonspa.example.entity.Product;
import com.salonspa.example.entity.Shop;
import com.salonspa.example.entity.User;

public class MasterData {
	
	public static Shop getShop() {
		Shop shop = new Shop();
		shop.setId(1);
		shop.setAddress("pune");
		shop.setName("Jenkins");
		shop.setDescription("This is the best CI/CD tool");
		shop.setStatus("Done");
		return shop;
	}
	
	public static ShopDto getShopDto() {
		ShopDto shopDto = new ShopDto();
		shopDto.setId(1);
		shopDto.setAddress("pune");
		shopDto.setName("Jenkins");
		shopDto.setDescription("Good Shop");
		shopDto.setStatus("pending");
		return shopDto;
	}
	
	public static User getUser() {
		User user = new User();
		user.setUserId(1);
		user.setUserName("Yaksha");
		user.setAddress("pune");
		user.setEmail("yak@gmail.com");
		user.setPassword("pass");
		user.setPhoneNumber("123");
		return user;
	}
	
	public static UserDto getUserDto()
	{
		UserDto userDto = new UserDto();
		userDto.setUserId(1);
		userDto.setUserName("Yaksha");
		userDto.setAddress("pune");
		userDto.setEmail("yak@gmail.com");
		userDto.setPassword("pass");
		userDto.setPhoneNumber("123");
		return userDto;
	}
	
	public static Admin getAdmin()
	{
		Admin admin = new Admin();
		admin.setAdminId(1);
		admin.setUserName("admin");
		admin.setAddress("pune");
		admin.setEmail("admin@admin.com");
		admin.setPassword("adminpass");
		admin.setPhoneNumber("123456");
		return admin;
	}
	
	public static AdminDto getAdminDto()
	{
		AdminDto AdminDto = new AdminDto();
		AdminDto.setAdminId(10);
		AdminDto.setUserName("admin");
		AdminDto.setAddress("pune");
		AdminDto.setEmail("admin@admin.com");
		AdminDto.setPassword("adminpass");
		AdminDto.setPhoneNumber("123456");
		return AdminDto;
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	public static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
    public static ShopDto createShop(Integer id, String address, String title, String status, 
    		String description) {
    	ShopDto shopDto = new ShopDto();
    	shopDto.setId(id);
    	shopDto.setAddress(address);
    	shopDto.setDescription(description);
    	shopDto.setStatus(status);
    	shopDto.setName(title);
  	 	return shopDto;
    }

	public static ProductDto getProductDto() {
		ProductDto productDto = new ProductDto();
		productDto.setProductId(10);
		productDto.setProductName("ABCD");
		productDto.setAdvantages("good");
		productDto.setProductType("AAAAAAAA");
		productDto.setPrice(2525.25);
		return productDto;
	}

	public static Product getProduct() {
		Product product = new Product();
		product.setProductId(10);
		product.setProductName("ABCD");
		product.setAdvantages("good");
		product.setProductType("AAAAAAAA");
		product.setPrice(2525.25);
		return product;
	}
	
	public static Payment getPayment() {
		Payment payment = new Payment();
		payment.setPaymentId(10);
		payment.setAmount(2525.25);
		payment.setPaymentMode("CASH");
		payment.setShopId(10);
		payment.setUserId(2);
		return payment;
	}
	public static PaymentDto getPaymentDto() {
		PaymentDto payment = new PaymentDto();
		payment.setPaymentId(10);
		payment.setAmount(2525.25);
		payment.setPaymentMode("CASH");
		payment.setShopId(10);
		payment.setUserId(2);
		return payment;
	}

	public static BeauticianDto getBeauticianDto()
	{
		BeauticianDto beauticianDto = new BeauticianDto();
		beauticianDto.setBeauticianId(10);
		beauticianDto.setBeauticianName("Krushna");
		beauticianDto.setEmail("krushna@gmail.com");
		beauticianDto.setExperience("5 years");
		beauticianDto.setPhonenumber("123456");
		return beauticianDto;
	}
	
	public static AppointmentDto getAppointmentDto()
	{
		AppointmentDto appointmentDto = new AppointmentDto();
		appointmentDto.setAppointmentId(10);
		appointmentDto.setUserId(10);
		appointmentDto.setAppointmentDatetime("2021-12-20");
		return appointmentDto;
	}
	
	public static ServicesDto getServicesDto()
	{
		ServicesDto servicesDto = new ServicesDto();
		servicesDto.setServiceId(10);
		servicesDto.setShopId(10);
		servicesDto.setServicePrice(2525.25);
		servicesDto.setServicesAvailable("open all days");
		return servicesDto;
	}
}
