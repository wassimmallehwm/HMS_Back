package com.hotels.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
public class HotelDto {
	
	@JsonProperty
	private Long id;
    
    @JsonProperty
    private String name;
    
    @JsonProperty
    private String adresse;
    
    @JsonProperty
    private int stars;
    
    @JsonProperty
    private String city;
    
    @JsonProperty
    private String tel;

	public HotelDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
    
    

}
