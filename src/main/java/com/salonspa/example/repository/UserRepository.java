package com.salonspa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salonspa.example.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
