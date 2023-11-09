package fr.diginamic.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.diginamic.springmvc.model.Animal;
import fr.diginamic.springmvc.repository.AnimalRepository;

@Controller
public class AnimalController {

	@Autowired
	private AnimalRepository animalRepo;
	
	@GetMapping("/animals")
	public String listAnimals(Model model) {
		List<Animal> animals = animalRepo.findAll();
		model.addAttribute("animals", animals);
		return "list_animals";
	}
	
	@GetMapping("/animals/{id}")
	public String getOneAnimal(@PathVariable("id") Integer id, Model model) {
		System.out.println("ID demandé : " + id);
		Animal animal = animalRepo.findById(id).orElseThrow();
		model.addAttribute(animal);
		return "update_animal";
	}
	
	@GetMapping("/animals/create")
	public String getCreateAnimal(Model model) {
		model.addAttribute(new Animal());
		return "create_animal";
	}
	
}
