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

}
