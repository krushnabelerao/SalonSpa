package com.salonspa.example.exception;

public class InvalidShopDataException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidShopDataException(String message) {
		super(message);
	}
}
