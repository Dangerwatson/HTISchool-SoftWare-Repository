package com.pathlight.hti_school.entities;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ModelSaveNote {
	
	private String note;
	private String idPerson;
	private String idClasse;
	private String anneeAcad;
	private String matiere;
	private String frequence;
	//private String eleves;
	public ModelSaveNote() {}

	public ModelSaveNote(String note, String idPerson, String idClasse) {
		super();
		this.note = note;
		this.idPerson = idPerson;
		this.idClasse = idClasse;
		
	}

	
	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String idMatiere) {
		this.matiere = idMatiere;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
		public String getIdClasse() {
		return idClasse;
	}
	public void setIdClasse(String idClasse) {
		this.idClasse = idClasse;
	}

	public String getAnneeAcad() {
		return anneeAcad;
	}

	public void setAnneeAcad(String idAcad) {
		this.anneeAcad = idAcad;
	}

	

	public String getFrequence() {
		return frequence;
	}

	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}

	public String getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(String idPerson) {
		this.idPerson = idPerson;
	}
	
	

	

	
	

	/*
	 * public String getEleves() { return eleves; }
	 * 
	 * public void setEleves(String idPerson) { this.eleves = idPerson; }
	 */
	
	
	
	
}
