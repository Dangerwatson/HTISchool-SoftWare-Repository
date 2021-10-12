package com.pathlight.hti_school.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Matiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMatiere;
	private String codeMatiere;
	private String nomMatiere;
	private Long coefficient;
	@ManyToOne
	  @JoinColumn(name="idCatMatiere") 
	private CategoryMatiere cateMat;
	
	
	@ManyToOne()
	  @JoinColumn(name="idClasse")	
	private Classe classe;
	//private Professeur prof;
	public Matiere() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Matiere(String codeMatiere, String nomMatiere, Long coefficient, CategoryMatiere cateMat, Classe classe) {
		super();
		this.codeMatiere = codeMatiere;
		this.nomMatiere = nomMatiere;
		this.coefficient = coefficient;
		this.cateMat = cateMat;
		this.classe = classe;
	}
	public Long getIdMatiere() {
		return idMatiere;
	}
	public void setIdMatiere(Long idMatiere) {
		this.idMatiere = idMatiere;
	}
	public String getCodeMatiere() {
		return codeMatiere;
	}
	public void setCodeMatiere(String codeMatiere) {
		this.codeMatiere = codeMatiere;
	}
	public String getNomMatiere() {
		return nomMatiere;
	}
	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}
	public Long getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Long coefficient) {
		this.coefficient = coefficient;
	}
	public CategoryMatiere getCateMat() {
		return cateMat;
	}
	public void setCateMat(CategoryMatiere cateMat) {
		this.cateMat = cateMat;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	
	
}
