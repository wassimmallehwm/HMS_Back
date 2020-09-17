package com.hotels.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.model.Hotel;
import com.hotels.repository.HotelRepository;
import com.hotels.service.HotelService;

@Service("HotelService")
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public List<Hotel> findAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel findOne(long id) {
		return hotelRepository.findById(id).orElse(null);
	}

	@Override
	public Hotel create(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel update(Hotel hotel) {
		Hotel newHotel = findOne(hotel.getId());
		newHotel.setName(hotel.getName());
		newHotel.setAdresse(hotel.getAdresse());
		newHotel.setCity(hotel.getCity());
		newHotel.setStars(hotel.getStars());
		newHotel.setTel(hotel.getTel());
		newHotel.setChambers(hotel.getChambers());
		return hotelRepository.save(newHotel);
	}

	@Override
	public void delete(long id) {
		hotelRepository.deleteById(id);
	}

}
