package com.hotels.service;

import java.util.List;

import com.hotels.model.Hotel;

public interface HotelService {

	List<Hotel> findAll();

	Hotel findOne(long id);
	
	Hotel create(Hotel hotel);
	
	Hotel update(Hotel hotel);
	
	void delete(long id);
}
