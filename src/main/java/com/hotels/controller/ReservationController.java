package com.hotels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotels.dto.ReservationDto;
import com.hotels.message.response.ResponseMessage;
import com.hotels.service.ReservationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/findall")
	public ResponseEntity<?> findAll(){
		try {
			List<ReservationDto> reservations =  reservationService.findAll();
			return new ResponseEntity<List<ReservationDto>>(reservations ,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findone/{id}")
	public ResponseEntity<?> findOne(@PathVariable long id){
		try {
			ReservationDto reservation = reservationService.findOne(id);
			return new ResponseEntity<ReservationDto>(reservation ,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/create/{clientId}/{hotelId}")
	public ResponseEntity<ResponseMessage> create(@RequestBody ReservationDto reservation, @PathVariable long clientId, @PathVariable long hotelId){
		try {
			reservationService.create(reservation, clientId, hotelId);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Created with success !") ,HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseMessage> update(@RequestBody ReservationDto reservation){
		try {
			reservationService.update(reservation);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Updated with success !") ,HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseMessage> delete(@RequestParam long id){
		try {
			reservationService.delete(id);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Deleted with success !") ,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
