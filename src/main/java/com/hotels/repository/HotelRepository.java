package com.hotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotels.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>  {

}
