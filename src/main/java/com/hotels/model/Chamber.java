package com.hotels.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private ChamberType type;

	public Chamber() {
		super();
	}

	public Chamber(Long id, String ref, ChamberType type) {
		super();
		this.id = id;
		this.ref = ref;
		this.type = type;
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
		return type;
	}

	public void setType(ChamberType type) {
		this.type = type;
	}
    
    

}
