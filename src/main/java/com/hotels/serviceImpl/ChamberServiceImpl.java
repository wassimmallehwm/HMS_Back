package com.hotels.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.dto.ChamberDto;
import com.hotels.model.Chamber;
import com.hotels.model.Hotel;
import com.hotels.repository.ChamberRepository;
import com.hotels.repository.ChamberTypeRepository;
import com.hotels.repository.HotelRepository;
import com.hotels.service.ChamberService;

@Service("ChamberService")
public class ChamberServiceImpl implements ChamberService {

	@Autowired
	private ChamberRepository chamberRepository;
	
	@Autowired
	private ChamberTypeRepository chamberTypeRepository;
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public List<ChamberDto> findAll() {
		return mapDtoList(chamberRepository.findAll());
	}

	@Override
	public ChamberDto findOne(long id) {
		return mapChamberToChamberDto(chamberRepository.findById(id).orElse(null));
	}

	@Override
	public Chamber create(ChamberDto chamber) {
		Hotel hotel = hotelRepository.findById(chamber.getHotel()).orElse(null);
		Chamber newChamber = chamberRepository.save(mapChamberDtoToChamber(chamber));
		hotel.getChambers().add(newChamber);
		hotelRepository.save(hotel);
		return newChamber;
	}

	@Override
	public Chamber update(ChamberDto chamber) {
		Chamber newChamber = chamberRepository.findById(chamber.getId()).orElse(null);
		newChamber.setRef(chamber.getRef());
		newChamber.setType(chamberTypeRepository.findOneByName(chamber.getType()));
		newChamber.setHotel(hotelRepository.getOne(chamber.getHotel()));
		return chamberRepository.save(newChamber);
	}

	@Override
	public void delete(long id) {
		chamberRepository.deleteById(id);
	}
	

	
	private List<ChamberDto> mapDtoList(List<Chamber> chambers){
		List<ChamberDto> chambersDto = new ArrayList<ChamberDto>();
		for(Chamber chamber: chambers) {
			chambersDto.add(mapChamberToChamberDto(chamber));
		}
		return chambersDto;
	}
	
	private Chamber mapChamberDtoToChamber(ChamberDto chamber) {
		Chamber newChamber = new Chamber();
		newChamber.setType(chamberTypeRepository.findOneByName(chamber.getType()));
		newChamber.setHotel(hotelRepository.getOne(chamber.getHotel()));
		newChamber.setRef(chamber.getRef());
		return newChamber;
		
	}
	
	private ChamberDto mapChamberToChamberDto(Chamber chamber) {
		ChamberDto newChamber = new ChamberDto();
		newChamber.setId(chamber.getId());
		newChamber.setType(chamber.getType().getName());
		newChamber.setPrice(chamber.getType().getPrice_per_night());
		newChamber.setHotel(chamber.getHotel().getId());
		newChamber.setHotelName(chamber.getHotel().getName());
		newChamber.setRef(chamber.getRef());
		return newChamber;
	}

}
