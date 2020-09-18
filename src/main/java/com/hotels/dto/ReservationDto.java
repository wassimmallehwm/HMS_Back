package com.hotels.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationDto {

	@JsonProperty
	private Long id;
    
    @JsonProperty
    private long client;
    
    @JsonProperty
    private long chamber;
    
    @JsonProperty
    private int duration;
    
    @JsonProperty
    private double price;
    
    @JsonProperty
    private int nbAdultes;
    
    @JsonProperty
    private int nbKids;
    
    @JsonProperty
    private String state;

	public ReservationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationDto(Long id, long client, long chamber, int duration, double price, int nbAdultes, int nbKids,
			String state) {
		super();
		this.id = id;
		this.client = client;
		this.chamber = chamber;
		this.duration = duration;
		this.price = price;
		this.nbAdultes = nbAdultes;
		this.nbKids = nbKids;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getClient() {
		return client;
	}

	public void setClient(long client) {
		this.client = client;
	}

	public long getChamber() {
		return chamber;
	}

	public void setChamber(long chamber) {
		this.chamber = chamber;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
    
    

}
