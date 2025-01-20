package com.hotelbookingapp.service;

import org.springframework.stereotype.Service;

import com.hotelbookingapp.dto.RoomDetailsDTO;
import com.hotelbookingapp.entity.BookingDetails;
import com.hotelbookingapp.entity.Hotel;
import com.hotelbookingapp.entity.RoomDetails;
import com.hotelbookingapp.exception.ResourceNotFoundException;
import com.hotelbookingapp.mapper.RoomMapper;
import com.hotelbookingapp.repository.BookingDetailsRepository;
import com.hotelbookingapp.repository.HotelRepository;
import com.hotelbookingapp.repository.RoomDetailsRepository;
import com.hotelbookingapp.util.RoomStatus;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomDetailsServiceImpl implements IRoomDetailsService {


    private RoomDetailsRepository roomDetailsRepository;
    
    private BookingDetailsRepository bookingRepository;
    
    private RoomMapper roomMapper;
    
    private HotelRepository hotelRepository;
    
    
    @Override
    public RoomDetailsDTO addRoomDetails(RoomDetailsDTO roomDetails) {
        RoomDetails roomEntity = roomMapper.toRoomDetails(roomDetails);
        Hotel hotel = roomEntity.getHotel();
        hotel.addRoom(roomEntity);
        roomEntity = roomDetailsRepository.save(roomEntity);
        return roomMapper.toRoomDetailsDTO(roomEntity);
    }

    @Override
    public RoomDetailsDTO updateRoomDetails(RoomDetailsDTO roomDetails) {
        RoomDetails roomEntity = roomDetailsRepository.findById(roomDetails.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        roomEntity.setRoomNo(roomDetails.getRoomNo());
        roomEntity.setRoomType(roomDetails.getRoomType());
        roomEntity.setNoOfPersons(roomDetails.getNoOfPersons());
        roomEntity.setRatePerDay(roomDetails.getRatePerDay());
        roomEntity.setStatus(roomDetails.getStatus());
        roomEntity = roomDetailsRepository.save(roomEntity);
        return roomMapper.toRoomDetailsDTO(roomEntity);
    }

    @Override
    public RoomDetailsDTO findByRoomId(Long roomId) {
        RoomDetails roomEntity = roomDetailsRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        return roomMapper.toRoomDetailsDTO(roomEntity);
    }

    @Override
    public List<RoomDetailsDTO> findByHotelId(Long hotelId) {
    	
    	hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found for hotelId: " + hotelId));
        
    	
        List<RoomDetails> roomEntities = roomDetailsRepository.findByHotel_HotelId(hotelId);
        return roomEntities.stream()
                .map(roomMapper::toRoomDetailsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDetailsDTO> findByHotelName(String hotelName) {
    	if(hotelRepository.findByHotelName(hotelName).isEmpty())
    		throw new ResourceNotFoundException("Hotel not found of Name: "+hotelName);
    	
    	List<RoomDetails> roomEntities = roomDetailsRepository.findByHotel_HotelName(hotelName);
        return roomEntities.stream()
                .map(roomMapper::toRoomDetailsDTO)
                .collect(Collectors.toList());
    }
    
    
	@Override
	public List<RoomDetailsDTO> findAvailableRoomsByDate(LocalDate from, LocalDate to) {
		 	List<RoomDetails> allRooms = roomDetailsRepository.findAll();
	        
	        
	        List<RoomDetails> availableRooms = allRooms.stream()
	                .filter(room -> isRoomAvailable(room, from, to))
	                .collect(Collectors.toList());
	        
	        
	        return availableRooms.stream()
	                .map(roomMapper::toRoomDetailsDTO)
	                .collect(Collectors.toList());
	}
	
	 private boolean isRoomAvailable(RoomDetails room, LocalDate from, LocalDate to) {
	        List<BookingDetails> bookings = room.getBookingDetailsList();

	        
	        return bookings == null || bookings.stream()
	                .noneMatch(booking ->
	                        (booking.getCheckIn().isBefore(to) || booking.getCheckIn().isEqual(to)) &&
	                        (booking.getCheckOut().isAfter(from) || booking.getCheckOut().isEqual(from))
	                );
	    }
	 
	 @Override
		public List<RoomDetailsDTO> findRoomsByHotelAndStatus(Long hotelId, String status) {
			List<RoomDetails> rooms = roomDetailsRepository.findByHotel_HotelIdAndStatus(hotelId, RoomStatus.valueOf(status));
		    return rooms.stream()
		            .map(roomMapper::toRoomDetailsDTO)
		            .collect(Collectors.toList());
		}

	
	
	@Override
	public List<RoomDetailsDTO> removeRoomsFromHotel(Long hotelId, List<RoomDetailsDTO> roomsToRemove) {
	    Hotel hotel = hotelRepository.findById(hotelId)
	            .orElseThrow(() -> new RuntimeException("Hotel not found for ID: " + hotelId));

	    List<RoomDetails> roomsToRemoveEntities = roomsToRemove.stream()
	            .map(roomMapper::toRoomDetails)
	            .collect(Collectors.toList());

	    hotel.getListOfRooms().removeAll(roomsToRemoveEntities);

	    hotelRepository.save(hotel);

	    List<RoomDetailsDTO> remainingRooms = hotel.getListOfRooms().stream()
	            .map(roomMapper::toRoomDetailsDTO)
	            .collect(Collectors.toList());

	    return remainingRooms;
	}

	@Override
	public List<RoomDetailsDTO> removeRoomsFromBooking(Long bookingId, List<RoomDetailsDTO> listOfRooms) {
		 BookingDetails bookingDetails = bookingRepository.findById(bookingId)
		            .orElseThrow(() -> new ResourceNotFoundException("Booking details not found for ID: " + bookingId));

		 List<RoomDetails> roomsToRemove = listOfRooms.stream()
		            .map(roomMapper::toRoomDetails)
		            .collect(Collectors.toList());
		 
		    bookingDetails.getListOfRooms().removeAll(roomsToRemove);

		    
		    bookingDetails = bookingRepository.save(bookingDetails);

		    
		    List<RoomDetailsDTO> updatedRooms = bookingDetails.getListOfRooms().stream()
		            .map(roomMapper::toRoomDetailsDTO)
		            .collect(Collectors.toList());

		    return updatedRooms;
	}


	

	
	

  
}

	