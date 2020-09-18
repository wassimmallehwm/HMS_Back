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

import com.hotels.message.response.ResponseMessage;
import com.hotels.model.Client;
import com.hotels.service.ClientService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clients")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/findall")
	public ResponseEntity<?> findAll(){
		try {
			List<Client> clients =  clientService.findAll();
			return new ResponseEntity<List<Client>>(clients ,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findone/{id}")
	public ResponseEntity<?> findOne(@PathVariable long id){
		try {
			Client client =  clientService.findOne(id);
			return new ResponseEntity<Client>(client ,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody Client client){
		try {
			Client cl = clientService.create(client);
			return new ResponseEntity<Long>(cl.getId() ,HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Long>(0l ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseMessage> update(@RequestBody Client client){
		try {
			clientService.update(client);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Updated with success !") ,HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseMessage> delete(@RequestParam long id){
		try {
			clientService.delete(id);
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Deleted with success !") ,HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Error !") ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
