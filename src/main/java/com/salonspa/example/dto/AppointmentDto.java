package com.salonspa.example.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class AppointmentDto {
	@Id
	private int appointmentId;
	@NotBlank
	@NotNull
	private int userId;
	@NotBlank
	@NotNull
	private String appointmentDatetime;
	
	public AppointmentDto() {
		super();
	}
	public AppointmentDto( int appointmentId,  int userId,  String appointmentDatetime) {
		super();
		this.appointmentId = appointmentId;
		this.userId = userId;
		this.appointmentDatetime = appointmentDatetime;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAppointmentDatetime() {
		return appointmentDatetime;
	}
	public void setAppointmentDatetime(String appointmentDatetime) {
		this.appointmentDatetime = appointmentDatetime;
	}
	
	
}
