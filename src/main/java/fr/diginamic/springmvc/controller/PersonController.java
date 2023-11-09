package fr.diginamic.springmvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.diginamic.springmvc.model.Person;
import fr.diginamic.springmvc.model.Species;
import fr.diginamic.springmvc.repository.PersonRepository;

@Controller
public class PersonController {

	@Autowired
	private PersonRepository personRepo;
	
	@GetMapping("/persons")
	public String listPersons(Model model) {
		List<Person> persons = personRepo.findAll();
		model.addAttribute("list_persons", persons);
		return "list_persons";
	}
	
	@GetMapping("/persons/{id}")
	public String getOnePerson(@PathVariable("id") Integer id, Model model) {
		Optional<Person> person = personRepo.findById(id);
		if(person.isPresent()) {
			model.addAttribute(person.get());
			return "update_person";
		}
		return "error";
	}
	
	@GetMapping("/persons/create")
	public String getCreatePerson(Model model) {
		model.addAttribute(new Person());
		return "create_person";
	}
	
}
