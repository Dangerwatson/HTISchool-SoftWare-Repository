package com.pathlight.hti_school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Moyenne;
import com.pathlight.hti_school.entities.ReleveNote;

public interface MoyenneRepository extends JpaRepository<Moyenne, Long> {
	

	//lister moyenne pas classe
	public List<Moyenne> findByClasse(Classe moyenn);

	@Query("SELECT r FROM Moyenne r WHERE r.eleves.codeEl LIKE :code")
	public Moyenne searchNoteByCodeEleves(@Param("code")String codeEl);
	
	@Query("SELECT e from Moyenne e where e.mention like 'Reussi' and e.frequence.frequence like 'Janvier' and e.classe.idClasse=:idClasse")
	public List<Moyenne> searchMoyByCodeEleves(@Param("idClasse")Long idClasse);

}
