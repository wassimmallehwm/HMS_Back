package com.hotels.service;

import java.util.List;

import com.hotels.dto.ChamberDto;
import com.hotels.model.Chamber;

public interface ChamberService {

	List<ChamberDto> findAll();

	ChamberDto findOne(long id);
	
	Chamber create(ChamberDto chamber);
	
	Chamber update(ChamberDto chamber);
	
	void delete(long id);

}
