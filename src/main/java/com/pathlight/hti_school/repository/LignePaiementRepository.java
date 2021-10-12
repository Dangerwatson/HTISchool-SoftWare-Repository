package com.pathlight.hti_school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathlight.hti_school.entities.AnneeAcademique;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.LignePaiement;

public interface LignePaiementRepository extends JpaRepository<LignePaiement, Long> {

	public List<LignePaiement> findByAnneeAcad(AnneeAcademique anneeAcad);
	
	public List<LignePaiement> findByClasse(Classe classe);
}
