package com.pathlight.hti_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long>{
	
	//si email exit
	/*
	 * @Query("SELECT u FROM Eleves u WHERE u.emailPersonne = ?1") public Personne
	 * findByEmailPersonne(String emailPersonne);
	 */
	  

}
