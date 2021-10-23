package com.salonspa.example.exception;

public class ShopIdNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShopIdNotFoundException(String message) {
		super(message);
	}
}
