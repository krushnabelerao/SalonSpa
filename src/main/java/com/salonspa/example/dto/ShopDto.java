package com.salonspa.example.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;
public class ShopDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
	@NotNull
	@Length(min = 5)
    private String name;
    @NotNull
	@Length(max = 20,min = 5)
    private String address;
    @NotBlank
	@NotNull
	@Length(max = 20 , min = 3)
    private String status;
    @Length(min = 5)
    private String description;
    
	public ShopDto(int id2, String name2, String address2, String status2, String description2) {
		this.id  = id2;
		this.name = name2;
		this.address = address2;
		this.status = status2;
		this.description = description2;
	}
	public ShopDto() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
