package com.hotels.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.model.ChamberType;
import com.hotels.repository.ChamberTypeRepository;
import com.hotels.service.ChamberTypeService;

@Service("ChamberTypeService")
public class ChamberTypeServiceImpl implements ChamberTypeService {
	
	@Autowired
	private ChamberTypeRepository chamberTypeRepository;

	@Override
	public List<ChamberType> findAll() {
		return chamberTypeRepository.findAll();
	}

	@Override
	public ChamberType findOne(long id) {
		return chamberTypeRepository.findById(id).orElse(null);
	}

	@Override
	public ChamberType create(ChamberType chamber) {
		return chamberTypeRepository.save(chamber);
	}

	@Override
	public ChamberType update(ChamberType chamber) {
		ChamberType chamberType = findOne(chamber.getId());
		chamberType.setName(chamber.getName());
		chamberType.setPrice_per_night(chamber.getPrice_per_night());
		return chamberTypeRepository.save(chamberType);
	}

	@Override
	public void delete(long id) {
		chamberTypeRepository.deleteById(id);
		
	}

}
