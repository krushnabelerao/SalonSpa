package com.salonspa.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salonspa.example.dto.ShopDto;
import com.salonspa.example.service.ShopService;

@RestController
@RequestMapping("/shopservice")
public class ShopController {

    @Autowired
    private ShopService service;

    @PostMapping("/add")
    public ShopDto addShop(@Valid @RequestBody ShopDto shopDto) {
        return service.saveShop(shopDto);
    }

    @GetMapping("/getall")
    public List<ShopDto> findAllShops() {
        return service.getShops();
    }

    @DeleteMapping("/delete/{id}")
    public ShopDto deleteShop(@PathVariable int id) {
        return service.deleteShop(id);
    }
}
