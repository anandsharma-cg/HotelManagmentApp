package com.hotelbookingapp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	@NotNull(message = "Checkin Date Required")
	private LocalDate checkIn;
	
	@NotNull(message = "Checkout Date Required")
	private LocalDate checkOut;
	
	@NotNull(message = "No Of Adults Required")
	private Integer noOfAdults;
	
	private Integer noOfChildren;
	
	@NotNull(message = "Total Amount Required")
	private Double totalAmount;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
	    name = "booking_room",
	    joinColumns = @JoinColumn(name = "booking_id"),
	    inverseJoinColumns = @JoinColumn(name = "room_id")
	)
	private List<RoomDetails> listOfRooms;
	
	public void addRoom(RoomDetails room) {
		if(listOfRooms==null)
			listOfRooms =new ArrayList<>();		
		 listOfRooms.add(room);
	}
}
