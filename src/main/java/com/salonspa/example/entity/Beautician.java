package com.salonspa.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Beautician {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int beauticianId;
	private String beauticianName;
	private String phonenumber;
	private String email;
	private String experience;
	
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
