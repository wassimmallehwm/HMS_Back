package com.hotels.service;

import java.util.List;

import com.hotels.model.Client;


public interface ClientService {

	List<Client> findAll();

	Client findOne(long id);
	
	Client create(Client client);
	
	Client update(Client client);
	
	void delete(long id);
}
