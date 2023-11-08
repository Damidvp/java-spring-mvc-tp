package fr.diginamic.springmvc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

import fr.diginamic.springmvc.enums.Sex;
import fr.diginamic.springmvc.model.Animal;
import fr.diginamic.springmvc.model.Species;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class AnimalRepositoryCustomImpl implements AnimalRepositoryCustom{

	@PersistenceContext
	private EntityManager em;
	
	private List<String> randomColors = new ArrayList<>();
	private List<Sex> randomSexs = new ArrayList<>();
	private List<Species> randomSpecies = new ArrayList<>();
	
	@Override
	public void createAnimalEntites(Integer numberOfEntities) {
		// TODO Auto-generated method stub
		createLists();
		Faker faker = new Faker(Locale.FRANCE);
		for(int i=0; i<numberOfEntities; i++) {
			Animal animal = new Animal();
			
			animal.setColor(randomColors.get(new Random().nextInt(randomColors.size())));
			animal.setName(faker.pokemon().name());
			animal.setSex(randomSexs.get(new Random().nextInt(randomSexs.size())));
			animal.setSpecies(randomSpecies.get(new Random().nextInt(randomSpecies.size())));
			
			em.persist(animal);
		}
	}
	
	private void createLists() {
		randomColors.add("Noir");
		randomColors.add("Blanc");
		randomColors.add("Roux");
		randomColors.add("Gris");
		randomColors.add("Violet");
		
		randomSexs.add(Sex.M);
		randomSexs.add(Sex.F);
		
		randomSpecies = em.createQuery("SELECT s FROM Species s").getResultList();
	}

}
