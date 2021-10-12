package com.pathlight.hti_school.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmploiDuTemps {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long idEmploi;
	private String debut;
	private String fin;
	@ManyToOne
	@JoinColumn(name = "idClasse")
	private Classe classe;
	private String jour1;
	private String jour2;
	private String jour3;
	private String jour4;
	private String jour5;
	public EmploiDuTemps() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmploiDuTemps(String debut, String fin, Classe classe, String jour1, String jour2, String jour3,
			String jour4, String jour5) {
		super();
		this.debut = debut;
		this.fin = fin;
		this.classe = classe;
		this.jour1 = jour1;
		this.jour2 = jour2;
		this.jour3 = jour3;
		this.jour4 = jour4;
		this.jour5 = jour5;
	}
	public Long getIdEmploi() {
		return idEmploi;
	}
	public void setIdEmploi(Long idEmploi) {
		this.idEmploi = idEmploi;
	}
	public String getDebut() {
		return debut;
	}
	public void setDebut(String debut) {
		this.debut = debut;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public String getJour1() {
		return jour1;
	}
	public void setJour1(String jour1) {
		this.jour1 = jour1;
	}
	public String getJour2() {
		return jour2;
	}
	public void setJour2(String jour2) {
		this.jour2 = jour2;
	}
	public String getJour3() {
		return jour3;
	}
	public void setJour3(String jour3) {
		this.jour3 = jour3;
	}
	public String getJour4() {
		return jour4;
	}
	public void setJour4(String jour4) {
		this.jour4 = jour4;
	}
	public String getJour5() {
		return jour5;
	}
	public void setJour5(String jour5) {
		this.jour5 = jour5;
	}
	
	

}
