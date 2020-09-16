package com.hotels.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hotels.model.AppConfig;
import com.hotels.model.Role;
import com.hotels.model.User;
import com.hotels.repository.AppConfigRepository;
import com.hotels.repository.RoleRepository;
import com.hotels.repository.UserRepository;

/**
 * Main events handler for the application
 * @author sem
 */
@Component
public class ApplicationEventListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AppConfigRepository configRepository;
	
	@Autowired
	private ExternalConfig externalConfig;

	@Autowired
	PasswordEncoder encoder;


	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		
		if(!externalConfig.isDatabaseInitialized()) {
			AppConfig conf = new AppConfig();
			conf.setLang("en");
			conf.setTheme("grey");
			configRepository.save(conf);
			
			Role role = new Role();
			role.setName("ROLE_ADMIN");
			role = roleRepository.save(role);
			
			User user = new User();
			user.setName("admin");
			user.setEmail("admin@gmail.com");
			user.setUsername("admin");
			user.setPassword(encoder.encode("password"));
			Set<Role> roles= new HashSet<Role>();
			roles.add(role);
			user.setRoles(roles);
			
			userRepository.save(user);
			System.out.println("Intializing data ...");
		} else {
			System.out.println("Data already intialized !");
		}
		
	}
}
