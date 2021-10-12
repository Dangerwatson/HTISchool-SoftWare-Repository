package com.pathlight.hti_school.entities;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ModelSaveMoyenne {
	
	private String total;
	private String moyenne;
	private String idPerson;
	private String idClasse;
	private String anneeAcad;
	private String matiere;
	private String frequence;
	private String releveNote;
	private String mention;
	
	public ModelSaveMoyenne() {}
	
	

	public ModelSaveMoyenne(String total, String moyenne, String idPerson, String idClasse, String anneeAcad,
			String matiere, String frequence,String releveNote,String mention) {
		super();
		this.total = total;
		this.moyenne = moyenne;
		this.idPerson = idPerson;
		this.idClasse = idClasse;
		this.anneeAcad = anneeAcad;
		this.matiere = matiere;
		this.frequence = frequence;
		this.releveNote = releveNote;
		this.mention=mention;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(String moyenne) {
		this.moyenne = moyenne;
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
	public String getAnneeAcad() {
		return anneeAcad;
	}
	public void setAnneeAcad(String anneeAcad) {
		this.anneeAcad = anneeAcad;
	}
	public String getMatiere() {
		return matiere;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	public String getFrequence() {
		return frequence;
	}
	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}



	public String getReleveNote() {
		return releveNote;
	}



	public void setReleveNote(String releveNote) {
		this.releveNote = releveNote;
	}



	public String getMention() {
		return mention;
	}



	public void setMention(String mention) {
		this.mention = mention;
	}

	
	
	
	
}
