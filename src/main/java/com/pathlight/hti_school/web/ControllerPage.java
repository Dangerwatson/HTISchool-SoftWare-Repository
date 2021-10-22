package com.pathlight.hti_school.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.Notifications;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.ElevesRepository;
import com.pathlight.hti_school.repository.NotificationRepository;
import com.pathlight.hti_school.repository.ProfesseurRepository;

@Controller
public class ControllerPage {
	@Autowired
	private ElevesRepository elevesRepository;
	
	@Autowired
	private ClasseRepository classeRepository;
	
	@Autowired
	private ProfesseurRepository professeurRepository;
	
	@Autowired
	private NotificationRepository NotificationRepo;
	
	
	
	
	@GetMapping(value = { "", "/", "index"})
	public String viewHome(Model model) {
		 
		return "index";
	}
	
	
	
	@GetMapping("HTISchool")
	public String viewHome1(Model mod, Long clas) {
		mod.addAttribute("countEl", elevesRepository.countEleve());
		
		mod.addAttribute("countClasse", classeRepository.countClasse());
		
		mod.addAttribute("countProf", professeurRepository.countProf());
		
		
		/*
		 * List<Classe> countEleveByClasse=classeRepository.countEleveByClasse();
		 * mod.addAttribute("countELeveByClasse", countEleveByClasse);
		 */
		  List<Notifications> listeNotif=NotificationRepo.findAll();
		  mod.addAttribute("listeNotif",listeNotif);
		
		 List<Classe> classe=classeRepository.findAll();
		  mod.addAttribute("listeClasse",classe);
		return "Corp";
	}
	
	
	public Long count() {
		Long nbre;
		nbre= elevesRepository.countEleve();
		return nbre;
	}
	
	

}
