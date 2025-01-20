package com.hotelbookingapp.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.hotelbookingapp.dto.BookingDetailsDTO;
import com.hotelbookingapp.entity.BookingDetails;
import com.hotelbookingapp.entity.Customer;
import com.hotelbookingapp.entity.Hotel;
import com.hotelbookingapp.entity.RoomDetails;
import com.hotelbookingapp.repository.CustomerRepository;
import com.hotelbookingapp.repository.HotelRepository;
import com.hotelbookingapp.repository.RoomDetailsRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class BookingMapper {
	private CustomerRepository customerRepository;
	private HotelRepository hotelRepository;
	private RoomDetailsRepository roomRepository;
	
    public BookingDetailsDTO toBookingDetailsDTO(BookingDetails bookingDetails) {
        BookingDetailsDTO bookingDetailsDTO = new BookingDetailsDTO();
        bookingDetailsDTO.setBookingId(bookingDetails.getBookingId());
        
        bookingDetailsDTO.setCustomerId(bookingDetails.getCustomer().getCustomerId());
        bookingDetailsDTO.setHotelId(bookingDetails.getHotel().getHotelId());
        
        bookingDetailsDTO.setCheckIn(bookingDetails.getCheckIn());        
        bookingDetailsDTO.setCheckOut(bookingDetails.getCheckOut());
        bookingDetailsDTO.setNoOfAdults(bookingDetails.getNoOfAdults());
        bookingDetailsDTO.setNoOfChildren(bookingDetails.getNoOfChildren());
        bookingDetailsDTO.setTotalAmount(bookingDetails.getTotalAmount());
        
        // Mapping listOfRooms
        List<Long> roomDetailsDTOList = bookingDetails.getListOfRooms().stream()
            .map(roomDetails -> roomDetails.getRoomId())
            .collect(Collectors.toList());
        bookingDetailsDTO.setListOfRooms(roomDetailsDTOList);
        
        return bookingDetailsDTO;
    }

    public BookingDetails toBookingDetails(BookingDetailsDTO bookingDetailsDTO) {
        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setBookingId(bookingDetailsDTO.getBookingId());        
        
        Long customerId = bookingDetailsDTO.getCustomerId();
        Customer customer = this.customerRepository.findById(customerId).get();
        
        Long hotelId = bookingDetailsDTO.getHotelId();
        Hotel hotel = this.hotelRepository.findById(hotelId).get();
        
        bookingDetails.setCustomer(customer);
        bookingDetails.setHotel(hotel);
        
        bookingDetails.setCheckIn(bookingDetailsDTO.getCheckIn());        
        bookingDetails.setCheckOut(bookingDetailsDTO.getCheckOut());
        bookingDetails.setNoOfAdults(bookingDetailsDTO.getNoOfAdults());
        bookingDetails.setNoOfChildren(bookingDetailsDTO.getNoOfChildren());
        bookingDetails.setTotalAmount(bookingDetailsDTO.getTotalAmount());
        
        List<RoomDetails> rdList = new ArrayList<RoomDetails>();
        
        // Mapping listOfRooms
        for(Long roomId : bookingDetailsDTO.getListOfRooms()) {
        	RoomDetails rd = roomRepository.findById(roomId).get();
        	rdList.add(rd);
        }        
        bookingDetails.setListOfRooms(rdList);
        
        return bookingDetails;
    }
}
