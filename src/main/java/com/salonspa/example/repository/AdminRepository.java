package com.salonspa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salonspa.example.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
