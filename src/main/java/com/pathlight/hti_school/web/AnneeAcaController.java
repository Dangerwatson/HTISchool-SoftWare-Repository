package com.pathlight.hti_school.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pathlight.hti_school.entities.AnneeAcademique;
import com.pathlight.hti_school.repository.AnneeAcadRepository;

@Controller
public class AnneeAcaController {
	@Autowired
	private AnneeAcadRepository AnneeRepo;
	
	 @RequestMapping("/ListeAnne") 
	  public String listeAnnee(Model model) {
	  List<AnneeAcademique> listeAnnee=AnneeRepo.findAll();
	  model.addAttribute("listeAnnee",listeAnnee); 
	  return "AnneeAca/AnneAca"; }
	 
	 
	 @RequestMapping("/saveAnneAc") 
	  public String save(AnneeAcademique anneeAcademique) { 
		 AnneeRepo.save(anneeAcademique);
		 return "redirect:HTISchool"; 
	  }
	 
	 @GetMapping("/deleteAn") 
	  public String delete(Model model, Long idAcad){ 
		 AnneeRepo.deleteById(idAcad); 
		 return "redirect:ListeAnne";
	  
	  }
}
