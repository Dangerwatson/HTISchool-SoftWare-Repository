package com.pathlight.hti_school.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pathlight.hti_school.entities.AnneeAcademique;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.EmploiDuTemps;
import com.pathlight.hti_school.entities.Matiere;
import com.pathlight.hti_school.entities.ReleveNote;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.EmploiDuTempsRepository;
import com.pathlight.hti_school.repository.MatiereRepository;

@Controller
public class EmploiDuTempsController {
	@Autowired
	private EmploiDuTempsRepository EmploiDuTempsRepo;
	
	@Autowired
	private ClasseRepository classRep;
	
	@Autowired
	private MatiereRepository matiereRep;
	
	 @RequestMapping("/horaire")
	public String viewHoraire(Model model) {
		 List<EmploiDuTemps> listehoraire=EmploiDuTempsRepo.findAll();
		  model.addAttribute("listehoraire",listehoraire);
		return "GestionHoraire/ListeEmploiTemps";
	}
	
	 @RequestMapping("/Ajouterhoraire")
		public String Emploie(Model model, Long idClasse) { 
		//classe
		  List<Classe> classe1=classRep.findAll();
		  model.addAttribute("listeClasse",classe1);
			return "GestionHoraire/EmploiTemps"; 
			}
	 
	 
	 
	 @RequestMapping("/findMatiereByClasse")
		public String findByCalsse(Model model, Long idClasse) { 
		  //liste matiere par classe
		  Classe c1=new Classe();
		  c1.setIdClasse(idClasse);
		  model.addAttribute("listeMat", matiereRep.findByClasse(c1));
		  
		  //classe par id
		  Classe cl=classRep.findById(idClasse).get();
	    	model.addAttribute("cls", cl);
	    	
	    	//classe
			  List<Classe> classe1=classRep.findAll();
			  model.addAttribute("listeClasse",classe1);
			return "GestionHoraire/EmploiTemps"; 
			}
	 
	   @RequestMapping(value="/saveEmploie") 
	  public String save(@Valid EmploiDuTemps Emploi, BindingResult b,RedirectAttributes redirAttrs, Model model) { 
		  if (b.hasErrors()) {
		        redirAttrs.addFlashAttribute("error", "The error ");
		        return "GestionHoraire/EmploiTemps";
		    }
	
		  EmploiDuTempsRepo.save(Emploi);
	   redirAttrs.addFlashAttribute("success",true);
	  return "GestionHoraire/EmploiTemps"; 
	  }
	   
	   
	 //liste horaire par classe			
	 		@RequestMapping("/listeEmploie")
	 		public String viewEmp(Model model) {
	 			  //classe
	 			 List<Classe> classe=classRep.findAll();
	 			  model.addAttribute("listeClasse",classe);				
	 			
	 			return "GestionHoraire/ListeEmploiTemps";
	 		}
	 		
	 		
	 		//Retourne par classe		 
			  @RequestMapping("findHoraireByClasse") 
			  public String listEleve(Model mod, Long idClasse) {
				  
				  //liste horaire par classe 
			  Classe c=new Classe();
			  c.setIdClasse(idClasse);
			  mod.addAttribute("empl", EmploiDuTempsRepo.findByClasse(c));
			  
			 
			  //liste classe
			  List<Classe> classe=classRep.findAll(); 
			  mod.addAttribute("listeClasse",classe); 
			  //classe par id 
			  Classe cl=classRep.findById(idClasse).get(); 
			  mod.addAttribute("emplo",cl);
			  
			  List<EmploiDuTemps> listehoraire=EmploiDuTempsRepo.findAll();
			  mod.addAttribute("listehoraire",listehoraire);
			  
			  
			  
			  return "GestionHoraire/ListeEmploiTemps"; }
		 
	  

}
