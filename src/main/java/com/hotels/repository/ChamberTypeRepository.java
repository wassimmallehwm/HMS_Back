package com.hotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotels.model.ChamberType;

public interface ChamberTypeRepository extends JpaRepository<ChamberType, Long> {
	
	ChamberType findOneByName(String name);

}
