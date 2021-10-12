package com.pathlight.hti_school.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.pathlight.hti_school.entities.CategoryMatiere;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.Matiere;
import com.pathlight.hti_school.repository.CategorieMatiereRepository;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.MatiereRepository;

@Controller

public class MatiereController {
	@Autowired	
	private CategorieMatiereRepository catMatRepo;
	@Autowired
	private ClasseRepository classRep;
	
	Matiere mat=new Matiere();
	
	@Autowired
	private MatiereRepository matiereRep;
	
	@RequestMapping("/listerMatieres") public String listeMatiere(Model model) {
		//Matiere
		  List<Matiere> matiere=matiereRep.findAll();
		  model.addAttribute("listeMatiere",matiere);
		return "GestionMatiere/ListMatiere";
		
	}
	
	@RequestMapping("/AjouterMatieres") public ModelAndView listeCat(Model model) {
		  //CategorieMatiere
		  List<CategoryMatiere> listeCatMat=catMatRepo.findAll();
		  model.addAttribute("listeCatMat",listeCatMat);
		  //classe
		  List<Classe> classe=classRep.findAll();
		  model.addAttribute("listeClasse",classe);
		  //Matiere
		  List<Matiere> matiere=matiereRep.findAll();
		  model.addAttribute("listeMatiere",matiere);
		  model.addAttribute("matiere", new Matiere());
		  return new ModelAndView  ("GestionMatiere/AjFormMatiere"); 
		  }
	  
	  @RequestMapping("/saveMatiere") 
	  public String save(@Valid Matiere matier, BindingResult b,RedirectAttributes redirAttrs) { 
		  if (b.hasErrors()) {
		        redirAttrs.addFlashAttribute("error", "The error ");
		        return "/AjouterMatieres";
		    }
			String codeMat=matier.getNomMatiere();
			String codeMatiere=codeMat.substring(0,3);
			String code=codeMatiere.toUpperCase().trim();
			Long count=matiereRep.countMatiere();
		    String newCode=code+"-"+"1000"+count;
			matier.setCodeMatiere(newCode);
		  matiereRep.save(matier); 
		 redirAttrs.addFlashAttribute("success",true);
	  return "redirect:/listerMatieres"; 
	  }
	  
	  
	  @GetMapping("/deleteMatiere") 
	  public String delete(Model model, Long idMatiere){
	  matiereRep.deleteById(idMatiere);
	  return "redirect:/listerMatieres";
	  
	  }
	  

		
		  @GetMapping("/editerMatiere")
	  public String editM(Model model, Long idMatiere) {
		  Matiere matiere=matiereRep.findById(idMatiere).get();
		  model.addAttribute("matiere",matiere);
	  
	 //classe
		  List<Classe> classe=classRep.findAll();
		  model.addAttribute("listeClasse",classe);
		 
		//CategorieMatiere
		  List<CategoryMatiere> listeCatMat=catMatRepo.findAll();
		  model.addAttribute("listeCatMat",listeCatMat);
	  
	  return "GestionMatiere/EditMatiere";
	  
	  }
		  /*lister matiere par classe nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn*/	
			@RequestMapping("/listeMatiereC")
			public String ListeEl(Model model) {
				  //classe
				 List<Classe> classe=classRep.findAll();
				  model.addAttribute("listeClasse",classe);	  
				
				return "GestionMatiere/LsitMatParClasse";
			}
						 
						 //Retourne par classe
			//liste  par classe  
			  @RequestMapping("findByClasseMat") 
			  public String listM(Model mod, Long
			  idClasse, Long idMatiere) { 
				  //liste Matiere par classe 
			  Classe c=new Classe();
			  c.setIdClasse(idClasse);
			  mod.addAttribute("listeMatiere", matiereRep.findByClasse(c)); 
			  
			//classe par id 
			  Classe cl=classRep.findById(idClasse).get(); 
			  mod.addAttribute("clsMat",cl);
			  
			  //liste classe
			  List<Classe> classe=classRep.findAll(); 
			  mod.addAttribute("listeClasse",classe); 
			   
			  return "GestionMatiere/LsitMatParClasse"; }			
			  /*fin  lister eleves par classe nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn*/
	  	  

}
