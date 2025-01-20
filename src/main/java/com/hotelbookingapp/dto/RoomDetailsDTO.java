package com.hotelbookingapp.dto;

import com.hotelbookingapp.util.RoomStatus;
import com.hotelbookingapp.util.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RoomDetailsDTO {
	private Long roomId;
	private String roomNo;
	private RoomType roomType;
	private Integer noOfPersons;
	private Double ratePerDay;
	private RoomStatus status;
	private Long hotelId;
}
