package com.hotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotels.model.AppConfig;


public interface AppConfigRepository extends JpaRepository<AppConfig, Long> {

}
