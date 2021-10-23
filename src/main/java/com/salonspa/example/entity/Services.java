package com.salonspa.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Services {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int serviceId;
private int shopId;
private String  servicesAvailable;
private double servicePrice;

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
