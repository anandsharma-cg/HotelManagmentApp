/**
 * 
 */
package com.hotelbookingapp.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hotelbookingapp.entity.BookingDetails;

/**
 * @author sudmaity
 *
 */
public interface BookingDetailsRepository extends JpaRepository<BookingDetails,Long>{
	List<BookingDetails> findByCustomer_CustomerId(Long customerId);
	List<BookingDetails> findByHotel_HotelId(Long hotelId);
	List<BookingDetails> findByCustomer_CustomerIdAndCheckOut(Long customerId, LocalDate checkOutDate);
}
