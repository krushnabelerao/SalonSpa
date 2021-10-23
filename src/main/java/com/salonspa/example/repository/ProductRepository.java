package com.salonspa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salonspa.example.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
