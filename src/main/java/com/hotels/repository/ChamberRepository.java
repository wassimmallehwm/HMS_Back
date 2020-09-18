package com.hotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotels.model.Chamber;
import com.hotels.model.ChamberType;
import com.hotels.model.Hotel;

public interface ChamberRepository extends JpaRepository<Chamber, Long> {
	
	Chamber findFirstByChamberTypeAndHotel(ChamberType type, Hotel hotel);
	
	Chamber findFirstByChamberType(ChamberType type);

}
