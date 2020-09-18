package com.hotels.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.dto.ChamberReservationDto;
import com.hotels.dto.ClientDto;
import com.hotels.dto.ReservationDto;
import com.hotels.model.Chamber;
import com.hotels.model.Client;
import com.hotels.model.Hotel;
import com.hotels.model.Reservation;
import com.hotels.repository.ChamberRepository;
import com.hotels.repository.ClientRepository;
import com.hotels.repository.HotelRepository;
import com.hotels.repository.ReservationRepository;
import com.hotels.service.ReservationService;

@Service("ReservationService")
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
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
	public Reservation create(ReservationDto reservation, long clientId, long hotelId) {
		Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
		Client client = clientRepository.findById(clientId).orElse(null);
		Chamber chamber = chamberRepository.findFirstByChamberTypeAndHotel(reservation.getChamber(), hotel);
		Reservation reserv = mapDtoToEntity(reservation);
		reserv.setChamber(chamber);
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
		dto.setClient(new ClientDto(reservation.getClient().getId(), reservation.getClient().getFirstname() + " " + reservation.getClient().getLastname()));
		dto.setDuration(reservation.getDuration());
		dto.setNbAdultes(reservation.getNbAdultes());
		dto.setNbKids(reservation.getNbKids());
		dto.setPrice(reservation.getPrice());
		dto.setState(reservation.getState());
		return dto;
	}
	
	private Reservation mapDtoToEntity(ReservationDto reservation) {
		Reservation dto = new Reservation();
		dto.setDuration(reservation.getDuration());
		dto.setNbAdultes(reservation.getNbAdultes());
		dto.setNbKids(reservation.getNbKids());
		dto.setPrice(reservation.getPrice());
		dto.setState(reservation.getState());
		return dto;
	}

}
