package com.hotelbookingapp.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDetails {
	LocalDateTime date;
	String message;
	String path;
}