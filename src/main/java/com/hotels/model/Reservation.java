package com.hotels.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private Date createdOn;
    
    @Column
    private long cin_client;
    
    @Column
    private int nb_persons;
    
    @Column
    private int duration;
    
    @Column
    private double price;

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(Long id, Date createdOn, long cin_client, int nb_persons, int duration, double price) {
		super();
		this.id = id;
		this.createdOn = createdOn;
		this.cin_client = cin_client;
		this.nb_persons = nb_persons;
		this.duration = duration;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		createdOn = new Date();
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public long getCin_client() {
		return cin_client;
	}

	public void setCin_client(long cin_client) {
		this.cin_client = cin_client;
	}

	public int getNb_persons() {
		return nb_persons;
	}

	public void setNb_persons(int nb_persons) {
		this.nb_persons = nb_persons;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
    
    

}
