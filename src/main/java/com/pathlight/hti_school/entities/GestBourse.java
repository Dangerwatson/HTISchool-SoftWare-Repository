package com.pathlight.hti_school.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity 
public class GestBourse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long idBourse;
private String codeBourse;
private String typeBourse;
private String modalite;
private float coutModalite;
private Double reduction;
@ManyToOne()
@JoinColumn(name="idPerson")
private Eleves eleves;
public GestBourse() {
	super();
	// TODO Auto-generated constructor stub
}
public GestBourse(String codeBourse, String typeBourse, String modalite, float coutModalite,  Double reduction, Eleves eleves) {
	super();
	this.codeBourse = codeBourse;
	this.typeBourse=typeBourse;
	this.modalite=modalite;
	this.coutModalite=coutModalite;
	this.reduction = reduction;
	this.eleves = eleves;
	
}
public Long getIdBourse() {
	return idBourse;
}
public void setIdBourse(Long idBourse) {
	this.idBourse = idBourse;
}
public String getCodeBourse() {
	return codeBourse;
}
public void setCodeBourse(String codeBourse) {
	this.codeBourse = codeBourse;
}
public String getTypeBourse() {
	return typeBourse;
}
public void setTypeBourse(String typeBourse) {
	this.typeBourse = typeBourse;
}
public Double getReduction() {
	return reduction;
}
public void setReduction(Double reduction) {
	this.reduction = reduction;
}
public Eleves getEleves() {
	return eleves;
}
public void setEleves(Eleves eleves) {
	this.eleves = eleves;
}
public String getModalite() {
	return modalite;
}
public void setModalite(String modalite) {
	this.modalite = modalite;
}
public float getCoutModalite() {
	return coutModalite;
}
public void setCoutModalite(float coutModalite) {
	this.coutModalite = coutModalite;
}



}
