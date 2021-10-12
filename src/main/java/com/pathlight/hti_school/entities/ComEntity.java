package com.pathlight.hti_school.entities;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ComEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private Long idCom;
	private String nameCom;
	public ComEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComEntity(Long idCom, String nameCom) {
		super();
		this.idCom = idCom;
		this.nameCom = nameCom;
	}
	public Long getIdCom() {
		return idCom;
	}
	public void setIdCom(Long idCom) {
		this.idCom = idCom;
	}
	public String getNameCom() {
		return nameCom;
	}
	public void setNameCom(String nameCom) {
		this.nameCom = nameCom;
	}


}
