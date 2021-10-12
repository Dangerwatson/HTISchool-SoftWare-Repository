package com.pathlight.hti_school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.Presence;



public interface PresenceRepository extends JpaRepository<Presence, Long> {

public List<Presence> findByClasseP(Classe c);

	

	

	
	

}
