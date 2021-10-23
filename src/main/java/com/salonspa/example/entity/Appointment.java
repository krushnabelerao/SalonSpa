package com.salonspa.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int appointmentId;
	
	private int userId;
	private String appointmentDatetime;
	
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
