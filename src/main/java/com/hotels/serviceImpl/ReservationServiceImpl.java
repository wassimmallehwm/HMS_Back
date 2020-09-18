package com.hotels.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.dto.ReservationDto;
import com.hotels.model.Chamber;
import com.hotels.model.Client;
import com.hotels.model.Reservation;
import com.hotels.repository.ChamberRepository;
import com.hotels.repository.ClientRepository;
import com.hotels.repository.ReservationRepository;
import com.hotels.service.ReservationService;

@Service("ReservationService")
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ChamberRepository chamberRepository;

	@Override
	public List<ReservationDto> findAll() {
		return mapDtoList(reservationRepository.findAll());
	}

	@Override
	public ReservationDto findOne(long id) {
		return mapEntityToDto(reservationRepository.findById(id).orElse(null));
	}

	@Override
	public Reservation create(ReservationDto reservation, long clientId) {
		Client client = clientRepository.findById(clientId).orElse(null);
		Reservation reserv = mapDtoToEntity(reservation);
		reserv.setClient(client);
		reserv.setCreatedOn(new Date());
		return reservationRepository.save(reserv);
	}

	@Override
	public Reservation update(ReservationDto reservation) {
		Reservation reserv = reservationRepository.findById(reservation.getId()).orElse(null);
		reserv.setState(reservation.getState());
		return reservationRepository.save(reserv);
	}

	@Override
	public void delete(long id) {
		reservationRepository.deleteById(id);
	}
	
	
	private List<ReservationDto> mapDtoList(List<Reservation> reservations){
		List<ReservationDto> reservationsDto = new ArrayList<ReservationDto>();
		
		for(Reservation reservation : reservations) {
			reservationsDto.add(mapEntityToDto(reservation));
		}
		
		return reservationsDto;
	}
 
	
	private ReservationDto mapEntityToDto(Reservation reservation) {
		ReservationDto dto = new ReservationDto();
		dto.setId(reservation.getId());
		dto.setChamber(reservation.getChamber().getId());
		dto.setClient(reservation.getClient().getId());
		dto.setDuration(reservation.getDuration());
		dto.setNbAdultes(reservation.getNbAdultes());
		dto.setNbKids(reservation.getNbKids());
		dto.setPrice(reservation.getPrice());
		dto.setState(reservation.getState());
		return dto;
	}
	
	private Reservation mapDtoToEntity(ReservationDto reservation) {
		Reservation dto = new Reservation();
		Chamber ch = chamberRepository.findById(reservation.getChamber()).orElse(null);
		dto.setChamber(ch);
		Client cl = clientRepository.findById(reservation.getChamber()).orElse(null);
		dto.setClient(cl);
		dto.setDuration(reservation.getDuration());
		dto.setNbAdultes(reservation.getNbAdultes());
		dto.setNbKids(reservation.getNbKids());
		dto.setPrice(reservation.getPrice());
		dto.setState(reservation.getState());
		return dto;
	}

}
