package com.salonspa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salonspa.example.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
