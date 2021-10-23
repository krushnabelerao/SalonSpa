package com.salonspa.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salonspa.example.dto.ServicesDto;
import com.salonspa.example.dto.ShopDto;
import com.salonspa.example.entity.Services;
import com.salonspa.example.entity.Shop;
import com.salonspa.example.repository.ServiceRepository;
@Service
@Transactional
public class ServicesServiceImpl implements ServicesService{
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Override
	public ServicesDto saveService(ServicesDto servicesDto) {
		Services services = new Services();
		services.setServiceId(servicesDto.getServiceId());
		services.setServicePrice(servicesDto.getServicePrice());
		services.setServicesAvailable(servicesDto.getServicesAvailable());
		services.setShopId(servicesDto.getShopId());
    	serviceRepository.save(services);
         return servicesDto;
    }
	
	@Override
	public List<ServicesDto> getServices() {
		List<Services> services = serviceRepository.findAll();

		return services.stream()
				.map(servicesDto -> new ServicesDto(servicesDto.getServiceId(),servicesDto.getShopId(),servicesDto.getServicesAvailable(),servicesDto.getServicePrice()))
				.collect(Collectors.toList());
    }
	
	@Override
	public ServicesDto deleteService(int id) {
		Services services = serviceRepository.findById(id).orElse(null);
    	ServicesDto servicesDto = new ServicesDto();
		if (services != null) {
			servicesDto.setServiceId(services.getServiceId());
			servicesDto.setShopId(services.getShopId());
			servicesDto.setServicesAvailable(services.getServicesAvailable());
			servicesDto.setServicePrice(services.getServicePrice());
		}
		serviceRepository.deleteById(id);
		return servicesDto;
    }
	
	
}
