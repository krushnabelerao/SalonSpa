package com.salonspa.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonspa.example.dto.ProductDto;
import com.salonspa.example.entity.Product;
import com.salonspa.example.repository.ProductRepository;

@Service
public class ProductServicesImpl implements ProductService {

	@Autowired
	private ProductRepository productReposiroty;
	
	@Override
	public ProductDto saveProduct(ProductDto productDto) {
		Product product = new Product();
		product.setProductId(productDto.getProductId());
		product.setProductName(productDto.getProductName());
		product.setProductType(productDto.getProductType());
		product.setPrice(productDto.getPrice());
		product.setAdvantages(productDto.getAdvantages());
		productReposiroty.save(product);
         return productDto;
    }

	@Override
    public List<ProductDto> getProducts() {
		List<Product> products = productReposiroty.findAll();

		return products.stream()
				.map(productDto -> new ProductDto( productDto.getProductId(),productDto.getProductName(), productDto.getProductType(),
						productDto.getAdvantages(),productDto.getPrice()))
				.collect(Collectors.toList());
    }
    
	@Override
    public ProductDto deleteProduct(int id) {
		Product product = productReposiroty.findById(id).orElse(null);
    	ProductDto servicesDto = new ProductDto();
		if (product != null) {
			servicesDto.setProductId(product.getProductId());
			servicesDto.setProductName(product.getProductName());
			servicesDto.setProductType(product.getProductType());
			servicesDto.setAdvantages(product.getAdvantages());
					servicesDto.setPrice(product.getPrice());
		}
		productReposiroty.deleteById(id);
		return servicesDto;
    }
	

}
