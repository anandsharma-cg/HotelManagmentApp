package com.hotelbookingapp.mapper;

import org.springframework.stereotype.Component;
import com.hotelbookingapp.dto.RoomDetailsDTO;
import com.hotelbookingapp.entity.Hotel;
import com.hotelbookingapp.entity.RoomDetails;
import com.hotelbookingapp.exception.ResourceNotFoundException;
import com.hotelbookingapp.repository.HotelRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RoomMapper {
	
	private  HotelRepository hotelrepo;

    public  RoomDetailsDTO toRoomDetailsDTO(RoomDetails roomDetails) {
        RoomDetailsDTO roomDetailsDTO = new RoomDetailsDTO();
        
        roomDetailsDTO.setRoomId(roomDetails.getRoomId());
        roomDetailsDTO.setHotelId(roomDetails.getHotel().getHotelId());
        roomDetailsDTO.setRoomNo(roomDetails.getRoomNo());
        roomDetailsDTO.setRoomType(roomDetails.getRoomType());
        roomDetailsDTO.setNoOfPersons(roomDetails.getNoOfPersons());
        roomDetailsDTO.setRatePerDay(roomDetails.getRatePerDay());
        roomDetailsDTO.setStatus(roomDetails.getStatus());
        
        return roomDetailsDTO;
    }

    public  RoomDetails toRoomDetails(RoomDetailsDTO roomDetailsDTO) {
        RoomDetails roomDetails = new RoomDetails();
        
        roomDetails.setRoomId(roomDetailsDTO.getRoomId());
        Hotel hotel = hotelrepo.findById(roomDetailsDTO.getHotelId())
        		.orElseThrow(()->new ResourceNotFoundException("Room not found during mapping"));
        roomDetails.setHotel(hotel);
        roomDetails.setRoomNo(roomDetailsDTO.getRoomNo());
        roomDetails.setRoomType(roomDetailsDTO.getRoomType());
        roomDetails.setNoOfPersons(roomDetailsDTO.getNoOfPersons());
        roomDetails.setRatePerDay(roomDetailsDTO.getRatePerDay());
        roomDetails.setStatus(roomDetailsDTO.getStatus());
        
        return roomDetails;
    }
}
