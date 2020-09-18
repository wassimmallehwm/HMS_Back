package com.hotels.service;

import java.util.List;

import com.hotels.dto.HotelDto;
import com.hotels.model.Hotel;

public interface HotelService {

	List<HotelDto> findAll();

	HotelDto findOne(long id);
	
	Hotel create(HotelDto hotel);
	
	Hotel update(HotelDto hotel);
	
	void delete(long id);
}
