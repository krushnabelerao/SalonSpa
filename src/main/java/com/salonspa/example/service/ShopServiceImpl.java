package com.salonspa.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonspa.example.dto.ShopDto;
import com.salonspa.example.entity.Shop;
import com.salonspa.example.repository.ShopRepository;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository repository;

    @Override
    public ShopDto saveShop(ShopDto shopDto) {
    	Shop shop = new Shop();
    	shop.setId(shopDto.getId());
    	shop.setName(shopDto.getName());
    	shop.setAddress(shopDto.getAddress());
    	shop.setDescription(shopDto.getDescription());
    	shop.setStatus(shopDto.getStatus());
         repository.save(shop);
         return shopDto;
    }

    @Override
    public List<ShopDto> getShops() {
    	List<Shop> allocations = repository.findAll();

		return allocations.stream()
				.map(allocation -> new ShopDto(allocation.getId(), allocation.getName(), allocation.getAddress(),
						allocation.getStatus(), allocation.getDescription()))
				.collect(Collectors.toList());
    }

    @Override
    public ShopDto deleteShop(int id) {
    	Shop allocation = repository.findById(id).orElse(null);
    	ShopDto allocationDto = new ShopDto();
		if (allocation != null) {
			allocationDto.setId(allocation.getId());
			allocationDto.setName(allocation.getName());
			allocationDto.setAddress(allocation.getAddress());
			allocationDto.setStatus(allocation.getStatus());
			allocationDto.setDescription(allocation.getDescription());
		}
		repository.deleteById(id);
		return allocationDto;
    }


}
