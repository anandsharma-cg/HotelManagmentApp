package com.hotelbookingapp.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookingDetailsDTO {

	private Long bookingId;
	private Long customerId;
	private Long hotelId;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Integer noOfAdults;
	private Integer noOfChildren;
	private Double totalAmount;
	private List<Long> listOfRooms;
}
