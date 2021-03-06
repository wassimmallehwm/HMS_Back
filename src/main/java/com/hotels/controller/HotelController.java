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

import com.hotels.dto.HotelDto;
import com.hotels.message.response.ResponseMessage;
import com.hotels.service.HotelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseMessage> create(@RequestBody HotelDto hotel){
		try {
			hotelService.create(hotel);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Created with success !") ,HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findall")
	public ResponseEntity<?> findAll(){
		try {
			List<HotelDto> hotels =  hotelService.findAll();
			return new ResponseEntity<List<HotelDto>>(hotels ,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findone/{id}")
	public ResponseEntity<?> findOne(@PathVariable long id){
		try {
			HotelDto hotel =  hotelService.findOne(id);
			return new ResponseEntity<HotelDto>(hotel ,HttpStatus.OK);
		} catch(NullPointerException e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Not found !") ,HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseMessage> update(@RequestBody HotelDto hotel){
		try {
			hotelService.update(hotel);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Updated with success !") ,HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseMessage> delete(@RequestParam long id){
		try {
			hotelService.delete(id);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Deleted with success !") ,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
