package com.pathlight.hti_school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
	@Query(value = "SELECT max(codeClasse) FROM Classe")
	public String maxClasse();
	
	  @Query(value = "SELECT COUNT(idClasse) FROM Classe") 
	  public Long countClasse();
	  
		/*
		 * @Query(value =
		 * "SELECT c, c.classe.idClasse , COUNT(c.idPerson) as countEleves FROM Eleves c where c.classe.idClasse= c.idPerson"
		 * ) public List<Classe> countEleveByClasse();
		 */
}

