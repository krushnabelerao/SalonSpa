package com.salonspa.example.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class AdminDto {
	@Id
	private Integer adminId;
	@NotBlank
	@NotNull
	private String userName;
	@NotBlank
	@NotNull
	private String phoneNumber;
	@NotBlank
	@NotNull
	private String address;
	@NotBlank
	@NotNull
	private String email;
	@NotBlank
	@NotNull
	private String password;
	
	public AdminDto(Integer adminId2, String userName2, String address2, String phoneNumber2, String email2,
			String password2) {
		this.adminId = adminId2;
		this.userName = userName2;
		this.address = address2;
		this.phoneNumber = phoneNumber2;
		this.email = email2;
		this.password = password2;
	}
	
	public AdminDto() {
		
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
