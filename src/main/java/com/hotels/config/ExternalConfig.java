package com.hotels.config;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ExternalConfig {

	private PropertiesConfiguration configuration;

	public ExternalConfig() {
	}

	@PostConstruct
	private void init() {
		ClassPathResource resource = new ClassPathResource("application.properties");
		try {
			configuration = new PropertiesConfiguration(resource.getFile());
		} catch (ConfigurationException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isDatabaseInitialized() {
		return configuration.getBoolean("is_initialized");
	}


	public void setIsDatabaseInitialized(boolean isInitialized) {
		configuration.setProperty("is_initialized", isInitialized);
		try {
			configuration.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}


}
