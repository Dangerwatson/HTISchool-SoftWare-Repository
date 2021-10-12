package com.pathlight.hti_school.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pathlight.hti_school.entities.Departement;
import com.pathlight.hti_school.repository.DepartementRepository;

@RestController
public class ControllerDep {
	@Autowired
	private	DepartementRepository repoDept;
	
    @GetMapping("/loadDept")
    public List<Departement> listDept(){
  	System.out.println("..................");
  	return (List<Departement>) repoDept.findAll();
  }
}
