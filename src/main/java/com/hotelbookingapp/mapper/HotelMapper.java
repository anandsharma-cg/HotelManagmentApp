package com.hotelbookingapp.mapper;

import org.springframework.stereotype.Component;
import com.hotelbookingapp.dto.HotelDTO;
import com.hotelbookingapp.entity.Hotel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class HotelMapper {
	
    public HotelDTO toHotelDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setHotelId(hotel.getHotelId());
        hotelDTO.setHotelName(hotel.getHotelName());
        hotelDTO.setHotelCity(hotel.getHotelCity());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setDescription(hotel.getDescription());
        hotelDTO.setHotelEmail(hotel.getHotelEmail());
        hotelDTO.setHotelContactNo(hotel.getHotelContactNo());
        hotelDTO.setHotelWebsite(hotel.getHotelWebsite());

        return hotelDTO;
    }

    public Hotel toHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setHotelId(hotelDTO.getHotelId());
        hotel.setHotelName(hotelDTO.getHotelName());
        hotel.setHotelCity(hotelDTO.getHotelCity());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setHotelEmail(hotelDTO.getHotelEmail());
        hotel.setHotelContactNo(hotelDTO.getHotelContactNo());
        hotel.setHotelWebsite(hotelDTO.getHotelWebsite());        

        return hotel;
    }
}
