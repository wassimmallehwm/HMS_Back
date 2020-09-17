package com.hotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotels.model.Chamber;

public interface ChamberRepository extends JpaRepository<Chamber, Long> {

}
