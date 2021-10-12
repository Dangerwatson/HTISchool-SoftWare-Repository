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
import javax.persistence.Table;

@Entity
@Table(name="docs")
public class Doc {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String docName;
	private String docType;
	private String coefficient;
	
	private Date datePublication;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "idClasse")
	private Classe classe;
	
	@Lob
	private byte[] data;
	
	
	
	

	public Doc() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Doc(String docName,String coefficient, String docType,  byte[] data) {
		super();
		this.docName = docName;
		this.docType = docType;
		this.data = data;
		this.classe = classe;
		this.coefficient = coefficient;
		
				
	}
	



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
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

	public void setClasse(Classe doc) {
		this.classe = doc;
	}


	public String getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
	}




	public void setCoefficient(Doc doc) {
		// TODO Auto-generated method stub
		
	}



	

	

	
	
	

}
