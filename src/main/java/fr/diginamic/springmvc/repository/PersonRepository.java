package fr.diginamic.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.diginamic.springmvc.model.Animal;
import fr.diginamic.springmvc.model.Person;

public interface PersonRepository extends PersonRepositoryCustom, JpaRepository<Person, Integer>{
	
	List<Person> findByFirstnameOrLastname(String firstname, String lastname);
	List<Person> findByAgeGreaterThanEqual(Integer age);
	
	@Query("SELECT p FROM Person p WHERE p.age BETWEEN ?1 AND ?2")
	List<Person> findAllBetweenAges(Integer ageMin, Integer ageMax);
	@Query("SELECT p FROM Person p INNER JOIN p.animals a WHERE a = ?1")
	List<Person> findAllHavingThisAnimal(Animal animal);
	
	//Retourne les personnes ayant 2 animaux ou plus
	@Query("SELECT p FROM Person p WHERE SIZE(p.animals) > 1")
	List<Person> findNicePersons();

}
