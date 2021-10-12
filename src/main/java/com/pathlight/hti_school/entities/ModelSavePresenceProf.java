package com.pathlight.hti_school.entities;

import java.util.Date;

public class ModelSavePresenceProf {
	private String presAbsP ;
	private String idPerson;	
	private String dateAppel;

	public ModelSavePresenceProf() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ModelSavePresenceProf(String presAbsP,String idPerson,String dateAppel) {
		super();
		this.presAbsP = presAbsP;
		this.idPerson = idPerson;		
		this.dateAppel = dateAppel;
	}

	public String getPresAbsP() {
		return presAbsP;
	}

	public void setPresAbsP(String presAbsP) {
		this.presAbsP = presAbsP;
	}

	public String getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(String idPerson) {
		this.idPerson = idPerson;
	}

	
	public String getDateAppel() {
		return dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}
	

	
	
	

}
