package fr.diginamic.springmvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.diginamic.springmvc.model.Species;
import fr.diginamic.springmvc.repository.SpeciesRepository;
import jakarta.validation.Valid;

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
	
	@PostMapping("/new")
	public String createSpecies(@Valid @ModelAttribute("create_species") Species speciesItem, BindingResult result) {
		if (result.hasErrors()) {
	        return "create_species";
	    }
		speciesRepo.save(speciesItem);
		return "redirect:/species";
	}
	
	@PostMapping("/update")
	public String updateSpecies(@Valid @ModelAttribute("update_species") Species speciesItem, BindingResult result) {
		if (result.hasErrors()) {
	        return "update_species";
	    }
		speciesRepo.save(speciesItem);
		return "redirect:/species";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteSpecies(@PathVariable("id") Integer id) {
		Optional<Species> speciesToDelete = speciesRepo.findById(id);
		speciesToDelete.ifPresent(species -> speciesRepo.delete(species));
		return "redirect:/species";
	}
	
}
