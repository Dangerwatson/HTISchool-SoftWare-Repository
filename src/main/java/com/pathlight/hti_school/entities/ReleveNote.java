package com.pathlight.hti_school.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReleveNote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
    private Long idRelve;
	private Float note;
	@ManyToOne()
	  @JoinColumn(name="idPerson")
	private Eleves eleves;
	@ManyToOne()
	  @JoinColumn(name="idMatiere")
	private Matiere matiere;
	@ManyToOne()
	  @JoinColumn(name="idClasse")
	private Classe classe;
	
	private String frequence;
	@ManyToOne()
	  @JoinColumn(name="idAcad")
	private AnneeAcademique anneeAcad;
	
	
	public ReleveNote() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ReleveNote(Float note, Eleves eleves, Matiere matiere, Classe classe, String frequence,
			AnneeAcademique anneeAcad) {
		super();
		this.note = note;
		this.eleves = eleves;
		this.matiere = matiere;
		this.classe = classe;
		this.frequence = frequence;
		this.anneeAcad = anneeAcad;
	}
	
	
	public Long getIdRelve() {
		return idRelve;
	}
	public void setIdRelve(Long idRelve) {
		this.idRelve = idRelve;
	}
	public Float getNote() {
		return note;
	}
	public void setNote(Float note) {
		this.note = note;
	}
	
	


	public Eleves getEleves() {
		return eleves;
	}


	public void setPersonne(Eleves eleves) {
		this.eleves = eleves;
	}


	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
	public String getFrequence() {
		return frequence;
	}


	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}


	public AnneeAcademique getAnneeAcad() {
		return anneeAcad;
	}
	public void setAnneeAcad(AnneeAcademique anneeAcad) {
		this.anneeAcad = anneeAcad;
	}
	
	
	
	
}
