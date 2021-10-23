package com.salonspa.example.service;

import java.util.List;

import com.salonspa.example.dto.AppointmentDto;

public interface AppointmentService {
	public AppointmentDto saveAppointment(AppointmentDto appointment); 

    public List<AppointmentDto> getAppointments(); 
	
    public AppointmentDto deleteAppointment(int id); 
	
}
