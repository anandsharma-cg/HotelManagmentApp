package com.hotelbookingapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbookingapp.entity.BookingDetails;
import com.hotelbookingapp.entity.Customer;
import com.hotelbookingapp.entity.Hotel;
import com.hotelbookingapp.entity.RoomDetails;
import com.hotelbookingapp.repository.BookingDetailsRepository;
import com.hotelbookingapp.repository.CustomerRepository;
import com.hotelbookingapp.repository.HotelRepository;
import com.hotelbookingapp.repository.RoomDetailsRepository;
import com.hotelbookingapp.util.RoomStatus;
import com.hotelbookingapp.util.RoomType;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomDetailsRepository roomDetailsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @PostMapping
    public String addTestData() {
        // Add data toh Hotel table
        Hotel hotel = new Hotel();
        hotel.setHotelName("Example Hotel");
        hotel.setHotelCity("City");
        hotel.setAddress("123 Example St");
        hotel.setDescription("Description of Example Hotel");
        hotel.setHotelEmail("hotel@example.com");
        hotel.setHotelContactNo("123-456-7890");
        hotel.setHotelWebsite("http://www.examplehotel.com");

       

        // Add data to RoomDetails table
        RoomDetails room = new RoomDetails();
        room.setHotel(hotel);
        room.setRoomNo("101");
        room.setRoomType(RoomType.SINGLE);
        room.setNoOfPersons(1);
        room.setRatePerDay(100.0);
        room.setStatus(RoomStatus.AVAILABLE);
        
        hotel.addRoom(room);
        hotel = hotelRepository.save(hotel);
        // Save room
        room = roomDetailsRepository.save(room);
        
  

        // Add data to Customer table
        Customer customer = new Customer();
        customer.setCustomerName("John Doe");
        customer.setCustomerMobile(1234567890L);
        customer.setCustomerEmail("john@example.com");
        customer.setCustomerAddress("456 Customer St");

        // Save customer
        customer = customerRepository.save(customer);

        // Add data to BookingDetails table
//        BookingDetails booking = new BookingDetails();
//        booking.setHotel(hotel);
//        booking.setCustomer(customer);
//        booking.setCheckIn(LocalDate.now());
//        booking.setCheckOut(LocalDate.now().plusDays(3));
//        booking.setNoOfAdults(2);
//        booking.setNoOfChildren(0);
//        booking.setTotalAmount(300.0);
//
//        // Add room to booking
//        booking.addRoom(hotel.getListOfRooms().get(0));
//
//        // Save booking
//        bookingDetailsRepository.save(booking);

        return "Data added successfully";
    }
    
    @PostMapping("{hotelId}/{roomId}")
    public String addRoomByHotelId(@PathVariable Long hotelId,@PathVariable String roomId) {
    	Hotel hotel = this.hotelRepository.findById(hotelId).get();
    	
    	// Add data to RoomDetails table
        RoomDetails room = new RoomDetails();
        room.setHotel(hotel);
        room.setRoomNo(roomId);
        room.setRoomType(RoomType.SINGLE);
        room.setNoOfPersons(1);
        room.setRatePerDay(100.0);
        room.setStatus(RoomStatus.AVAILABLE);
        
        hotel.addRoom(room);
        hotel = hotelRepository.save(hotel);
        // Save room
//        room = roomDetailsRepository.save(room);
    	
    	return "Room added successfully in hotel-id : "+hotelId;
    }
}

