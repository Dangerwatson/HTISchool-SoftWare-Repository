package com.pathlight.hti_school.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pathlight.hti_school.entities.AnneeAcademique;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.Matiere;
import com.pathlight.hti_school.entities.ModelSaveNote;
import com.pathlight.hti_school.entities.ModelSavePresence;
import com.pathlight.hti_school.entities.Presence;
import com.pathlight.hti_school.entities.ReleveNote;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.ElevesRepository;
import com.pathlight.hti_school.repository.PresenceRepository;

@Controller
public class ControllerPresence {
	
	@Autowired
	private ClasseRepository classRep;
	
	@Autowired
	private ElevesRepository elevesRepo;
	
	@Autowired
	private PresenceRepository PresenceRep;

	/*ajouter presence/absence */	
	@RequestMapping("/AjouterPresence")
	public String viewPre(Model model) {
		  //classe
		  List<Classe> classe=classRep.findAll();
		  model.addAttribute("listeClasse",classe);
		return "GPresence/presence";
	}
	
     /*lister presence/absence */	
	@RequestMapping("/listepresence")
	public String viewListe(Model model) {
		  //classe
		 List<Classe> classe=classRep.findAll();
		  model.addAttribute("listeClasse",classe);	  
		
		return "GPresence/ListerPresence";
	}
	
	
	//Retourne par classe
	//ajouter presence et absence 
	  @RequestMapping("findByClasseP") 
	  public String listEleves(Model mod, Long
	  idClasse, Eleves el) { 
		  //liste eleves par classe 
	  Classe c=new Classe();
	  c.setIdClasse(idClasse);
	  mod.addAttribute("listep", elevesRepo.findByClasse(c)); 
	  //liste classe
	  List<Classe> classe=classRep.findAll(); 
	  mod.addAttribute("listeClasse",classe); 
	  //classe	  par id 
	  Classe cl=classRep.findById(idClasse).get(); 
	  mod.addAttribute("cls",cl);
	  
	  return "GPresence/presence"; }
	  
	  
	  
//Retourne par classe
	//liste presence et absence par classe  
	  @RequestMapping("findByClassePA") 
	  public String listEleve(Model mod, Long
	  idClasse, Eleves el, Long idPresence) { 
		  //liste eleves par classe 
	  Classe c=new Classe();
	  c.setIdClasse(idClasse);
	  mod.addAttribute("listePA", elevesRepo.findByClasse(c)); 
	  
	  //liste presence par classe 
	  Classe p=new Classe();
	  p.setIdClasse(idClasse);
	  mod.addAttribute("pre", PresenceRep.findByClasseP(p));
	  
	//liste presence
	  List<Presence> listPre=PresenceRep.findAll();
	  mod.addAttribute("listPre",listPre);	  
	
	//classe	  par id 
	  Classe cl=classRep.findById(idClasse).get(); 
	  mod.addAttribute("cls",cl);
	  
	  //liste classe
	  List<Classe> classe=classRep.findAll(); 
	  mod.addAttribute("listeClasse",classe); 
	    
	  return "GPresence/ListerPresence"; }
	  
	  
	 
	  
	 //save multiple Presence
	    @RequestMapping(value="/savePresence", method=RequestMethod.POST)
	  public String savePres(@ModelAttribute ModelSavePresence pres) throws ParseException{		 
		  
           ModelSavePresence p=new ModelSavePresence();
		  	
		  	p.setPresAbs(pres.getPresAbs());
		  	p.setIdPerson(pres.getIdPerson());
		  	p.setIdClasse(pres.getIdClasse()); 			  	
         
		  	 
	         String[] pre = p.getPresAbs().split(",");
	         String [] pers=p.getIdPerson().split(",");
	         String[] classe = p.getIdClasse().split(",");        
         
	        
	         
          List<Presence> tempList = new ArrayList<>();
          for(int i = 0 ; i < pre.length; i++) {
          //appel a chaque classe qui derive(reference comme cles etrangere)  
          Presence pel = new Presence();
          Eleves el=new Eleves();
          Classe cl=new Classe();
          
          /*converti de type String en Long en passant par la classe ModelSavePresence */
          //convertion
          el.setIdPerson(Long.parseLong(pers[i]));
          pel.setElevesP(el);
          //convertion
          cl.setIdClasse(Long.parseLong(classe[0]));
          pel.setClasseP(cl);
          //pas de convertion
          pel.setPresAbs(pre[i]);
          
        //date
           pel.setDateAppel(new Date());  
        //date
           tempList.add(pel);
          
          }
       
          PresenceRep.saveAll(tempList);
	      return "redirect:AjouterPresence";
}
	
	
}