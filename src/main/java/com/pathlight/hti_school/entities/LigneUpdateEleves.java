package com.pathlight.hti_school.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class LigneUpdateEleves {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long updateEleves;
@ManyToOne()
@JoinColumn(name="idPerson")
private Eleves eleves;
@ManyToOne
@JoinColumn(name = "idClasse")
private Classe classe;
@ManyToOne
@JoinColumn(name = "idAcad")
private AnneeAcademique AnneeAcade;
	public LigneUpdateEleves() {
		// TODO Auto-generated constructor stub
	}
	public LigneUpdateEleves(Long updateEleves, Eleves eleves, Classe classe, AnneeAcademique anneeAcade) {
		super();
		this.updateEleves = updateEleves;
		this.eleves = eleves;
		this.classe = classe;
		AnneeAcade = anneeAcade;
	}
	public Long getUpdateEleves() {
		return updateEleves;
	}
	public void setUpdateEleves(Long updateEleves) {
		this.updateEleves = updateEleves;
	}
	public Eleves getEleves() {
		return eleves;
	}
	public void setEleves(Eleves eleves) {
		this.eleves = eleves;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public AnneeAcademique getAnneeAcade() {
		return AnneeAcade;
	}
	public void setAnneeAcade(AnneeAcademique anneeAcade) {
		AnneeAcade = anneeAcade;
	}
	
	

}
