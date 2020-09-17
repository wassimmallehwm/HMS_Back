package com.hotels.service;

import java.util.List;

import com.hotels.model.Reservation;

public interface ReservationService {

	List<Reservation> findAll();

	Reservation findOne(long id);
	
	Reservation create(Reservation reservation);
	
	Reservation update(Reservation reservation);
	
	void delete(long id);

}
