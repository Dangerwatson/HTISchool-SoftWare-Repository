 package com.pathlight.hti_school.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pathlight.hti_school.entities.AnneeAcademique;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.Personne;

public interface ElevesRepository extends JpaRepository<Eleves, Long> {
	
	
	  @Query(value = "SELECT max(codeEl) FROM Eleves") 
	  public String max();
	  
	  
	  //lister eleve pas classe
	 // @Query(value="SELECT e, nom,prenom, p, presAbs From Eleves e, Presence p where idPersonne=idPresence")
	  public List<Eleves> findByClasse(Classe cl);
	  
	  
	  
	  
	  @Query(value = "SELECT COUNT(idPerson) as idPerson FROM Eleves") 
	  public Long countEleve();

	  //si email exit
	  @Query("SELECT u FROM Eleves u WHERE u.emailM = ?1")
	public Eleves findByEmailM(String emailM);





	


	


	


	
	  
	 
	 
}
