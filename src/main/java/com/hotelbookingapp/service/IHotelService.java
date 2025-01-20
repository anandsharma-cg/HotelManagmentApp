package com.hotelbookingapp.service;

import java.util.List;
import com.hotelbookingapp.dto.HotelDTO;

public interface IHotelService {
	public HotelDTO addHotel(HotelDTO hotel);

	public HotelDTO updateHotel(HotelDTO hotel);

	public List<HotelDTO> findAllHotels();

	public HotelDTO findByHotelId(Long hotelId);

	public List<HotelDTO> findByCity(String city);

	public List<HotelDTO> findByHotelName(String hotelName);
	
	void removeHotel(Long hotelId);
	
}
