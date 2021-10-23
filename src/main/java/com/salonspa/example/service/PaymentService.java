package com.salonspa.example.service;

import java.util.List;

import com.salonspa.example.dto.PaymentDto;

public interface PaymentService {
	public PaymentDto savePayment(PaymentDto paymentDto);

    public List<PaymentDto> getPayments();
	
    public PaymentDto deletePayment(int id);
	
}
