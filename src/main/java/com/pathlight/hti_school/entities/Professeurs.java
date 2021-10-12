package com.pathlight.hti_school.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Professeurs extends Personne{
	private String codeProf;
	private String niveauEd;
	private String remarque;
	@ManyToOne
	@JoinColumn(name = "idClasse")
	private Classe classe;

	public Professeurs() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Professeurs(Long idPerson,String codeProf, String nom, String prenom, String sexe, Date dateNaissance, String telephone,
			String lieuDeNaissance, String adresse, String photo, String email,Classe classe,String niveauEd, String remarque) {
		super(idPerson, nom, prenom, sexe, dateNaissance, telephone, lieuDeNaissance, adresse, photo, email);
		this.codeProf = codeProf;
		this.niveauEd = niveauEd;
		this.remarque = remarque;
		
	}

	
	
	public String getCodeProf() {
		return codeProf;
	}

	public void setCodeProf(String codeProf) {
		this.codeProf = codeProf;
	}

	


	public Classe getClasse() {
		return classe;
	}


	public void setClasse(Classe classe) {
		this.classe = classe;
	}


	public String getNiveauEd() {
		return niveauEd;
	}


	public void setNiveauEd(String niveauEd) {
		this.niveauEd = niveauEd;
	}


	public String getRemarque() {
		return remarque;
	}


	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	
	
	
	
}
