package com.hotelbookingapp.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import jakarta.validation.ConstraintViolationException;
 
@RestControllerAdvice
public class GlobalExceptionHandler  {
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<Object>("Message: " + errors, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails>resourceNotFoundException(ResourceNotFoundException ex,WebRequest r){
		ErrorDetails details =new ErrorDetails();
		details.setDate(LocalDateTime.now());
		details.setMessage(ex.getMessage());
		details.setPath(r.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RoomNotAvailableException.class)
	public ResponseEntity<ErrorDetails>roomNotAvailableException(RoomNotAvailableException ex,WebRequest r){
		ErrorDetails details =new ErrorDetails();
		details.setDate(LocalDateTime.now());
		details.setMessage(ex.getMessage());
		details.setPath(r.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details,HttpStatus.NOT_FOUND);
	}
}
