package com.hotelbookingapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hotelbookingapp.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{
	 List<Hotel> findByHotelCity(String city);
	 List<Hotel> findByHotelName(String hotelName);
}
