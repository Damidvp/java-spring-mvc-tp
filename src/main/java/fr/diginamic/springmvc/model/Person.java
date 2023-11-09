package fr.diginamic.springmvc.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Person {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer age;
	private String firstname;
	private String lastname;
	
	@ManyToMany
	private List<Animal> animals = new ArrayList<>();
	
	public Person() {}
	
	public Person(Integer age, String firstname, String lastname) {
		this.age = age;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		String display = "*** PERSON - " + this.firstname + " " + this.lastname.toUpperCase() + ", " + 
				this.age + " ans, ";
		if(this.animals.size() > 1) {
			display = display + this.animals.size() + " animaux ***";
		} else {
			display = display + this.animals.size() + " animal ***";
		}
		return display;
	}
	
	public void addAnimal(Animal animal) {
		this.animals.add(animal);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}
