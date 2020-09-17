package com.hotels.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.model.Reservation;
import com.hotels.repository.ReservationRepository;
import com.hotels.service.ReservationService;

@Service("ReservationService")
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation findOne(long id) {
		return reservationRepository.findById(id).orElse(null);
	}

	@Override
	public Reservation create(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation update(Reservation reservation) {
		Reservation reserv = findOne(reservation.getId());
		reserv.setChamber(reservation.getChamber());
		reserv.setClient(reservation.getClient());
		reserv.setCreatedOn(reservation.getCreatedOn());
		reserv.setDuration(reservation.getDuration());
		reserv.setNbAdultes(reservation.getNbAdultes());
		reserv.setNbKids(reservation.getNbKids());
		reserv.setPrice(reservation.getPrice());
		reserv.setState(reservation.getState());
		return reservationRepository.save(reserv);
	}

	@Override
	public void delete(long id) {
		reservationRepository.deleteById(id);
	}

}
