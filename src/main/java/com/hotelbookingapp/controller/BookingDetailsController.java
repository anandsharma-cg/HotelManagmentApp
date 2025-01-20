package com.hotelbookingapp.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hotelbookingapp.dto.BookingDetailsDTO;
import com.hotelbookingapp.service.IBookingDetailsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/booking-details")
public class BookingDetailsController {
	IBookingDetailsService service;	
	
	@PostMapping
	public ResponseEntity<BookingDetailsDTO> saveBooking(@Valid @RequestBody BookingDetailsDTO bdDTO){
		
		BookingDetailsDTO responseDTO = this.service.addBookingDetails(bdDTO);
		return new ResponseEntity<BookingDetailsDTO>(responseDTO,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<BookingDetailsDTO>> 
	findAllBooking()
	{
		List<BookingDetailsDTO> responseDTOList = this.service.findAllBookings();
		return new ResponseEntity<List<BookingDetailsDTO>>(responseDTOList,HttpStatus.OK);
	}
	
	@GetMapping("{bookingId}")
	public ResponseEntity<BookingDetailsDTO> 
	findBookingByID(@PathVariable Long bookingId)
	{
		BookingDetailsDTO responseDTO = this.service.findByBookingId(bookingId);
		return new ResponseEntity<BookingDetailsDTO>(responseDTO,HttpStatus.OK);
	}
	
	@PutMapping("{bookingId}")
	public ResponseEntity<BookingDetailsDTO> 
	updateBookingByID(@PathVariable Long bookingId,@Valid @RequestBody BookingDetailsDTO bdDTO)
	{
		bdDTO.setBookingId(bookingId);
		BookingDetailsDTO responseDTO = this.service.updateBookingDetails(bdDTO);
		return new ResponseEntity<BookingDetailsDTO>(responseDTO,HttpStatus.OK);
	}
	
	@DeleteMapping("{bookingId}")
	public ResponseEntity<BookingDetailsDTO> 
	deleteBookingByID(@PathVariable Long bookingId)
	{
		BookingDetailsDTO responseDTO = this.service.cancelBooking(bookingId);
		return new ResponseEntity<BookingDetailsDTO>(responseDTO,HttpStatus.OK);
	}
	
	@GetMapping("/findByHotelId/{hotelId}")
	public ResponseEntity<List<BookingDetailsDTO>> 
	findBookingByHotelID(@PathVariable Long hotelId)
	{
		List<BookingDetailsDTO> responseDTOList = this.service.findByHotelId(hotelId);
		return new ResponseEntity<List<BookingDetailsDTO>>(responseDTOList,HttpStatus.OK);
	}
	
	@GetMapping("/findByCustomerId/{customerId}")
	public ResponseEntity<List<BookingDetailsDTO>> 
	findBookingByCustomerId(@PathVariable Long customerId)
	{
		List<BookingDetailsDTO> responseDTOList = this.service.findByCustomerId(customerId);
		return new ResponseEntity<List<BookingDetailsDTO>>(responseDTOList,HttpStatus.OK);
	}
	
	@GetMapping("/findByCustomerIdAndcheckOutDate")
	public ResponseEntity<List<BookingDetailsDTO>> 
	findBookingByCustomerIdAndCheckOutDate(@RequestParam Long customerId,@RequestParam LocalDate checkOutDate)
	{
		List<BookingDetailsDTO> responseDTOList = this.service.findByCustomerIdAndCheckOutDate(customerId,checkOutDate);
		return new ResponseEntity<List<BookingDetailsDTO>>(responseDTOList,HttpStatus.OK);
	}
}
