package fr.diginamic.springmvc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.javafaker.Faker;

import fr.diginamic.springmvc.model.Animal;
import fr.diginamic.springmvc.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void deletePersonsWithoutAnimal() {
		// TODO Auto-generated method stub
		em.createQuery("DELETE FROM Person p WHERE SIZE(p.animals) = 0").executeUpdate();
	}

	@Override
	public void createPersonEntities(Integer numberOfEntities) {
		// TODO Auto-generated method stub
		Faker faker = new Faker(Locale.FRANCE);
		for(int i=0; i<numberOfEntities; i++) {
			Person person = new Person();
			
			person.setFirstname(faker.name().firstName());
			person.setLastname(faker.name().lastName());
			person.setAge(faker.number().numberBetween(16, 80));

			em.persist(person);
		}
	}
	
	@Override
	public void adoptAnimalForPerson(Person person, Animal animal) {
		// TODO Auto-generated method stub
		person.addAnimal(animal);
		em.persist(person);
	}

	@Override
	public void giveAnimalsToRandomPersons(Integer numberOfAdoptions) {
		// TODO Auto-generated method stub
		List<Person> allPersons = em.createQuery("SELECT p FROM Person p").getResultList();
		List<Animal> allAnimals = em.createQuery("SELECT a FROM Animal a").getResultList();
		for(int i=0; i<numberOfAdoptions; i++) {
			Person randomPerson = allPersons.get(new Random().nextInt(allPersons.size()));
			Animal randomAnimal = allAnimals.get(new Random().nextInt(allAnimals.size()));
			
			if(!randomPerson.getAnimals().contains(randomAnimal)) {
				randomPerson.addAnimal(randomAnimal);
				em.persist(randomPerson);
			}
			
		}
	}
	
}
