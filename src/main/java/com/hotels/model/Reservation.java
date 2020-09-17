package com.hotels.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private Date createdOn;
    
    @ManyToOne()
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    
    @Column
    private int duration;
    
    @Column
    private double price;
    
    @Column
    private int nbAdultes;
    
    @Column
    private int nbKids;
    
    @Column
    private String state;
    
    @ManyToOne()
    @JoinColumn(name = "chamber_id", referencedColumnName = "id")    
    private Chamber chamber;

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(Long id, Date createdOn, long cin_client, int nb_persons, int duration, double price) {
		super();
		this.id = id;
		this.createdOn = createdOn;
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

	public int getNbAdultes() {
		return nbAdultes;
	}

	public void setNbAdultes(int nbAdultes) {
		this.nbAdultes = nbAdultes;
	}

	public int getNbKids() {
		return nbKids;
	}

	public void setNbKids(int nbKids) {
		this.nbKids = nbKids;
	}

	public Chamber getChamber() {
		return chamber;
	}

	public void setChamber(Chamber chamber) {
		this.chamber = chamber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
    
    

}
