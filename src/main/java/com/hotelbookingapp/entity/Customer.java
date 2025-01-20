package com.hotelbookingapp.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = "customerEmail"))
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid Name Format")
	@NotBlank(message = "Name cannot be null or empty")
	private String customerName;
	
	@NotNull(message = "Mobile cannot be null")
	@Column(unique = true)
	private Long customerMobile;
	
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email format")
	@Column(unique = true)
	@NotBlank(message = "Email cannot be null or empty")
	private String customerEmail;
	
	@NotBlank(message = "Invalid Address")
	@NotNull(message = "Address cannot be null")
	private String customerAddress;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<BookingDetails> bookings;

}
