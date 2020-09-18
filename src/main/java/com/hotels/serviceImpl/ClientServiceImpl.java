package com.hotels.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.model.Client;
import com.hotels.repository.ClientRepository;
import com.hotels.service.ClientService;

@Service("ClientService")
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client findOne(long id) {
		return clientRepository.findById(id).orElse(null);
	}

	@Override
	public Client create(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client update(Client client) {
		Client newClient = findOne(client.getId());
		newClient.setAge(client.getAge());
		newClient.setCin(client.getCin());
		newClient.setEmail(client.getEmail());
		newClient.setFirstname(client.getFirstname());
		newClient.setLastname(client.getLastname());
		newClient.setPhone(client.getPhone());
		return clientRepository.save(newClient);
	}

	@Override
	public void delete(long id) {
		clientRepository.deleteById(id);
	}

}
