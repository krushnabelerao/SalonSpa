package com.salonspa.example.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class BeauticianDto {
	@Id
	private int beauticianId;
	@NotBlank
	@NotNull
	private String beauticianName;
	@NotBlank
	@NotNull
	private String phonenumber;
	@NotBlank
	@NotNull
	private String email;
	@NotBlank
	@NotNull
	private String experience;
	
	
	
	public BeauticianDto() {
		super();
	}
	public BeauticianDto( int beauticianId,  String beauticianName,  String phonenumber,
			 String email, String experience) {
		super();
		this.beauticianId = beauticianId;
		this.beauticianName = beauticianName;
		this.phonenumber = phonenumber;
		this.email = email;
		this.experience = experience;
	}
	public int getBeauticianId() {
		return beauticianId;
	}
	public void setBeauticianId(int beauticianId) {
		this.beauticianId = beauticianId;
	}
	public String getBeauticianName() {
		return beauticianName;
	}
	public void setBeauticianName(String beauticianName) {
		this.beauticianName = beauticianName;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + beauticianId;
		result = prime * result + ((beauticianName == null) ? 0 : beauticianName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((experience == null) ? 0 : experience.hashCode());
		result = prime * result + ((phonenumber == null) ? 0 : phonenumber.hashCode());
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
		BeauticianDto other = (BeauticianDto) obj;
		if (beauticianId != other.beauticianId)
			return false;
		if (beauticianName == null) {
			if (other.beauticianName != null)
				return false;
		} else if (!beauticianName.equals(other.beauticianName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (experience == null) {
			if (other.experience != null)
				return false;
		} else if (!experience.equals(other.experience))
			return false;
		if (phonenumber == null) {
			if (other.phonenumber != null)
				return false;
		} else if (!phonenumber.equals(other.phonenumber))
			return false;
		return true;
	}

}
