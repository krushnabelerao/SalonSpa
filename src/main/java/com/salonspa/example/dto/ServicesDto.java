package com.salonspa.example.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class ServicesDto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int serviceId;
	@NotBlank
	@NotNull
private int shopId;
	@NotBlank
	@NotNull
private String  servicesAvailable;
	@NotBlank
	@NotNull
private double servicePrice;
	
	
	
	

public ServicesDto(int serviceId, int shopId, String servicesAvailable,
			 double servicePrice) {
		super();
		this.serviceId = serviceId;
		this.shopId = shopId;
		this.servicesAvailable = servicesAvailable;
		this.servicePrice = servicePrice;
	}

public ServicesDto() {
	super();
}

public int getServiceId() {
	return serviceId;
}
public void setServiceId(int serviceId) {
	this.serviceId = serviceId;
}
public int getShopId() {
	return shopId;
}
public void setShopId(int shopId) {
	this.shopId = shopId;
}
public double getServicePrice() {
	return servicePrice;
}
public void setServicePrice(double servicePrice) {
	this.servicePrice = servicePrice;
}
public String getServicesAvailable() {
	return servicesAvailable;
}
public void setServicesAvailable(String servicesAvailable) {
	this.servicesAvailable = servicesAvailable;
}

}
