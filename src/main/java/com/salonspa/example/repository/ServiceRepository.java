package com.salonspa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salonspa.example.entity.Services;

public interface ServiceRepository extends JpaRepository<Services,Integer> {

}
