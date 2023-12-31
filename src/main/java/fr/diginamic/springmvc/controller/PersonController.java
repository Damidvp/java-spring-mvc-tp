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

import fr.diginamic.springmvc.model.Person;
import fr.diginamic.springmvc.repository.PersonRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepo;
	
	@GetMapping
	public String listPersons(Model model) {
		List<Person> persons = personRepo.findAll();
		model.addAttribute("list_persons", persons);
		return "list_persons";
	}
	
	@GetMapping("/{id}")
	public String getOnePerson(@PathVariable("id") Integer id, Model model) {
		Optional<Person> person = personRepo.findById(id);
		if(person.isPresent()) {
			model.addAttribute("update_person", person.get());
			return "update_person";
		}
		return "error";
	}
	
	@GetMapping("/create")
	public String getCreatePerson(Model model) {
		model.addAttribute("create_person", new Person());
		return "create_person";
	}
	
	@PostMapping("/new")
	public String createPerson(@Valid @ModelAttribute("create_person") Person personItem, BindingResult result) {
		if(result.hasErrors()) {
			return "create_person";
		}
		personRepo.save(personItem);
		return "redirect:/persons";
	}
	
	@PostMapping("/update")
	public String updatePerson(@Valid @ModelAttribute("update_person") Person personItem, BindingResult result) {
		if(result.hasErrors()) {
			return "update_person";
		}
		personRepo.save(personItem);
		return "redirect:/persons";
	}
	
	@GetMapping("/random") @Transactional
	public String create15Persons() {
		personRepo.createPersonEntities(15);
		return "redirect:/persons";
	}
	
	@GetMapping("/clear") @Transactional
	public String clearPersonsWithoutAnimal() {
		personRepo.deletePersonsWithoutAnimal();
		return "redirect:/persons";
	}
	
	@GetMapping("/adopt") @Transactional
	public String adopt15Animals() {
		personRepo.giveAnimalsToRandomPersons(15);
		return "redirect:/persons";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePerson(@PathVariable("id") Integer id) {
		Optional<Person> personToDelete = personRepo.findById(id);
		personToDelete.ifPresent(person -> personRepo.delete(person));
		return "redirect:/persons";
	}
	
}
