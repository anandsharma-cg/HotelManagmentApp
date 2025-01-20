package com.hotelbookingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbookingapp.dto.HotelDTO;
import com.hotelbookingapp.service.HotelServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	HotelServiceImpl hotelService;
	
	@PostMapping
	public ResponseEntity<HotelDTO>addHotel(@Valid @RequestBody HotelDTO hotelDTO){
		
		HotelDTO addedHotel = hotelService.addHotel(hotelDTO);
        return new ResponseEntity<>(addedHotel, HttpStatus.CREATED);
	}
	
	 @PostMapping("/update")
	    public ResponseEntity<HotelDTO> updateHotel(@Valid @RequestBody HotelDTO hotelDTO) {
	        HotelDTO updatedHotel = hotelService.updateHotel(hotelDTO);
	        return new ResponseEntity<>(updatedHotel,HttpStatus.OK);
	    }

	    @GetMapping("/all")
	    public ResponseEntity<List<HotelDTO>> findAllHotels() {
	        List<HotelDTO> hotels = hotelService.findAllHotels();
	        return new ResponseEntity<>(hotels,HttpStatus.OK);
	    }

	    @GetMapping("/{hotelId}")
	    public ResponseEntity<HotelDTO> findByHotelId(@PathVariable Long hotelId) {
	        HotelDTO hotel = hotelService.findByHotelId(hotelId);
	        return new ResponseEntity<>(hotel,HttpStatus.OK);
	    }

	    @GetMapping("/city/{city}")
	    public ResponseEntity<List<HotelDTO>> findByCity(@PathVariable String city) {
	        List<HotelDTO> hotels = hotelService.findByCity(city);
	        return new ResponseEntity<>(hotels,HttpStatus.OK);
	    }

	    @GetMapping("/name/{hotelName}")
	    public ResponseEntity<List<HotelDTO>> findByHotelName(@PathVariable String hotelName) {
	        List<HotelDTO> hotels = hotelService.findByHotelName(hotelName);
	        return new ResponseEntity<>(hotels,HttpStatus.OK);
	    }
	
	@DeleteMapping("/remove/{hotelId}")
	 public ResponseEntity<Void> removeHotel(@PathVariable Long hotelId) {
	        hotelService.removeHotel(hotelId);
	        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	    }
}
