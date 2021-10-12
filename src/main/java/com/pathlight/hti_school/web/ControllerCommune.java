package com.pathlight.hti_school.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Commune;
import com.pathlight.hti_school.entities.Departement;
import com.pathlight.hti_school.entities.Matiere;
import com.pathlight.hti_school.repository.CommuneRepository;
import com.pathlight.hti_school.repository.MatiereRepository;

@RestController
public class ControllerCommune {
	@Autowired
	private CommuneRepository repoCom;
	
	@Autowired
	private MatiereRepository mat;
	
	@GetMapping(value = "/loadCommuneByDept/{id}")
	@ResponseBody
	public List<Commune> loadCommuneByDept( @PathVariable ("id") Integer deptId) {
		//Gson gson=new Gson();
		return repoCom.findByDepartement(new Departement(deptId));
	}
	
	@GetMapping(value = "/loadMatiereByClasse/{id}")
	@ResponseBody
	public List<Matiere> loadMatiereByClasse( @PathVariable ("id") Long idClasse) {
		
		//Gson gson=new Gson();
		return mat.findByClasse(new Classe(idClasse));
	}

}
