package com.hotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotels.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
