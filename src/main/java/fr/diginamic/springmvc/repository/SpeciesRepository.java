package fr.diginamic.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.diginamic.springmvc.model.Species;

public interface SpeciesRepository extends JpaRepository<Species, Integer>{

	Species findFirstByCommonName(String commonName);
	List<Species> findByLatinNameContainsIgnoreCase(String latinName);
	
	@Query("SELECT s FROM Species s ORDER BY s.commonName ASC")
	List<Species> findAllByNameAsc();
	@Query("SELECT s FROM Species s WHERE s.commonName LIKE %?1%")
	List<Species> findAllByCommonName(String commonName);
	
}
