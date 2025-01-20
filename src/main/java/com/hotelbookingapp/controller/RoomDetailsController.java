package com.hotelbookingapp.controller;

import com.hotelbookingapp.dto.RoomDetailsDTO;
import com.hotelbookingapp.service.IRoomDetailsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomDetailsController{

    private final IRoomDetailsService roomDetailsService;

    @PostMapping("/add")
    public ResponseEntity<RoomDetailsDTO> addRoomDetails(@Valid @RequestBody RoomDetailsDTO roomDetails) {
        RoomDetailsDTO addedRoom = roomDetailsService.addRoomDetails(roomDetails);
        return new ResponseEntity<>(addedRoom, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<RoomDetailsDTO> updateRoomDetails(@Valid @RequestBody RoomDetailsDTO roomDetails) {
        RoomDetailsDTO updatedRoom = roomDetailsService.updateRoomDetails(roomDetails);
        return new ResponseEntity<>(updatedRoom,HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDetailsDTO> findRoomById(@PathVariable Long roomId) {
        RoomDetailsDTO room = roomDetailsService.findByRoomId(roomId);
        return new ResponseEntity<>(room,HttpStatus.OK);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomDetailsDTO>> findRoomsByHotelId(@PathVariable Long hotelId) {
        List<RoomDetailsDTO> rooms = roomDetailsService.findByHotelId(hotelId);
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

    @GetMapping("/hotel/{hotelName}")
    public ResponseEntity<List<RoomDetailsDTO>> findRoomsByHotelName(@PathVariable String hotelName) {
        List<RoomDetailsDTO> rooms = roomDetailsService.findByHotelName(hotelName);
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<RoomDetailsDTO>> findAvailableRoomsByDate(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        List<RoomDetailsDTO> availableRooms = roomDetailsService.findAvailableRoomsByDate(from, to);
        return new ResponseEntity<>(availableRooms,HttpStatus.OK);
    }

    @GetMapping("/hotel/{hotelId}/status/{status}")
    public ResponseEntity<List<RoomDetailsDTO>> findRoomsByHotelAndStatus(@PathVariable Long hotelId, @PathVariable String status) {
        List<RoomDetailsDTO> rooms = roomDetailsService.findRoomsByHotelAndStatus(hotelId, status);
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

    @DeleteMapping("/removeFromHotel/{hotelId}")
    public ResponseEntity<List<RoomDetailsDTO>> removeRoomsFromHotel(@PathVariable Long hotelId, @RequestBody List<RoomDetailsDTO> roomsToRemove) {
        List<RoomDetailsDTO> remainingRooms = roomDetailsService.removeRoomsFromHotel(hotelId, roomsToRemove);
        return new ResponseEntity<>(remainingRooms,HttpStatus.OK);
    }

    @DeleteMapping("/removeFromBooking/{bookingId}")
    public ResponseEntity<List<RoomDetailsDTO>> removeRoomsFromBooking(@PathVariable Long bookingId, @RequestBody List<RoomDetailsDTO> roomsToRemove) {
        List<RoomDetailsDTO> remainingRooms = roomDetailsService.removeRoomsFromBooking(bookingId, roomsToRemove);
        return new ResponseEntity<>(remainingRooms,HttpStatus.OK);
    }
}
