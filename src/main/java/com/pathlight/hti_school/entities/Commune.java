package com.pathlight.hti_school.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Commune {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@ManyToOne
	@JoinColumn(name="dept_id")
	private Departement departement;
	
	public Commune() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Commune(String name) {
		super();
		this.name = name;
	}
	
	public Commune(String name, Departement departement) {
		super();
		this.name = name;
		this.departement = departement;
	}
	
	
	
	public Commune(Integer id) {
		super();
		this.id = id;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	

}
