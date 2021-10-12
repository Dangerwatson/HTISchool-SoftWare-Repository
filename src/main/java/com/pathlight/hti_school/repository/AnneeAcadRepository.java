package com.pathlight.hti_school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathlight.hti_school.entities.AnneeAcademique;

public interface AnneeAcadRepository extends JpaRepository<AnneeAcademique, Long> {

	public List<AnneeAcademique> findByOrderByAnneeACDesc();	

}
