package com.pathlight.hti_school.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Presence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPresence;
	private String presAbs;
	private Date dateAppel;
	
	@ManyToOne()
	  @JoinColumn(name="idPerson")
	private Eleves elevesP;
	
	@ManyToOne()
	  @JoinColumn(name="idClasse")
	private Classe classeP;
	
	
	public Presence() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	public Presence(Long idPresence, String presAbs, Eleves elevesP, Classe classeP,Date dateAppel) {
		super();
		this.idPresence = idPresence;
		this.presAbs = presAbs;
		this.elevesP = elevesP;
		this.classeP = classeP;
		this.dateAppel = dateAppel;
	}


	public Long getIdPresence() {
		return idPresence;
	}


	public void setIdPresence(Long idPresence) {
		this.idPresence = idPresence;
	}



	public String getPresAbs() {
		return presAbs;
	}


	public void setPresAbs(String presAbs) {
		this.presAbs = presAbs;
	}


	public Eleves getElevesP() {
		return elevesP;
	}


	public void setElevesP(Eleves elevesP) {
		this.elevesP = elevesP;
	}


	public Classe getClasseP() {
		return classeP;
	}


	public void setClasseP(Classe classeP) {
		this.classeP = classeP;
	}


	public Date getDateAppel() {
		return dateAppel;
	}


	public void setDateAppel(Date dateAppel) {
		this.dateAppel = dateAppel;
	}
	
	



}
