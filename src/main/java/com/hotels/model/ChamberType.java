package com.hotels.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chamber_types")
public class ChamberType {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private double price_per_night;

	public ChamberType() {
		super();
	}

	public ChamberType(String name, double price_per_night) {
		super();
		this.name = name;
		this.price_per_night = price_per_night;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice_per_night() {
		return price_per_night;
	}

	public void setPrice_per_night(double price_per_night) {
		this.price_per_night = price_per_night;
	}
    
    

}
