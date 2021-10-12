package com.pathlight.hti_school.entities;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DiscriminatorValue("POSTULANT")
public class Eleves extends Personne {
	private String codeEl;
	//@DateTimeFormat(pattern ="yyyy-MM-dd")
	//private Date dateBapteme;
	private String dernierEtablissement;
	private String religion;
	private String dernierClasse;
	private String pieceFournies;
	@ManyToOne
	@JoinColumn(name = "idClasse")
	private Classe classe;
	@ManyToOne
	@JoinColumn(name = "idAcad")
	private AnneeAcademique AnneeAcade;
	
	
	
	
	//INFO PERE
	//private String nomCPere;
	//private String tel1P;
	//private String tel2P;
	//private String emailP;
	//INFO MERE
	//private String nomCMere;
	//private String tel1M;
	//private String tel2M;
	private String emailM;
	//PERS RESPO.
	//private String nomCPersResp;
	private String lienParental;
	//private String tel1Pers;
	//private String tel2Pers;
	//private String emailPers;
	
	//PERSONNE A CONTACTER EN CAS D'URGENCE
	private String contactPers;
	private String tel1ConPers;
	private String tel2ConPers;
	
	private Date dateInsc;
	
	public Eleves() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	


	public Eleves(Long idPerson,String codeEl, String nom, String prenom, String sexe, Date dateNaissance, String telephone,
			String lieuDeNaissance, String adresse, String photo, String email, Date dateBapteme,
			String dernierEtablissement, String religion, String dernierClasse, String pieceFournies, Classe classe,
			AnneeAcademique anneeAcade, String nomCPere, String tel1p, String tel2p, String emailP, String nomCMere,
			String tel1m, String tel2m, String emailM, String nomCPersResp, String lienParental,  String tel1Pers, String tel2Pers,
			String emailPers, String contactPers, String tel1ConPers, String tel2ConPers, Date dateInsc) {
		super(idPerson, nom, prenom, sexe, dateNaissance, telephone, lieuDeNaissance, adresse, photo, email);
		this.codeEl = codeEl;
		//this.dateBapteme = dateBapteme;
		this.dernierEtablissement = dernierEtablissement;
		this.religion = religion;
		this.dernierClasse = dernierClasse;
		this.pieceFournies = pieceFournies;
		this.classe = classe;
		this.AnneeAcade = anneeAcade;
		//this.nomCPere = nomCPere;
		//this.tel1P = tel1p;
		//this.tel2P = tel2p;
		//this.emailP = emailP;
		//this.nomCMere = nomCMere;
		//this.tel1M = tel1m;
		//this.tel2M = tel2m;
		this.emailM = emailM;
		//this.nomCPersResp = nomCPersResp;
		this.lienParental = lienParental;
		//this.tel1Pers = tel1Pers;
		//this.tel2Pers = tel2Pers;
		//this.emailPers = emailPers;
		this.contactPers = contactPers;
		this.tel1ConPers = tel1ConPers;
		this.tel2ConPers = tel2ConPers;
		this.dateInsc = dateInsc;
		
	}

	
	
	public String getCodeEl() {
		return codeEl;
	}

	public void setCodeEl(String codeEl) {
		this.codeEl = codeEl;
	}

	/*
	 * public Date getDateBapteme() { return dateBapteme; }
	 * 
	 * public void setDateBapteme(Date dateBapteme) { this.dateBapteme =
	 * dateBapteme; }
	 */
	public String getDernierEtablissement() {
		return dernierEtablissement;
	}

	public void setDernierEtablissement(String dernierEtablissement) {
		this.dernierEtablissement = dernierEtablissement;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getDernierClasse() {
		return dernierClasse;
	}

	public void setDernierClasse(String dernierClasse) {
		this.dernierClasse = dernierClasse;
	}

	public String getPieceFournies() {
		return pieceFournies;
	}

	public void setPieceFournies(String pieceFournies) {
		this.pieceFournies = pieceFournies;
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
/*
	public String getNomCPere() {
		return nomCPere;
	}

	public void setNomCPere(String nomCPere) {
		this.nomCPere = nomCPere;
	}

	public String getTel1P() {
		return tel1P;
	}

	public void setTel1P(String tel1p) {
		tel1P = tel1p;
	}

	public String getTel2P() {
		return tel2P;
	}

	public void setTel2P(String tel2p) {
		tel2P = tel2p;
	}

	public String getEmailP() {
		return emailP;
	}

	public void setEmailP(String emailP) {
		this.emailP = emailP;
	}

	public String getNomCMere() {
		return nomCMere;
	}

	public void setNomCMere(String nomCMere) {
		this.nomCMere = nomCMere;
	}

	public String getTel1M() {
		return tel1M;
	}

	public void setTel1M(String tel1m) {
		tel1M = tel1m;
	}

	public String getTel2M() {
		return tel2M;
	}

	public void setTel2M(String tel2m) {
		tel2M = tel2m;
	}
*/
	public String getEmailM() {
		return emailM;
	}

	public void setEmailM(String emailM) {
		this.emailM = emailM;
	}

	/*
	 * public String getNomCPersResp() { return nomCPersResp; }
	 * 
	 * public void setNomCPersResp(String nomCPersResp) { this.nomCPersResp =
	 * nomCPersResp; }
	 */

	
	
	public String getLienParental() {
		return lienParental;
	}

	public void setLienParental(String lienParental) {
		this.lienParental = lienParental;
	}
/*
	public String getTel1Pers() {
		return tel1Pers;
	}

	public void setTel1Pers(String tel1Pers) {
		this.tel1Pers = tel1Pers;
	}

	public String getTel2Pers() {
		return tel2Pers;
	}

	public void setTel2Pers(String tel2Pers) {
		this.tel2Pers = tel2Pers;
	}

	public String getEmailPers() {
		return emailPers;
	}

	public void setEmailPers(String emailPers) {
		this.emailPers = emailPers;
	}
*/
	public String getContactPers() {
		return contactPers;
	}

	public void setContactPers(String contactPers) {
		this.contactPers = contactPers;
	}

	public String getTel1ConPers() {
		return tel1ConPers;
	}

	public void setTel1ConPers(String tel1ConPers) {
		this.tel1ConPers = tel1ConPers;
	}

	public String getTel2ConPers() {
		return tel2ConPers;
	}

	public void setTel2ConPers(String tel2ConPers) {
		this.tel2ConPers = tel2ConPers;
	}

	public Date getDateInsc() {
		return dateInsc;
	}

	public void setDateInsc(Date dateInsc) {
		this.dateInsc = dateInsc;
	}

	

	

	
	

}
