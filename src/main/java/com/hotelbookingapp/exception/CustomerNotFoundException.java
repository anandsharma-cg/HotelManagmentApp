package com.hotelbookingapp.exception;

public class CustomerNotFoundException extends RuntimeException{
	public CustomerNotFoundException(String msg) {
		super(msg);
	}
}
