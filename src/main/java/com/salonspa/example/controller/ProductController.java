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

import com.salonspa.example.dto.ProductDto;
import com.salonspa.example.service.ProductService;

@RestController
@RequestMapping("/productservice")
public class ProductController {

	@Autowired
	private ProductService productServices;
	
	@PostMapping("/add")
	public ProductDto saveProduct(@Valid @RequestBody ProductDto product) {
        return productServices.saveProduct(product);
    }


	@GetMapping("/getall")
    public List<ProductDto> getProducts() {
        return productServices.getProducts();
    }
	
	@DeleteMapping("/delete/{id}")
	public ProductDto deleteProduct(@PathVariable int id) {
		return productServices.deleteProduct(id);
    }
	
}
