package com.pathlight.hti_school.web;

import java.util.ArrayList;
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
import com.pathlight.hti_school.entities.ModelSaveMoyenne;
import com.pathlight.hti_school.entities.ModelSaveNote;
import com.pathlight.hti_school.entities.Moyenne;
import com.pathlight.hti_school.entities.ReleveNote;
import com.pathlight.hti_school.repository.AnneeAcadRepository;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.ElevesRepository;
import com.pathlight.hti_school.repository.MatiereRepository;
import com.pathlight.hti_school.repository.MoyenneRepository;
import com.pathlight.hti_school.repository.NotesRepository;

@Controller
public class MoyenneController {
	
	@Autowired
	private ClasseRepository classRep;
	
	@Autowired
	private MatiereRepository matiereRep;
	@Autowired
	private ElevesRepository elevesRepo;
	
	@Autowired
	private AnneeAcadRepository AnneeRepo;
	
	@Autowired
	private MoyenneRepository moyRepo;
	
	@Autowired
	private NotesRepository noteRepo;
	
	
	
	 //liste note par classe			
	@RequestMapping("/calculMoyenne")
	public String viewPre(Model model) {
		  //classe
		 List<Classe> classe=classRep.findAll();
		  model.addAttribute("listeClasse",classe);				
		
		return "GestionExamens/CalculMoyenne";
	}
  
	//Retourne par classe		 
	  @RequestMapping("findCalculByClasse") 
	  public String listEleve(Model mod, Long idClasse, Eleves el, Long idMatiere,Long idRelve,Long idAcad) {
		  
		  //liste eleves par classe 
	  Classe c=new Classe();
	  c.setIdClasse(idClasse);
	  mod.addAttribute("moyen", elevesRepo.findByClasse(c));
	  
	  //liste matiere par classe 
	  Classe ma=new Classe();
	 ma.setIdClasse(idClasse);
	 mod.addAttribute("mat", matiereRep.findByClasse(ma));
	 
	 //liste note par classe 
	 Classe notc=new Classe();
	 notc.setIdClasse(idClasse);
	 mod.addAttribute("notC", noteRepo.findByClasse(notc));		 
	
	
	//Matiere
	  List<Matiere> matiere=matiereRep.findAll();
	  mod.addAttribute("listeMatiere",matiere);
	
	  //liste classe
	  List<Classe> classe=classRep.findAll(); 
	  mod.addAttribute("listeClasse",classe); 
	  //classe par id 
	  Classe cl=classRep.findById(idClasse).get(); 
	  mod.addAttribute("clsM",cl);	  
	 
	  
	//note liste
	  List<ReleveNote> no=noteRepo.findAll();
	  mod.addAttribute("listeNote",no);
	  
	
	  //Annee Academique
	  List<AnneeAcademique> listeAnnee=AnneeRepo.findAll();
	  mod.addAttribute("listAnnee",listeAnnee);
	  
	
	  return "GestionExamens/CalculMoyenne"; }
	  
	  
	  
	  
	   //save multiple moyenne
	  @RequestMapping(value="/saveMoyenne", method=RequestMethod.POST)
	  public String saveMoy(@ModelAttribute ModelSaveMoyenne moye){
		  
		  ModelSaveMoyenne moy=new ModelSaveMoyenne();
		  	
		  	moy.setTotal(moye.getTotal());
		  	moy.setMoyenne(moye.getMoyenne());
		  	moy.setIdClasse(moye.getIdClasse());
		    moy.setMatiere(moye.getMatiere());
		  	moy.setAnneeAcad(moye.getAnneeAcad());
		  	moy.setFrequence(moye.getFrequence());
		  	moy.setIdPerson(moye.getIdPerson());
		  	moy.setReleveNote(moye.getReleveNote());
		  	moy.setMention(moye.getMention());
		  	
		 String[] to = moye.getTotal().split(",");
         String[] note = moye.getMoyenne().split(",");
         String [] pers=moye.getIdPerson().split(",");
         String[] classe = moye.getIdClasse().split(",");
         String[] annee = moye.getAnneeAcad().split(",");
         String[] releve = moye.getReleveNote().split(",");
         String[] matiere = moye.getMatiere().split(",");
         String[] ment=moye.getMention().split(",");
         
         String[] fre = moye.getFrequence().split(",");
        
         
          List<Moyenne> tempList = new ArrayList<>();
          for(int i = 0 ; i < note.length; i++) {
        	  
          Moyenne rel = new Moyenne();          
          //appel a chaque classe qui derive(reference comme cles etrangere)
          Eleves el=new Eleves();
          Classe cl=new Classe();
          AnneeAcademique ann=new AnneeAcademique();
          ReleveNote relev=new ReleveNote();
          Matiere  m=new Matiere();
          
         
          
         
			/*converti de type String en Long en passant par la classe ModelSaveNote */  
           // Matiere
              m.setIdMatiere(Long.parseLong(matiere[0])); 
              rel.setMatiere(m);
          
         
          //Personne
          el.setIdPerson(Long.parseLong(pers[i]));
          rel.setEleves(el);
          
        //Classe
          cl.setIdClasse(Long.parseLong(classe[0]));
          rel.setClasse(cl);
          
          //Annee Acadenique
          ann.setIdAcad(Long.parseLong(annee[0]));
          rel.setAnneeAcad(ann);
          
          //releNote
          relev.setIdRelve(Long.parseLong(releve[i]));
          rel.setReleveNote(relev);
          
          //Moyenne
          rel.setMoyenne(Float.parseFloat(note[i])); 
          
        //total
          rel.setTotal(Float.parseFloat(to[i]));
          
         //Frequence(control ou trimestre)          
          rel.setFrequence(fre[0]);
          
          //mention
          rel.setMention(ment[i]);
          
           tempList.add(rel);
          }
          moyRepo.saveAll(tempList);
	      return "redirect:calculMoyenne";
	      }
	  
	   
	  

}
