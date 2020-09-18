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

import com.hotels.dto.ChamberDto;
import com.hotels.message.response.ResponseMessage;
import com.hotels.model.ChamberType;
import com.hotels.service.ChamberService;
import com.hotels.service.ChamberTypeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/chambers")
public class ChamberController {
	
	@Autowired
	private ChamberService chamberService;
	
	@Autowired
	private ChamberTypeService chamberTypeService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseMessage> create(@RequestBody ChamberDto chamber){
		try {
			chamberService.create(chamber);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Created with success !") ,HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findall")
	public ResponseEntity<?> findAll(){
		try {
			List<ChamberDto> hotels =  chamberService.findAll();
			return new ResponseEntity<List<ChamberDto>>(hotels ,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findall-types")
	public ResponseEntity<?> findAllTypes(){
		try {
			List<ChamberType> hotels =  chamberTypeService.findAll();
			return new ResponseEntity<List<ChamberType>>(hotels ,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findone/{id}")
	public ResponseEntity<?> findOne(@PathVariable long id){
		try {
			ChamberDto chamber =  chamberService.findOne(id);
			return new ResponseEntity<ChamberDto>(chamber ,HttpStatus.OK);
		} catch(NullPointerException e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Not found !") ,HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseMessage> update(@RequestBody ChamberDto chamber){
		try {
			chamberService.update(chamber);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Updated with success !") ,HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseMessage> delete(@RequestParam long id){
		try {
			chamberService.delete(id);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Deleted with success !") ,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
