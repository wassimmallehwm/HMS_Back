package com.hotels.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "hotels")
public class Hotel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private String adresse;
    
    @Column
    private int stars;
    
    @Column
    private String city;
    
    @Column
    private String tel;
    
    @OneToMany(mappedBy="hotel",fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Chamber> chambers = new HashSet<>();

	public Hotel() {
		super();
	}
	

	public Hotel(String name, String adresse, int stars, String city, String tel, Set<Chamber> chambers) {
		super();
		this.name = name;
		this.adresse = adresse;
		this.stars = stars;
		this.city = city;
		this.tel = tel;
		this.chambers = chambers;
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

	public Set<Chamber> getChambers() {
		return chambers;
	}

	public void setChambers(Set<Chamber> chambers) {
		this.chambers = chambers;
	}
	
	
	
}
