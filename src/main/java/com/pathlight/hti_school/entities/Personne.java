package com.pathlight.hti_school.entities;



import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import java.beans.Transient;
import java.util.Date;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="status", discriminatorType = DiscriminatorType.STRING)
public abstract class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPerson;
	@NotEmpty(message = "Champs classe ne peut pas être vide")
	@Size(min=2, max=10, message="La taille doit compris entre 2 a 10")
	@Pattern(regexp="[^0-9,]*", message="Only letter")
	private String nom;
	private String prenom;
	private String sexe;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date dateNaissance;
	private String telephone;
	private String lieuDeNaissance;
	private String adresse;
	private String photo;
	private String emailPersonne;
	
	
	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personne(Long idPerson, String nom, String prenom, String sexe, Date dateNaissance, String telephone,
			String lieuDeNaissance, String adresse, String photo, String emailPersonne) {
		super();
		this.idPerson=idPerson;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
		this.lieuDeNaissance = lieuDeNaissance;
		this.adresse = adresse;
		this.photo = photo;
		this.emailPersonne = emailPersonne;
		
	}

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLieuDeNaissance() {
		return lieuDeNaissance;
	}

	public void setLieuDeNaissance(String lieuDeNaissance) {
		this.lieuDeNaissance = lieuDeNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEmailPersonne() {
		return emailPersonne;
	}

	public void setEmailPersonne(String emailPersonne) {
		this.emailPersonne = emailPersonne;
	}

	
	
    
	
	
	
}
