package com.salonspa.example.service;

import java.util.List;

import com.salonspa.example.dto.ShopDto;

public interface ShopService {
	public ShopDto saveShop(ShopDto shopDto);

    public List<ShopDto> getShops();

    public ShopDto deleteShop(int id);

}
