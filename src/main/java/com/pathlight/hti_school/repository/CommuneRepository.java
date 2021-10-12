package com.pathlight.hti_school.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pathlight.hti_school.entities.Commune;
import com.pathlight.hti_school.entities.Departement;

public interface CommuneRepository extends CrudRepository<Commune, Integer> {
	
	public List<Commune> findByDepartement(Departement departement);
	
	
}
