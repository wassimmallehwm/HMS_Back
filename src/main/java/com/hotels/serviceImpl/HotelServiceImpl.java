package com.hotels.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.dto.HotelDto;
import com.hotels.model.Hotel;
import com.hotels.repository.HotelRepository;
import com.hotels.service.HotelService;

@Service("HotelService")
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public List<HotelDto> findAll() {
		return mapDtoList(hotelRepository.findAll());
	}

	@Override
	public HotelDto findOne(long id) {
		Hotel hotel =  hotelRepository.findById(id).orElse(null);
		return mapHotelToHotelDto(hotel);
	}

	@Override
	public Hotel create(HotelDto hotel) {
		return hotelRepository.save(mapHotelDtoToHotel(hotel));
	}

	@Override
	public Hotel update(HotelDto hotel) {
		Hotel newHotel = hotelRepository.findById(hotel.getId()).orElse(null);
		newHotel.setName(hotel.getName());
		newHotel.setAdresse(hotel.getAdresse());
		newHotel.setCity(hotel.getCity());
		newHotel.setStars(hotel.getStars());
		newHotel.setTel(hotel.getTel());
		
		return hotelRepository.save(newHotel);
	}

	@Override
	public void delete(long id) {
		hotelRepository.deleteById(id);
	}
	
	private List<HotelDto> mapDtoList(List<Hotel> hotels){
		List<HotelDto> hotelsDto = new ArrayList();
		for(Hotel hotel: hotels) {
			hotelsDto.add(mapHotelToHotelDto(hotel));
		}
		return hotelsDto;
	}
	
	private Hotel mapHotelDtoToHotel(HotelDto hotel) {
		Hotel newHotel = new Hotel();
		newHotel.setName(hotel.getName());
		newHotel.setAdresse(hotel.getAdresse());
		newHotel.setCity(hotel.getCity());
		newHotel.setStars(hotel.getStars());
		newHotel.setTel(hotel.getTel());
		return newHotel;
	}
	
	private HotelDto mapHotelToHotelDto(Hotel hotel) {
		HotelDto newHotel = new HotelDto();
		newHotel.setId(hotel.getId());
		newHotel.setName(hotel.getName());
		newHotel.setAdresse(hotel.getAdresse());
		newHotel.setCity(hotel.getCity());
		newHotel.setStars(hotel.getStars());
		newHotel.setTel(hotel.getTel());
		return newHotel;
	}

}
