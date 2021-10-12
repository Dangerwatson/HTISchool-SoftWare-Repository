package com.pathlight.hti_school.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AnneeAcademique {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAcad;
	
	@Column(length=9, nullable= false)
	private String anneeAC;
	
	public AnneeAcademique() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnneeAcademique(Long idAcad, String anneeAC) {
		super();
		this.idAcad = idAcad;
		this.anneeAC = anneeAC;
		
	}

	public Long getIdAcad() {
		return idAcad;
	}

	public void setIdAcad(Long idAcad) {
		this.idAcad = idAcad;
	}

	public String getAnneeAC() {
		return anneeAC;
	}

	public void setAnneeAC(String anneeAC) {
		this.anneeAC = anneeAC;
	}


	
	
}
