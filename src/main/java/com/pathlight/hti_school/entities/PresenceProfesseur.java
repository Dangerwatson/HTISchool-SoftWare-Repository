package com.pathlight.hti_school.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PresenceProfesseur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPresence;
	private String presAbsP;
	private Date dateAppel;
	
	@ManyToOne()
	  @JoinColumn(name="idPerson")
	private Professeurs professeur;
	
	

	public PresenceProfesseur() {
		super();
		// TODO Auto-generated constructor stub
	}



	public PresenceProfesseur(Long idPresence, String presAbsP, Date dateAppel, Professeurs professeur) {
		super();
		this.idPresence = idPresence;
		this.presAbsP = presAbsP;
		this.dateAppel = dateAppel;
		this.professeur = professeur;
	}



	public Long getIdPresence() {
		return idPresence;
	}



	public void setIdPresence(Long idPresence) {
		this.idPresence = idPresence;
	}



	public String getPresAbsP() {
		return presAbsP;
	}



	public void setPresAbsP(String presAbsP) {
		this.presAbsP = presAbsP;
	}



	public Date getDateAppel() {
		return dateAppel;
	}



	public void setDateAppel(Date dateAppel) {
		this.dateAppel = dateAppel;
	}



	public Professeurs getProfesseur() {
		return professeur;
	}



	public void setProfesseur(Professeurs professeur) {
		this.professeur = professeur;
	}
	
	

}
