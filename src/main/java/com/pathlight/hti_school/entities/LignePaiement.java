package com.pathlight.hti_school.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class LignePaiement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long idLignePaiement;
	private String codePaiement;
@ManyToOne()
@JoinColumn(name="idPerson")
private Eleves eleves;
@ManyToOne()
@JoinColumn(name="idPaiement")
private Paiement paiement;
private String modalite;
private float coutModalite;
private float quantiteVerser;
private float balance;

private Date datePaie;
@ManyToOne()
@JoinColumn(name="idClasse")
private Classe classe;
@ManyToOne()
@JoinColumn(name="idAcad")
private AnneeAcademique anneeAcad;

	public LignePaiement() {
		// TODO Auto-generated constructor stub
	}
	public LignePaiement(Long idLignePaiement, String codePaiement, Eleves eleves, Paiement paiement, String modalite, float coutModalite, float quantiteVerser, float balance, Date datePaie, Classe classe, AnneeAcademique anneeAcad) {
		super();
		this.idLignePaiement = idLignePaiement;
		this.codePaiement = codePaiement;
		this.eleves = eleves;
		this.paiement = paiement;
		this.modalite=modalite;
		this.coutModalite=coutModalite;
		this.quantiteVerser = quantiteVerser;
		this.balance = balance;
		this.datePaie=datePaie;
		this.classe=classe;
		this.anneeAcad=anneeAcad;
	}
	public Long getIdLignePaiement() {
		return idLignePaiement;
	}
	public void setIdLignePaiement(Long idLignePaiement) {
		this.idLignePaiement = idLignePaiement;
	}
	
	public String getCodePaiement() {
		return codePaiement;
	}
	public void setCodePaiement(String codePaiement) {
		this.codePaiement = codePaiement;
	}
	
	public Eleves getEleves() {
		return eleves;
	}
	public void setEleves(Eleves eleves) {
		this.eleves = eleves;
	}
	public Paiement getPaiement() {
		return paiement;
	}
	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}
	
	public float getQuantiteVerser() {
		return quantiteVerser;
	}
	public void setQuantiteVerser(float quantiteVerser) {
		this.quantiteVerser = quantiteVerser;
	}
	
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public Date getDatePaie() {
		return datePaie;
	}
	public void setDatePaie(Date datePaie) {
		this.datePaie = datePaie;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}


	public float getCoutModalite() {
		return coutModalite;
	}
	public void setCoutModalite(float coutModalite) {
		this.coutModalite = coutModalite;
	}
	

public String getModalite() {
	return modalite;
}
public void setModalite(String modalite) {
	this.modalite = modalite;
}
public AnneeAcademique getAnneeAcad() {
	return anneeAcad;
}
public void setAnneeAcad(AnneeAcademique anneeAcad) {
	this.anneeAcad = anneeAcad;
}



}
