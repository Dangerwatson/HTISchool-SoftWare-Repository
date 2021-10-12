package com.pathlight.hti_school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.Matiere;
import com.pathlight.hti_school.entities.ReleveNote;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {
	@Query(value = "SELECT max(codeMatiere) FROM Matiere")
	public String maxMatiere();
	
	//lister matiere par classe
	 public List<Matiere> findByClasse(Classe c);
	 
	
	 
	 @Query(value = "SELECT count(idMatiere) as idMatiere FROM Matiere") 
	  public Long countMatiere();

	 
}
