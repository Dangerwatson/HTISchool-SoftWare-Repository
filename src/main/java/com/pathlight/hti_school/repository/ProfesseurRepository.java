package com.pathlight.hti_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pathlight.hti_school.entities.Professeurs;

public interface ProfesseurRepository extends JpaRepository<Professeurs, Long> {
	 @Query(value = "SELECT max(codeProf) FROM Professeurs") 
	 public String maxProf();
	 
	 
	  @Query(value = "SELECT COUNT(idPerson) FROM Professeurs") 
	  public Long countProf();
}
