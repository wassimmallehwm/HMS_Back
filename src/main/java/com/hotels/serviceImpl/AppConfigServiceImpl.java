package com.hotels.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotels.message.response.ResponseMessage;
import com.hotels.model.AppConfig;
import com.hotels.repository.AppConfigRepository;
import com.hotels.service.AppConfigService;

@Service("AppConfigService")
public class AppConfigServiceImpl implements AppConfigService{
	
	@Autowired
	AppConfigRepository configRepository;

	@Override
	public ResponseEntity<?> get() {
		AppConfig config = configRepository.findAll().get(0);
		return new ResponseEntity<AppConfig>(config, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> update(AppConfig appconfig) {
		AppConfig config = configRepository.findAll().get(0);
		config.setLang(appconfig.getLang());
		config.setTheme(appconfig.getTheme());
		configRepository.save(config);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage("Configuration updated successfully"), HttpStatus.CREATED);
	}

}
