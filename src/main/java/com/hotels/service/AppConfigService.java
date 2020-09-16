package com.hotels.service;

import org.springframework.http.ResponseEntity;

import com.hotels.model.AppConfig;

public interface AppConfigService {
	
	ResponseEntity<?> get();
	
	ResponseEntity<?> update(AppConfig appconfig);

}
