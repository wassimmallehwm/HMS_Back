package com.hotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotels.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
