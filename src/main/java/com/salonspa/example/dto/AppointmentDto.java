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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointmentDatetime == null) ? 0 : appointmentDatetime.hashCode());
		result = prime * result + appointmentId;
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppointmentDto other = (AppointmentDto) obj;
		if (appointmentDatetime == null) {
			if (other.appointmentDatetime != null)
				return false;
		} else if (!appointmentDatetime.equals(other.appointmentDatetime))
			return false;
		if (appointmentId != other.appointmentId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	
}
