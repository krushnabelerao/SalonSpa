package com.salonspa.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonspa.example.dto.AppointmentDto;
import com.salonspa.example.entity.Appointment;
import com.salonspa.example.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	private AppointmentRepository appointmentRepository;
	public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
		Appointment beautician = new Appointment();
		beautician.setAppointmentId(appointmentDto.getAppointmentId());
		beautician.setUserId(appointmentDto.getUserId());
		beautician.setAppointmentDatetime(appointmentDto.getAppointmentDatetime());
		appointmentRepository.save(beautician);
         return appointmentDto;
    }

	@Override
    public List<AppointmentDto> getAppointments() {
		List<Appointment> appointments = appointmentRepository.findAll();

		return appointments.stream()
				.map(appointmentDro -> new AppointmentDto(appointmentDro.getAppointmentId(),appointmentDro.getUserId(),
						appointmentDro.getAppointmentDatetime()))
				.collect(Collectors.toList());
    }
	
	@Override
    public AppointmentDto deleteAppointment(int id) {
		Appointment appointment = appointmentRepository.findById(id).orElse(null);
		AppointmentDto appointmentDto = new AppointmentDto();
		if (appointment != null) {
			appointmentDto.setAppointmentId(appointment.getAppointmentId());
			appointmentDto.setUserId(appointment.getUserId());
			appointmentDto.setAppointmentDatetime(appointment.getAppointmentDatetime());
		}
		appointmentRepository.deleteById(id);
		return appointmentDto;
    }
	
}
