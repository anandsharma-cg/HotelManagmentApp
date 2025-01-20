package com.hotelbookingapp.entity;
 
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotelId;
	
	@NotBlank(message = "Hotel name is required")
    private String hotelName;
	
    @NotBlank(message = "City is required")
    private String hotelCity;
    
    @NotBlank(message = "Address is required")
    private String address;
    
    private String description;
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    
    private String hotelEmail;
    @Pattern(regexp = "\\+\\d{10}", message = "Invalid phone number. Must be 10 digits")
    @NotBlank(message = "Contact number is required")
    
    private String hotelContactNo;
    
    private String hotelWebsite;
    
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL,
				fetch = FetchType.EAGER)	
	private List<RoomDetails> listOfRooms;
	
	public void addRoom(RoomDetails room) {
		if(listOfRooms==null) {
			listOfRooms =new ArrayList<>();	
		}
		 listOfRooms.add(room);
	}
}
