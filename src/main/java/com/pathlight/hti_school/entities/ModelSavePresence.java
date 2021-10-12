package com.pathlight.hti_school.entities;

import java.util.Date;

public class ModelSavePresence {
	private String presAbs ;
	private String idPerson;
	private String idClasse;
	private String dateAppel;

	public ModelSavePresence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ModelSavePresence(String presAbs,String idPerson,String idClasse,String dateAppel) {
		super();
		this.presAbs = presAbs;
		this.idPerson = idPerson;
		this.idClasse = idClasse;
		this.dateAppel = dateAppel;
	}

	public String getPresAbs() {
		return presAbs;
	}

	public void setPresAbs(String presAbs) {
		this.presAbs = presAbs;
	}

	public String getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(String idPerson) {
		this.idPerson = idPerson;
	}

	public String getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(String idClasse) {
		this.idClasse = idClasse;
	}

	public String getDateAppel() {
		return dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}
	

	
	
	

}
