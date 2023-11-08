package fr.diginamic.springmvc.service;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class BavardService {
	
	private String nom = "Arno Camoa";

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String parler() {
		return this.nom + " [" + this.getClass().getSimpleName() + "]";
	}
	
	@PostConstruct
	private void postConstruct() {
		System.out.println("Print de postConstruct");
	}
	
}
