package com.pathlight.hti_school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.Matiere;
import com.pathlight.hti_school.entities.Moyenne;
import com.pathlight.hti_school.entities.ReleveNote;

public interface NotesRepository extends JpaRepository<ReleveNote, Long> {

	public List<ReleveNote> findByClasse(Classe notc);

	//chercher par mot clé
		@Query("SELECT r FROM ReleveNote r WHERE r.eleves.codeEl LIKE :code")
		public List<ReleveNote> searchNoteByCodeEleves(@Param("code")String codeEl);
		
		@Query("SELECT e FROM Eleves e WHERE e.codeEl LIKE :codeEleves")
		public Eleves searchInfoByCodeEleves(@Param("codeEleves")String codeEl);        
		
        
		//lister note pas personne
		//public List<ReleveNote> findByPersonne(Eleves notcp);
		@Query("SELECT r FROM ReleveNote r WHERE r.frequence  LIKE :frequence AND r.anneeAcad.idAcad LIKE :anneeAC AND r.classe.idClasse LIKE :classeName")
		public List<ReleveNote> listNotes(@Param("frequence")String frequence, 
				@Param("anneeAC")Long anneeAC, 
				@Param("classeName")Long classeName);
		
		@Query("SELECT m FROM Matiere m WHERE  m.classe.classeName LIKE:classeName")
		public List<Matiere>listByClasseParam(@Param("classeName")String classeName);
		
		@Query("SELECT e FROM Eleves e WHERE e.classe.classeName LIKE:classeName")
		public List<Eleves>listElByClasseParam(@Param("classeName")String classeName);
	
}
