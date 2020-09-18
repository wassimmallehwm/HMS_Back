package com.hotels.service;

import java.util.List;

import com.hotels.dto.ReservationDto;
import com.hotels.model.Reservation;

public interface ReservationService {

	List<ReservationDto> findAll();

	ReservationDto findOne(long id);
	
	Reservation create(ReservationDto reservation, long clientId);
	
	Reservation update(ReservationDto reservation);
	
	void delete(long id);

}
