package fr.diginamic.springmvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Species {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "common_name", length = 50)
	@NotEmpty
	@Size(max = 50)
	private String commonName;
	
	@Column(name = "latin_name" , length = 120)
	@NotEmpty
	@Size(max = 120)
	private String latinName;
	
	public Species() {}
	
	public Species(String common_name, String latin_name) {
		super();
		this.commonName = common_name;
		this.latinName = latin_name;
	}

	@Override
	public String toString() {
		return "*** SPECIES - " + this.commonName + ", " + this.latinName + " ***";
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getLatinName() {
		return latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

}
