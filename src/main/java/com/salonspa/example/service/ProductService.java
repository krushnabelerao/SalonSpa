package com.salonspa.example.service;

import java.util.List;

import com.salonspa.example.dto.ProductDto;

public interface ProductService {
	public ProductDto saveProduct(ProductDto product);

    public List<ProductDto> getProducts();
    
    public ProductDto deleteProduct(int id);
	
}
