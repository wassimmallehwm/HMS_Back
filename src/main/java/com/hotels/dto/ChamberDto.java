package com.hotels.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ChamberDto {

	@JsonProperty
	private Long id;
    
	@JsonProperty
    private String ref;

	@JsonProperty
    private String type;

	@JsonProperty
    private double price;
	
	@JsonProperty
    private long hotel;
	
	@JsonProperty
    private String hotelName;

	public ChamberDto() {
		super();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getHotel() {
		return hotel;
	}

	public void setHotel(long hotel) {
		this.hotel = hotel;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
