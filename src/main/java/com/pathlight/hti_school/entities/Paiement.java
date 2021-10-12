package com.pathlight.hti_school.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Paiement{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long idPaie;
@ManyToOne()
@JoinColumn(name="idPerson")
private Eleves eleves;
private float quantiteVerser;
private float balance;


public Paiement() {
	super();
	// TODO Auto-generated constructor stub
}
public Paiement(Long idBalance,  Eleves eleves, float quantiteVerser, float balance) {
	super();
	
	this.eleves = eleves;
	this.quantiteVerser = quantiteVerser;
	this.balance = balance;
	
	
}
public Long getIdPaie() {
	return idPaie;
}
public void setIdPaie(Long idPaie) {
	this.idPaie = idPaie;
}


public Eleves getEleves() {
	return eleves;
}
public void setEleves(Eleves eleves) {
	this.eleves = eleves;
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





}
