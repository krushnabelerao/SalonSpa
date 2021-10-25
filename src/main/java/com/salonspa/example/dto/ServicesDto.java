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

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + serviceId;
	long temp;
	temp = Double.doubleToLongBits(servicePrice);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((servicesAvailable == null) ? 0 : servicesAvailable.hashCode());
	result = prime * result + shopId;
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
	ServicesDto other = (ServicesDto) obj;
	if (serviceId != other.serviceId)
		return false;
	if (Double.doubleToLongBits(servicePrice) != Double.doubleToLongBits(other.servicePrice))
		return false;
	if (servicesAvailable == null) {
		if (other.servicesAvailable != null)
			return false;
	} else if (!servicesAvailable.equals(other.servicesAvailable))
		return false;
	if (shopId != other.shopId)
		return false;
	return true;
}


}
