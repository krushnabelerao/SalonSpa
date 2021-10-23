package com.salonspa.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salonspa.example.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
