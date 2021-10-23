package com.salonspa.example.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class PaymentDto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentId;
	@NotBlank
	@NotNull
	private String paymentMode;
	@NotBlank
	@NotNull
	private double amount;
	@NotBlank
	@NotNull
	private int userId;
	@NotBlank
	@NotNull
	private int shopId;
	
	public PaymentDto() {
		super();
	}
	public PaymentDto( int paymentId,  String paymentMode,  double amount,
			int userId,  int shopId) {
		super();
		this.paymentId = paymentId;
		this.paymentMode = paymentMode;
		this.amount = amount;
		this.userId = userId;
		this.shopId = shopId;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
	
}
