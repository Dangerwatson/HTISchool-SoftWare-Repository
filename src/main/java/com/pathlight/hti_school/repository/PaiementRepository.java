package com.pathlight.hti_school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pathlight.hti_school.entities.AnneeAcademique;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.GestBourse;
import com.pathlight.hti_school.entities.LignePaiement;
import com.pathlight.hti_school.entities.Matiere;
import com.pathlight.hti_school.entities.Paiement;
import com.pathlight.hti_school.entities.ReleveNote;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
     
	//chercher par mot clé
	@Query("SELECT e FROM Eleves e WHERE e.codeEl LIKE :x ")
	public Eleves searchByCode(@Param("x")String codeEl);
	
	@Query("SELECT b FROM GestBourse b WHERE b.eleves.codeEl LIKE :code")
	public List<GestBourse> searchBourseByCodeEleves(@Param("code")String codeEl);
	
	@Query("SELECT p FROM Paiement p WHERE p.eleves.codeEl LIKE :x")
	public Paiement searchByBalCode(@Param("x")String codeEl);
	
	@Query("SELECT p FROM LignePaiement p WHERE p.eleves.codeEl LIKE :x")
	public List <LignePaiement> listInfoBalCode(@Param("x")String codeEl);
	
	//  @Query(value = "SELECT  balance from Paiement where eleves.idPerson =:idPerson") 
	 //@Query(value="select p.balance from paiement p where p.id_person=?", nativeQuery = true) public String returnBalance( long idPerson);
	
	public Optional<Paiement> findByEleves(Eleves eleves);

	  @Query(value = "SELECT max(codePaiement) FROM LignePaiement") 
	  public String maxCodePaie();
	  
	  @Query("SELECT b FROM GestBourse b WHERE b.eleves.codeEl LIKE :code")
		public GestBourse searchBourseByCodeEleve(@Param("code")String codeEl);
	  
	  
	  @Query(value = "SELECT max(idAcad) FROM AnneeAcademique") 
	  public long maxAnnee();
	  
	  @Query(value = "SELECT max(lignePaiement) FROM LignePaiement lignePaiement") 
	  public LignePaiement maxIdPaie();
	  
}
