package com.salonspa.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonspa.example.dto.PaymentDto;
import com.salonspa.example.entity.Payment;
import com.salonspa.example.repository.PaymentRepository;

@Service
public class PaymentServicesImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public PaymentDto savePayment(PaymentDto paymentDto) {
		Payment payment = new Payment();
		payment.setPaymentId(paymentDto.getPaymentId());
		payment.setShopId(paymentDto.getShopId());
		payment.setUserId(paymentDto.getUserId());
		payment.setPaymentMode(paymentDto.getPaymentMode());
		payment.setAmount(paymentDto.getAmount());
		paymentRepository.save(payment);
         return paymentDto;
    }

	@Override
    public List<PaymentDto> getPayments() {
		List<Payment> payments = paymentRepository.findAll();

		return payments.stream()
				.map(paymentDto -> new PaymentDto(paymentDto.getPaymentId(),paymentDto.getPaymentMode(),
						paymentDto.getAmount(),paymentDto.getUserId(),paymentDto.getShopId()))
				.collect(Collectors.toList());
    }
	
	@Override
    public PaymentDto deletePayment(int id) {
		Payment payment = paymentRepository.findById(id).orElse(null);
		PaymentDto paymentDto = new PaymentDto();
		if (payment != null) {
			paymentDto.setPaymentId(payment.getPaymentId());
			paymentDto.setShopId(payment.getShopId());
			paymentDto.setUserId(payment.getUserId());
			paymentDto.setPaymentMode(payment.getPaymentMode());
			paymentDto.setAmount(payment.getAmount());
		}
		paymentRepository.deleteById(id);
		return paymentDto;
    }
	
}
