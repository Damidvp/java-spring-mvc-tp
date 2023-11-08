package fr.diginamic.springmvc.repository;

import fr.diginamic.springmvc.model.Animal;
import fr.diginamic.springmvc.model.Person;

public interface PersonRepositoryCustom {
	
	void deletePersonsWithoutAnimal();
	void createPersonEntities(Integer numberOfEntities);
	
	void adoptAnimalForPerson(Person person, Animal animal);
	void giveAnimalsToRandomPersons(Integer numberOfAdoptions);
}
