package com.pathlight.hti_school.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CategoryMatiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCatMatiere;
	private String nomCatMatiere;
	public CategoryMatiere() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryMatiere(String nomCatMatiere) {
		super();
		this.nomCatMatiere = nomCatMatiere;
	}
	public Long getIdCatMatiere() {
		return idCatMatiere;
	}
	public void setIdCatMatiere(Long idCatMatiere) {
		this.idCatMatiere = idCatMatiere;
	}
	public String getNomCatMatiere() {
		return nomCatMatiere;
	}
	public void setNomCatMatiere(String nomCatMatiere) {
		this.nomCatMatiere = nomCatMatiere;
	}
	
	
}
