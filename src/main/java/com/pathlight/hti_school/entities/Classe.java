package com.pathlight.hti_school.entities;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Classe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClasse;
	private String codeClasse;
	private String nomCat;
	private String classeName;
	@OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
	private Collection<Eleves> eleves;
	
	@OneToMany(mappedBy = "classe")
	private Set<Matiere> matiere;

	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Classe(Long idClasse) {
		super();
		this.idClasse = idClasse;
	}



	public Classe(String codeClasse, String nomCat, String classeName) {
		super();
		this.codeClasse = codeClasse;
		this.nomCat = nomCat;
		this.classeName = classeName;
	}

	public Long getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(Long idClasse) {
		this.idClasse = idClasse;
	}

	public String getCodeClasse() {
		return codeClasse;
	}

	public void setCodeClasse(String codeClasse) {
		this.codeClasse = codeClasse;
	}

	

	public String getNomCat() {
		return nomCat;
	}



	public void setNomCat(String nomCat) {
		this.nomCat = nomCat;
	}



	public String getClasseName() {
		return classeName;
	}

	public void setClasseName(String classeName) {
		this.classeName = classeName;
	}

	public Collection<Eleves> getEleves() {
		return eleves;
	}

	public void setEleves(Collection<Eleves> eleves) {
		this.eleves = eleves;
	}



	public Set<Matiere> getMatiere() {
		return matiere;
	}



	public void setMatiere(Set<Matiere> matiere) {
		this.matiere = matiere;
	}
	
	

}
