package com.pathlight.hti_school.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pathlight.hti_school.entities.AddCatMatiere;

import com.pathlight.hti_school.entities.CategoryMatiere;
import com.pathlight.hti_school.entities.Notifications;
import com.pathlight.hti_school.repository.CategorieMatiereRepository;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.NotificationRepository;

@Controller

public class CategorieMatiere {
@Autowired	
private CategorieMatiereRepository catMatRepo;
@Autowired
private ClasseRepository classRep;

@Autowired
private NotificationRepository NotificationRepo;

	
	  @GetMapping("liste_cate_matiere") public String listeCat(Model model) {
		  List<CategoryMatiere> listeCatMat=catMatRepo.findAll();
		  model.addAttribute("listeCatMat",listeCatMat); 
		  
		  List<Notifications> listeNotif=NotificationRepo.findAll();
		  model.addAttribute("listeNotif",listeNotif);
		  return "GestionMatiere/ListCatMatiere"; 
		  }
	  

	  @RequestMapping("/cate_matiere") public String formCat(Model model) {
	  List<CategoryMatiere> listeCat=catMatRepo.findAll();
	  model.addAttribute("listeCat",listeCat); 
	  List<Notifications> listeNotif=NotificationRepo.findAll();
	  model.addAttribute("listeNotif",listeNotif);
	  return "GestionMatiere/categorieMatiere"; 
	  }
	
	
	  
	
	  @RequestMapping(value = "/saveCatMat", method=RequestMethod.POST)
		public String saveCate(@ModelAttribute AddCatMatiere addCatMat, Model mod,BindingResult b,RedirectAttributes redirAttrs) {
		  if (b.hasErrors()) {
		        redirAttrs.addFlashAttribute("error", "The error ");
		        return "/cate_matiere";
		    }
		  AddCatMatiere add=new AddCatMatiere();
			add.setNomCatMatiere(addCatMat.getNomCatMatiere());
			
			String[] nomCatMat=add.getNomCatMatiere().split(",");
			
			List<CategoryMatiere> list=new ArrayList<>();
			
			for(int i=0;i<nomCatMat.length;i++) {
				CategoryMatiere cat=new CategoryMatiere();				
				
				cat.setNomCatMatiere(nomCatMat[i]);
				list.add(cat);
			}
			
			catMatRepo.saveAll(list);
			redirAttrs.addFlashAttribute("success", true);
			return "redirect:/liste_cate_matiere";
		}
	  
	  @GetMapping("/EditCatMatiere")	 
	  public String EditCatMatiere (Model model,Long idCatMatiere){
		  CategoryMatiere categoryMatiere = catMatRepo.findById(idCatMatiere).get(); 
		  model.addAttribute("categoryMatiere", categoryMatiere);
		return "GestionMatiere/EditCatMatiere";
	  }
	  
	  @GetMapping("/deleteCatMat") 
	  public String delete(Model model, Long idCatMatiere){
	  catMatRepo.deleteById(idCatMatiere); 
	  return "redirect:/liste_cate_matiere";
	  
	  }
}
