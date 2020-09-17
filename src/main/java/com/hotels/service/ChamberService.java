package com.hotels.service;

import java.util.List;

import com.hotels.model.Chamber;

public interface ChamberService {

	List<Chamber> findAll();

	Chamber findOne(long id);
	
	Chamber create(Chamber chamber);
	
	Chamber update(Chamber chamber);
	
	void delete(long id);

}
