package fr.diginamic.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.diginamic.springmvc.model.Species;
import fr.diginamic.springmvc.repository.SpeciesRepository;

@Controller
public class SpeciesController {
	
	@Autowired
	private SpeciesRepository speciesRepo;
	
	@GetMapping("/species")
	public String listSpecies(Model model) {
		List<Species> species = speciesRepo.findAll();
		model.addAttribute("species", species);
		return "list_species";
	}
	
	@GetMapping("/species/{id}")
	public String getOneSpecies(@PathVariable("id") Integer id, Model model) {
		System.out.println("ID demandé : " + id);
		Species species = speciesRepo.findById(id).orElseThrow();
		model.addAttribute(species);
		return "update_species";
	}
	
	@GetMapping("/species/create")
	public String getCreateSpecies(Model model) {
		model.addAttribute(new Species());
		return "create_species";
	}
	
}
