package com.salonspa.example.service;

import java.util.List;

import com.salonspa.example.dto.ServicesDto;

public interface ServicesService {

	public ServicesDto saveService(ServicesDto services);
	
	public List<ServicesDto> getServices();
	
	public ServicesDto deleteService(int id);
	
}
