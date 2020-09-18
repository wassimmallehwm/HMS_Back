package com.hotels.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hotels.model.AppConfig;
import com.hotels.model.Chamber;
import com.hotels.model.ChamberType;
import com.hotels.model.Hotel;
import com.hotels.model.Role;
import com.hotels.model.User;
import com.hotels.repository.AppConfigRepository;
import com.hotels.repository.ChamberRepository;
import com.hotels.repository.ChamberTypeRepository;
import com.hotels.repository.HotelRepository;
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
	private ChamberTypeRepository chamberTypeRepository;
	
	@Autowired
	private ChamberRepository chamberRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private ExternalConfig externalConfig;

	@Autowired
	PasswordEncoder encoder;


	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		
		if(!externalConfig.isDatabaseInitialized()) {
			initAppConfig();
			initAdmin();
			initHotels();
			System.out.println("Intializing data ...");
			externalConfig.setIsDatabaseInitialized(true);
		} else {
			System.out.println("Data already intialized !");
		}
		
	}
	
	private void initAppConfig() {
		AppConfig conf = new AppConfig();
		conf.setLang("en");
		conf.setTheme("0");
		configRepository.save(conf);
	}
	
	
	private void initHotels() {
		ChamberType chamberType1 = chamberTypeRepository.save(new ChamberType("Single", 80.0));
		ChamberType chamberType2 = chamberTypeRepository.save(new ChamberType("Double", 110.0));
		ChamberType chamberType3 = chamberTypeRepository.save(new ChamberType("Triple", 130.0));
		ChamberType chamberType4 = chamberTypeRepository.save(new ChamberType("Suite", 190.0));
		
		Set<Chamber> chambers = new HashSet<Chamber>();
		
		Hotel hotel1 = new Hotel("Hotel 1", "adresse 1", 3, "Tunis", "70 360 456", chambers);
		addHotel(chambers, hotel1, chamberType1, chamberType2, chamberType3, chamberType4);
		Hotel hotel2 = new Hotel("Hotel 2", "adresse 2", 5, "Hammamet", "70 547 362", chambers);
		addHotel(chambers, hotel2, chamberType1, chamberType2, chamberType3, chamberType4);
		Hotel hotel3 = new Hotel("Hotel 3", "adresse 3", 4, "Sousse", "70 123 714", chambers);
		addHotel(chambers, hotel3, chamberType1, chamberType2, chamberType3, chamberType4);
	}
	
	private void addHotel(Set<Chamber>chambers, Hotel hotel1, ChamberType chamberType1, ChamberType chamberType2, ChamberType chamberType3, ChamberType chamberType4) {
		hotel1 = hotelRepository.save(hotel1);
		Chamber ch1 = new Chamber("101", chamberType1, hotel1);
		Chamber ch2 = new Chamber("102", chamberType2, hotel1);
		Chamber ch3 = new Chamber("103", chamberType3, hotel1);
		Chamber ch4 = new Chamber("104", chamberType4, hotel1);
		ch1 = chamberRepository.save(ch1);
		ch2 = chamberRepository.save(ch2);
		ch3 = chamberRepository.save(ch3);
		ch4 = chamberRepository.save(ch4);
		chambers.add(ch1);
		chambers.add(ch2);
		chambers.add(ch3);
		chambers.add(ch4);
		hotel1.setChambers(chambers);
		hotelRepository.save(hotel1);
		
	}
	
	
	private void initAdmin() {
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
	}
}
