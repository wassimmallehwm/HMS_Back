package com.hotels.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChamberReservationDto {
	
	@JsonProperty
	private Long id;
    
    @JsonProperty
    private String ref;

	public ChamberReservationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChamberReservationDto(Long id, String ref) {
		super();
		this.id = id;
		this.ref = ref;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
