package com.hotelbookingapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hotelbookingapp.dto.BookingDetailsDTO;
import com.hotelbookingapp.dto.RoomDetailsDTO;
import com.hotelbookingapp.entity.BookingDetails;
import com.hotelbookingapp.entity.Customer;
import com.hotelbookingapp.entity.Hotel;
import com.hotelbookingapp.entity.RoomDetails;
import com.hotelbookingapp.exception.ResourceNotFoundException;
import com.hotelbookingapp.exception.RoomNotAvailableException;
import com.hotelbookingapp.mapper.BookingMapper;
import com.hotelbookingapp.mapper.CustomerMapper;
import com.hotelbookingapp.mapper.HotelMapper;
import com.hotelbookingapp.mapper.RoomMapper;
import com.hotelbookingapp.repository.BookingDetailsRepository;
import com.hotelbookingapp.repository.CustomerRepository;
import com.hotelbookingapp.repository.HotelRepository;
import com.hotelbookingapp.repository.RoomDetailsRepository;
import com.hotelbookingapp.util.RoomStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
public class BookingDetailsServiceImpl implements IBookingDetailsService{
	private BookingDetailsRepository bookingRepository;
	private RoomDetailsRepository roomRepository;
	private BookingMapper bookingMapper;
//	private IRoomDetailsService roomDetailsService;
	
	public void isRoomAvailable(BookingDetailsDTO bookingDetailsDTO) {
		for(Long inputRoomId : bookingDetailsDTO.getListOfRooms()) {
			boolean outsideDateRange = true;
			
			RoomDetails rd = this.roomRepository.findById(inputRoomId).
			orElseThrow(()-> new ResourceNotFoundException("Room Not Found"));
			
			LocalDate inputcheckIn = bookingDetailsDTO.getCheckIn();						
			List<BookingDetails> bdList = rd.getBookingDetailsList();
			
			for(BookingDetails bd : bdList) {
				LocalDate checkIn = bd.getCheckIn();
				LocalDate checkOut = bd.getCheckOut();
				
				if(inputcheckIn.isAfter(checkIn) && inputcheckIn.isBefore(checkOut))
					throw new RoomNotAvailableException("Room Not Available in given date range : " + rd.getRoomNo());
			}
			
			if(!outsideDateRange && rd.getStatus().compareTo(RoomStatus.BOOKED) == 0)
				throw new RoomNotAvailableException("Room Not Available : " + rd.getRoomNo());
			
			rd.setStatus(RoomStatus.BOOKED);
			this.roomRepository.save(rd);			
		}	
	}	
	
	public void updateRoomStatus(BookingDetailsDTO bookingDetailsDTO,RoomStatus rs) {
		for(Long inputRoomId : bookingDetailsDTO.getListOfRooms()) {
			RoomDetails rd = this.roomRepository.findById(inputRoomId).
			orElseThrow(()-> new ResourceNotFoundException("Room Not Found"));	
			
			rd.setStatus(rs);
			this.roomRepository.save(rd);			
		}	
	}
	
	@Override
	public BookingDetailsDTO addBookingDetails(BookingDetailsDTO bookingDetailsDTO) {		
		// TODO Auto-generated method stub		
		
		this.isRoomAvailable(bookingDetailsDTO);
//		this.roomDetailsService.findAvailableRoomsByDate(bookingDetailsDTO.getCheckIn(), bookingDetailsDTO.getCheckOut());
		this.updateRoomStatus(bookingDetailsDTO,RoomStatus.BOOKED);
		
		BookingDetails bookDetails = this.bookingMapper.toBookingDetails(bookingDetailsDTO);		
		
		return this.bookingMapper.toBookingDetailsDTO(this.bookingRepository.save(bookDetails));
	}
	
	@Override
	public BookingDetailsDTO updateBookingDetails(BookingDetailsDTO bookingDetailsDTO) {
		// TODO Auto-generated method stub
		BookingDetails bd = this.bookingRepository.
				findById(bookingDetailsDTO.getBookingId()).
				orElseThrow(()-> new ResourceNotFoundException("Booking Not Found"));
		
		bd.setCheckIn(bookingDetailsDTO.getCheckIn());
		bd.setCheckOut(bookingDetailsDTO.getCheckOut());
		bd.setNoOfAdults(bookingDetailsDTO.getNoOfAdults());
		bd.setNoOfChildren(bookingDetailsDTO.getNoOfChildren());
		bd.setTotalAmount(bookingDetailsDTO.getTotalAmount());				
		
		List<RoomDetails> rdList = new ArrayList<RoomDetails>();        
        // Mapping listOfRooms
        for(Long roomId : bookingDetailsDTO.getListOfRooms()) {
        	RoomDetails rd = this.roomRepository.findById(roomId).
        			orElseThrow(()-> new ResourceNotFoundException("Booking Not Found"));
        	rdList.add(rd);
        }        
        bd.setListOfRooms(rdList);
		
		return this.bookingMapper.toBookingDetailsDTO(this.bookingRepository.save(bd));		
	}
	
	@Override
	public BookingDetailsDTO cancelBooking(Long bookingId) {
		// TODO Auto-generated method stub
		BookingDetails bd = this.bookingRepository.findById(bookingId).
				orElseThrow(()-> new ResourceNotFoundException("Booking Not Found"));
		BookingDetailsDTO bdDTO = this.bookingMapper.toBookingDetailsDTO(bd);
		
		this.updateRoomStatus(bdDTO, RoomStatus.AVAILABLE);
		this.bookingRepository.deleteById(bookingId);
		
		return bdDTO;
	}
	
	@Override
	public List<BookingDetailsDTO> findAllBookings() {
		// TODO Auto-generated method stub
		List<BookingDetails> bdList = this.bookingRepository.findAll();
		List<BookingDetailsDTO> bdDTOList = new ArrayList<BookingDetailsDTO>();
		
		bdDTOList = bdList.stream().
		map(this.bookingMapper::toBookingDetailsDTO).toList();
		
		return bdDTOList;
	}
	
	@Override
	public BookingDetailsDTO findByBookingId(Long bookingId) {
		// TODO Auto-generated method stub
		BookingDetails bd = this.bookingRepository.findById(bookingId).
				orElseThrow(()-> new ResourceNotFoundException("Booking Not Found"));
		BookingDetailsDTO bdDTO = this.bookingMapper.toBookingDetailsDTO(bd);
		
		return bdDTO;
	}
	
	@Override
	public List<BookingDetailsDTO> findByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		List<BookingDetails> bdList = this.bookingRepository.findByCustomer_CustomerId(customerId);
		List<BookingDetailsDTO> bdDTOList = new ArrayList<BookingDetailsDTO>();
		
		bdDTOList = bdList.stream().
				map(this.bookingMapper::toBookingDetailsDTO).toList();
				
		return bdDTOList;		
	}
	
	@Override
	public List<BookingDetailsDTO> findByHotelId(Long hotelId) {
		// TODO Auto-generated method stub
		List<BookingDetails> bdList = this.bookingRepository.findByHotel_HotelId(hotelId);
		List<BookingDetailsDTO> bdDTOList = new ArrayList<BookingDetailsDTO>();
		
		bdDTOList = bdList.stream().
				map(this.bookingMapper::toBookingDetailsDTO).toList();
				
		return bdDTOList;
	}
	
	@Override
	public List<BookingDetailsDTO> findByCustomerIdAndCheckOutDate(Long customerId, LocalDate checkOutDate) {
		// TODO Auto-generated method stub	
		List<BookingDetails> bdList = this.bookingRepository.findByCustomer_CustomerIdAndCheckOut(customerId, checkOutDate);
		List<BookingDetailsDTO> bdDTOList = new ArrayList<BookingDetailsDTO>();
		
		bdDTOList = bdList.stream().
				map(this.bookingMapper::toBookingDetailsDTO).toList();
		
		return bdDTOList;		
	}
}
