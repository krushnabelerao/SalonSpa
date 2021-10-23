package com.salonspa.example.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class ProductDto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	@NotBlank
	@NotNull
	private String productName;
	@NotBlank
	@NotNull
	private String productType;
	@NotBlank
	@NotNull
	private String advantages;
	@NotBlank
	@NotNull
	private double price;
	
	
	
	public ProductDto( int productId,  String productName,  String productType,
			 String advantages,  double price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.advantages = advantages;
		this.price = price;
	}
	public ProductDto() {
		super();
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getAdvantages() {
		return advantages;
	}
	public void setAdvantages(String advantages) {
		this.advantages = advantages;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
