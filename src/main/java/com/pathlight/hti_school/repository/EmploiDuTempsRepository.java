package com.pathlight.hti_school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.EmploiDuTemps;



public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, Long> {

	//lister emploie pas classe
	public List<EmploiDuTemps> findByClasse(Classe c);
	

}
