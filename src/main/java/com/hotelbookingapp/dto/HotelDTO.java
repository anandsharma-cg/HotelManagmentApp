package com.hotelbookingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class HotelDTO {
	private Long hotelId;
	private String hotelName;
	private String hotelCity;
	private String address;
	private String description;
	private String hotelEmail;
	private String hotelContactNo;
	private String hotelWebsite;
}
