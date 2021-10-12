package com.pathlight.hti_school.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Moyenne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long idMoy;
	private float total;
	private float moyenne;
	private String mention;
	
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
	
	@ManyToOne()
	  @JoinColumn(name="idRelve")
	private ReleveNote releveNote;
	
	public Moyenne() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Moyenne(float total, float moyenne, Eleves eleves, Matiere matiere, Classe classe, String frequence,
			AnneeAcademique anneeAcad,ReleveNote releveNote,String mention) {
		super();
		this.total = total;
		this.moyenne = moyenne;
		this.eleves = eleves;
		this.matiere = matiere;
		this.classe = classe;
		this.frequence = frequence;
		this.anneeAcad = anneeAcad;
		this.releveNote = releveNote;
		this.mention=mention;
	}

	public Long getIdMoy() {
		return idMoy;
	}

	public void setIdMoy(Long idMoy) {
		this.idMoy = idMoy;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}

	public Eleves getEleves() {
		return eleves;
	}

	public void setEleves(Eleves eleves) {
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

	public ReleveNote getReleveNote() {
		return releveNote;
	}


	public void setReleveNote(ReleveNote releveNote) {
		this.releveNote = releveNote;
	}


	public String getMention() {
		return mention;
	}

	public void setMention(String mention) {
		this.mention = mention;
	}

	
	
	
	
	

}
