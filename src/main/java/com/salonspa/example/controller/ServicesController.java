package com.salonspa.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salonspa.example.dto.ServicesDto;
import com.salonspa.example.service.ServicesService;

@RestController
@RequestMapping("/servicesservice")
public class ServicesController {
	@Autowired
	private ServicesService servicesService;
	
	@PostMapping("/add")
    public ServicesDto addService(@Valid @RequestBody ServicesDto services) {
        return servicesService.saveService(services);
    }


    @GetMapping("/getall")
    public List<ServicesDto> getAllServices() {
        return servicesService.getServices();
    }
    
    @DeleteMapping("/delete/{id}")
    public ServicesDto deleteService(@PathVariable int id) {
    	return servicesService.deleteService(id);
    }
	

}
