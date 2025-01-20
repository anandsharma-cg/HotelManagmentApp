package com.hotelbookingapp.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.hotelbookingapp.dto.HotelDTO;
import com.hotelbookingapp.entity.Hotel;
import com.hotelbookingapp.exception.ResourceNotFoundException;
import com.hotelbookingapp.mapper.HotelMapper;
import com.hotelbookingapp.repository.HotelRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements IHotelService {
	
    private HotelRepository hotelRepository;
    
    private HotelMapper hotelMapper;
    
    @Override
    public HotelDTO addHotel(HotelDTO hotelDTO) {
        Hotel hotel = hotelMapper.toHotel(hotelDTO);
        hotel = hotelRepository.save(hotel);
        return hotelMapper.toHotelDTO(hotel);
    }
    
    @Override
    public HotelDTO updateHotel(HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.findById(hotelDTO.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        hotel.setHotelName(hotelDTO.getHotelName());
        hotel.setHotelCity(hotelDTO.getHotelCity());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setHotelEmail(hotelDTO.getHotelEmail());
        hotel.setHotelContactNo(hotelDTO.getHotelContactNo());
        hotel.setHotelWebsite(hotelDTO.getHotelWebsite());
        hotelRepository.save(hotel);
        return hotelMapper.toHotelDTO(hotel);
    }


    @Override
    public List<HotelDTO> findAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(hotelMapper::toHotelDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public HotelDTO findByHotelId(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        return hotelMapper.toHotelDTO(hotel);
    }

    @Override
    public List<HotelDTO> findByCity(String city) {
        List<Hotel> hotels = hotelRepository.findByHotelCity(city);
        return hotels.stream()
                .map(hotelMapper::toHotelDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> findByHotelName(String hotelName) {
        List<Hotel> hotels = hotelRepository.findByHotelName(hotelName);
        return hotels.stream()
                .map(hotelMapper::toHotelDTO)
                .collect(Collectors.toList());
    }
    
    public void removeHotel(Long hotelId) {
        hotelRepository.deleteById(hotelId);
    }
}
