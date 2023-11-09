package fr.diginamic.springmvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.springmvc.model.Person;
import fr.diginamic.springmvc.repository.PersonRepository;

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
	
}
