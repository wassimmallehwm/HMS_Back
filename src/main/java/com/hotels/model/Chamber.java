package com.hotels.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "chambers")
public class Chamber {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String ref;
    
    @ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "id")
    private ChamberType chamberType;
    
    @ManyToOne()
    @JsonManagedReference
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")    
    private Hotel hotel;

	public Chamber() {
		super();
	}

	public Chamber(String ref, ChamberType type, Hotel hotel) {
		super();
		this.ref = ref;
		this.chamberType = type;
		this.hotel = hotel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public ChamberType getType() {
		return chamberType;
	}

	public void setType(ChamberType type) {
		this.chamberType = type;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
    
    

}
