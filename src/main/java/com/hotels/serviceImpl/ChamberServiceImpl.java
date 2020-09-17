package com.hotels.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.model.Chamber;
import com.hotels.repository.ChamberRepository;
import com.hotels.service.ChamberService;

@Service("ChamberService")
public class ChamberServiceImpl implements ChamberService {
	
	@Autowired
	private ChamberRepository chamberRepository;

	@Override
	public List<Chamber> findAll() {
		return chamberRepository.findAll();
	}

	@Override
	public Chamber findOne(long id) {
		return chamberRepository.findById(id).orElse(null);
	}

	@Override
	public Chamber create(Chamber chamber) {
		return chamberRepository.save(chamber);
	}

	@Override
	public Chamber update(Chamber chamber) {
		Chamber newChamber = findOne(chamber.getId());
		newChamber.setRef(chamber.getRef());
		newChamber.setType(chamber.getType());
		newChamber.setHotel(chamber.getHotel());
		return chamberRepository.save(newChamber);
	}

	@Override
	public void delete(long id) {
		chamberRepository.deleteById(id);
	}

}
