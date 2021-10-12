package com.pathlight.hti_school.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class SendDevoir {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String docName;
	private String docType;
	
	private Date datePublication;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "idClasse")
	private Classe classe;
	
	@Lob
	private byte[] data;

	public SendDevoir() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SendDevoir(String docName, String docType, byte[] data) {
		super();
		this.docName = docName;
		this.docType = docType;
		
		this.classe = classe;
		this.data = data;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public Date getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	
	

}
