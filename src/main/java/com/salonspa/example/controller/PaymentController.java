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

import com.salonspa.example.dto.PaymentDto;
import com.salonspa.example.service.PaymentService;

@RestController
@RequestMapping("/paymentservice")
public class PaymentController {

	@Autowired
	private PaymentService paymentServices;
	@PostMapping("/add")
	public PaymentDto savePayment(@Valid @RequestBody PaymentDto paymentDto) {
        return paymentServices.savePayment(paymentDto);
    }

	@GetMapping("/getall")
    public List<PaymentDto> getPayments() {
        return paymentServices.getPayments();
    }
	
	@DeleteMapping("/delele/{id}")
	public PaymentDto deletePayment(@PathVariable int id) {
		return paymentServices.deletePayment(id);
    }
	
}
