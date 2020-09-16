package com.hotels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotels.model.AppConfig;
import com.hotels.service.AppConfigService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/config")
public class ConfigController {
	
	@Autowired
	AppConfigService configService;
	
	@GetMapping("/get")
	public ResponseEntity<?> get(){
		return configService.get();
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody AppConfig config){
		return configService.update(config);
	}

}
