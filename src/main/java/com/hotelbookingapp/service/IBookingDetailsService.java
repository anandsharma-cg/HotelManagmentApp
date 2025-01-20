package com.hotelbookingapp.service;

import java.time.LocalDate;
import java.util.List;
import com.hotelbookingapp.dto.BookingDetailsDTO;

public interface IBookingDetailsService {
	BookingDetailsDTO addBookingDetails(BookingDetailsDTO bookingDetailsDTO);

	BookingDetailsDTO updateBookingDetails(BookingDetailsDTO bookingDetailsDTO);

	BookingDetailsDTO cancelBooking(Long bookingId);

	List<BookingDetailsDTO> findAllBookings();

	BookingDetailsDTO findByBookingId(Long bookingId);

	List<BookingDetailsDTO> findByCustomerId(Long customerId);

	List<BookingDetailsDTO> findByHotelId(Long hotelId);

	List<BookingDetailsDTO> findByCustomerIdAndCheckOutDate(Long customerId, LocalDate checkOutDate);
	
}
