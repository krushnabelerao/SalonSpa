package com.salonspa.example.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class UserDto {
	
	@Id
	private int userId;
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
	
	public UserDto(int userId2, String userName2, String address2, String phoneNumber2, String email2,
			String password2) {
		super();
		this.userId = userId2;
		this.userName= userName2;
		this.phoneNumber = phoneNumber2;
		this.address = address2;
		this.email = email2;
		this.password = password2;
		
	}
	public UserDto() {
		
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
