package com.hotelbookingapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hotelbookingapp.entity.RoomDetails;
import com.hotelbookingapp.util.RoomStatus;

public interface RoomDetailsRepository extends JpaRepository<RoomDetails, Long> {
	List<RoomDetails> findByHotel_HotelId(Long hotelId);
    List<RoomDetails> findByHotel_HotelName(String hotelName);
    List<RoomDetails> findByHotel_HotelIdAndStatus(Long hotelId, RoomStatus status);
}
