package fr.diginamic.springmvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.springmvc.model.Animal;
import fr.diginamic.springmvc.repository.AnimalRepository;
import fr.diginamic.springmvc.repository.SpeciesRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/animals")
public class AnimalController {

	@Autowired
	private AnimalRepository animalRepo;
	
	@Autowired
	private SpeciesRepository speciesRepo;
	
	@GetMapping
	public String listAnimals(Model model) {
		List<Animal> animals = animalRepo.findAll();
		model.addAttribute("list_animals", animals);
		return "list_animals";
	}
	
	@GetMapping("/{id}")
	public String getOneAnimal(@PathVariable("id") Integer id, Model model) {
		System.out.println("ID demandé : " + id);
		Optional<Animal> animal = animalRepo.findById(id);
		if(animal.isPresent()) {
			model.addAttribute("update_animal", animal.get());
			model.addAttribute("speciesList", speciesRepo.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
			return "update_animal";
		}
		return "error";
	}
	
	@GetMapping("/create")
	public String getCreateAnimal(Model model) {
		model.addAttribute("create_animal", new Animal());
		model.addAttribute("speciesList", speciesRepo.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
		return "create_animal";
	}
	
	@PostMapping("/new")
	public String createAnimal(@Valid @ModelAttribute("create_animal") Animal animalItem, BindingResult result, Model model) {
		model.addAttribute("speciesList", speciesRepo.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
		if(result.hasErrors()) {
			return "create_animal";
		}
		animalRepo.save(animalItem);
		return "redirect:/animals";
	}
	
	@PostMapping("/update")
	public String updateAnimal(@Valid @ModelAttribute("update_animal") Animal animalItem, BindingResult result, Model model) {
		model.addAttribute("speciesList", speciesRepo.findAll(Sort.by(Sort.Direction.ASC, "commonName")));
		if(result.hasErrors()) {
			return "update_animal";
		}
		animalRepo.save(animalItem);
		return "redirect:/animals";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAnimal(@PathVariable("id") Integer id) {
		Optional<Animal> animalToDelete = animalRepo.findById(id);
		animalToDelete.ifPresent(animal -> animalRepo.delete(animal));
		return "redirect:/animals";
	}
	
}
