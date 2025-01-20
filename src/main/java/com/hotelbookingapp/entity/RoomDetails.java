package com.hotelbookingapp.entity;
 
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hotelbookingapp.util.RoomStatus;
import com.hotelbookingapp.util.RoomType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class RoomDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	@JsonBackReference
	private Hotel hotel;
	
	@Column
    @NotBlank(message = "Room number is required")
    private String roomNo;
    
	@NotNull(message = "Room type is required")
    private RoomType roomType;
    
	@NotNull(message = "Number of persons is required")
    private Integer noOfPersons;
    
	@NotNull(message = "Rate per day is required")
    private Double ratePerDay;
    
	@NotNull(message = "Room status is required")
    private RoomStatus status;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
	    name = "booking_room",
	    joinColumns = @JoinColumn(name = "room_id"),
	    inverseJoinColumns = @JoinColumn(name = "booking_id")
	)
	private List<BookingDetails> bookingDetailsList;
	
	public void addBooking(BookingDetails booking) {
		if(bookingDetailsList==null) {
			bookingDetailsList =new ArrayList<>();	
		}
		bookingDetailsList.add(booking);
	}
}
