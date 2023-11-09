package fr.diginamic.springmvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.springmvc.model.Species;
import fr.diginamic.springmvc.repository.SpeciesRepository;

@Controller
@RequestMapping("/species")
public class SpeciesController {
	
	@Autowired
	private SpeciesRepository speciesRepo;
	
	@GetMapping
	public String listSpecies(Model model) {
		List<Species> species = speciesRepo.findAll();
		model.addAttribute("list_species", species);
		return "list_species";
	}
	
	@GetMapping("/{id}")
	public String getOneSpecies(@PathVariable("id") Integer id, Model model) {
		System.out.println("ID demandé : " + id);
		Optional<Species> species = speciesRepo.findById(id);
		if(species.isPresent()) {
			model.addAttribute("update_species", species.get());
			return "update_species";
		}
		return "error";
	}
	
	@GetMapping("/create")
	public String getCreateSpecies(Model model) {
		model.addAttribute("create_species", new Species());
		return "create_species";
	}
	
}
