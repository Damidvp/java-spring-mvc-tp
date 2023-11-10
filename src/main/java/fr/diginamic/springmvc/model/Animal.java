package fr.diginamic.springmvc.model;

import java.util.List;

import fr.diginamic.springmvc.enums.Sex;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Animal {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	private String color;
	
	@NotEmpty
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@NotNull
	private Species species;
	
	@ManyToMany(mappedBy = "animals", cascade = CascadeType.REMOVE)
	private List<Person> person;
	
	public Animal() {}

	public Animal(String color, String name, Sex sex, Species species) {
		this.color = color;
		this.name = name;
		this.sex = sex;
		this.species = species;
	}

	@Override
	public String toString() {
		return "*** ANIMAL - " + this.name + ", " + this.color + ", " + this.sex + ", " + 
				this.species.getCommonName() + " ***";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

}
