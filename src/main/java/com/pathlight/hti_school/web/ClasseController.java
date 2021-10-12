package com.pathlight.hti_school.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.pathlight.hti_school.entities.Classe;

import com.pathlight.hti_school.repository.ClasseRepository;

@Controller
public class ClasseController {
	
	@Autowired
	private ClasseRepository classRep;
	
	
	
	  @RequestMapping("/classes") public ModelAndView ajouter(Model model) {
	 
	  //classe
	  List<Classe> classe1=classRep.findAll();
	  model.addAttribute("listeClasse",classe1);
	  model.addAttribute("classe", new Classe()); 
	  
	  return new ModelAndView ("GestionClasse/ClasseForm", "genererCode1" , genererCode1()); 
	  }
	  
	  
	  @RequestMapping("/ListerClasses") public String listeCat(Model model) {
		  List<Classe> classe1=classRep.findAll();
		  model.addAttribute("listeClasse",classe1);
		  return "GestionClasse/ClasseList"; 
	  }
	  
	 
	  @RequestMapping(value="/saveClasse") 
	  public String save(@Valid Classe classe, BindingResult b,RedirectAttributes redirAttrs, Model model) { 
		  if (b.hasErrors()) {
		        redirAttrs.addFlashAttribute("error", "The error ");
		        return "GestionEtablissement/ClasseForm";
		    }
	
	   classRep.save(classe);
	   redirAttrs.addFlashAttribute("success",true);
	  return "redirect:/ListerClasses"; 
	  }
	  
	  
	  @GetMapping("/editerClasse")
	  public String edit(Model model, Long idClasse) {
		  Classe classe=classRep.findById(idClasse).get();
		  model.addAttribute("classe",classe);
	  return "GestionClasse/EditForm";
	  
	  }
	    
	  
	  @GetMapping("/deleteClasse") 
	  public String delete(Model model, Long idClasse){
		  classRep.deleteById(idClasse);
	  return "redirect:/ListerClasses";
	  
	  }
	  
	  
		//generer code automatique
		public String genererCode1() {
			String code;
			String codeMax=classRep.maxClasse();
			if(codeMax !=null) {
				String rnno=codeMax;
				int co=rnno.length();
				String text=rnno.substring(0, 10);
				String num=rnno.substring(10, co);
				int n=Integer.parseInt(num);
				n++;
				String snum=Integer.toString(n);
				String ftx=text+snum;
				code=ftx;
			}
			else {
				code="787890233-10000";
			}
			return code;
		}
		
		 
}
