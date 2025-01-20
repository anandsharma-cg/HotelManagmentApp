package com.hotelbookingapp.service;

import java.time.LocalDate;
import java.util.List;
import com.hotelbookingapp.dto.RoomDetailsDTO;

public interface IRoomDetailsService {
	RoomDetailsDTO addRoomDetails(RoomDetailsDTO roomDetails);
 
	RoomDetailsDTO updateRoomDetails(RoomDetailsDTO roomDetails);
 
	RoomDetailsDTO findByRoomId(Long roomDetailsId);
 
	List<RoomDetailsDTO> findByHotelId(Long hotelId);
 
	List<RoomDetailsDTO> findByHotelName(String hotelName);
 
	List<RoomDetailsDTO> findAvailableRoomsByDate(LocalDate from, LocalDate to);
	
	List<RoomDetailsDTO> findRoomsByHotelAndStatus(Long hotelId,String status);
 
	List<RoomDetailsDTO> removeRoomsFromBooking(Long bookingId, List<RoomDetailsDTO> listOfRooms);
	
	List<RoomDetailsDTO> removeRoomsFromHotel(Long hotelId, List<RoomDetailsDTO> roomsToRemove) ;

}
