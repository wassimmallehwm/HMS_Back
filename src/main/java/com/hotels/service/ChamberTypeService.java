package com.hotels.service;

import java.util.List;

import com.hotels.model.ChamberType;

public interface ChamberTypeService {

	List<ChamberType> findAll();

	ChamberType findOne(long id);
	
	ChamberType create(ChamberType chamber);
	
	ChamberType update(ChamberType chamber);
	
	void delete(long id);

}
