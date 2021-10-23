package com.salonspa.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salonspa.example.dto.ShopDto;
import com.salonspa.example.entity.Shop;


public interface ShopRepository extends JpaRepository<Shop,Integer> {
	ShopDto findByName(String name);

	ShopDto findByStatus(String status);

	List<ShopDto> findByAddress(String address);
}

