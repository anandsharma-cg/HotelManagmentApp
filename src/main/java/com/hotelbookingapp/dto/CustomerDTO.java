package com.hotelbookingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerDTO {
	private Long customerId;	
	private String customerName;
	private Long customerMobile;
	private String customerEmail;
	private String customerAddress;
}
