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

import com.salonspa.example.dto.AppointmentDto;
import com.salonspa.example.service.AppointmentService;

@RestController
@RequestMapping("/appointmentservice")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	@PostMapping("/add")
	public AppointmentDto savePayment(@Valid @RequestBody AppointmentDto appointmentDto) {
        return appointmentService.saveAppointment(appointmentDto);
    }

	@GetMapping("/getall")
    public List<AppointmentDto> getAppointments() {
        return appointmentService.getAppointments();
    }
	
	@DeleteMapping("/delele/{id}")
	public AppointmentDto deleteAppointment(@PathVariable int id) {
		return appointmentService.deleteAppointment(id);
    }

}
