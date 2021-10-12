package com.pathlight.hti_school.web;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pathlight.hti_school.entities.CategoryMatiere;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.ModelSavePresence;
import com.pathlight.hti_school.entities.ModelSavePresenceProf;
import com.pathlight.hti_school.entities.Personne;
import com.pathlight.hti_school.entities.Presence;
import com.pathlight.hti_school.entities.PresenceProfesseur;
import com.pathlight.hti_school.entities.Professeurs;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.PresenceRepositoryProf;
import com.pathlight.hti_school.repository.ProfesseurRepository;

@Controller
public class ProfesseursController {
	@Autowired
	private ProfesseurRepository ProfesseursRep;
	
	
	
	@Autowired
	private ClasseRepository classRep;
	
	@Autowired
	private PresenceRepositoryProf PresenceRepositoryP;
	/*
	 * @Value("${dir.photoProfesseur}") private String imageDirProf;
	 */
	@Value("${dir.photoEleve}")
	private String imageDirEl;
	
	@RequestMapping("/listeProfesseurs")
	  public String listprof(Model mod) {		  
		List<Professeurs> listProfesseurs= ProfesseursRep.findAll();
		  mod.addAttribute("listProfesseurs",listProfesseurs); 
		  return "GestionProfesseur/ListeProfesseurs"; 
		  }
	
	 @GetMapping("/ajouterProfesseur") 
	  public ModelAndView listProfs(Model model) {
	  List<Professeurs> listProfesseurs= ProfesseursRep.findAll();
	  model.addAttribute("listProfesseurs",listProfesseurs);  
	  model.addAttribute("professeurs", new Professeurs());
	  
	//liste classe
	  List<Classe> classe1=classRep.findAll();
	  model.addAttribute("listeClasse",classe1);
	  
	
	  return new ModelAndView ("GestionProfesseur/AjouterProfesseur", "genererCode1" ,genererCode1()); 
	  }
	
	 @PostMapping("/saveProf")
     public String saveProf(@Valid Professeurs prof,BindingResult b, @RequestParam(name="photos")MultipartFile file, RedirectAttributes redirAttrs) throws Exception {
		 
		 
		 if (b.hasErrors()) {
		        redirAttrs.addFlashAttribute("error", "The error ");
		        return "ajouterProfesseur";
		    }
		 
		  if(!(file.isEmpty())) {
			  prof.setPhoto(file.getOriginalFilename());  
		  }
		  ProfesseursRep.save(prof);
		  
		  if(!(file.isEmpty())) {
			  prof.setPhoto(file.getOriginalFilename());
			  file.transferTo(new File(imageDirEl+prof.getIdPerson()));
		  }
		  
		  redirAttrs.addFlashAttribute("success",true);
   	  	    return "redirect:/listeProfesseurs";
      }
	 
	 
	 
	 @PostMapping("/updateProf")
     public String updateP(@Valid Professeurs prof,BindingResult b, @RequestParam(name="photos")MultipartFile file, RedirectAttributes redirAttrs) throws Exception {
		 
		 
		 if (b.hasErrors()) {
		        redirAttrs.addFlashAttribute("error", "The error ");
		        return "EditerProfeseurs";
		    }
		 
		  if(!(file.isEmpty())) {
			  prof.setPhoto(file.getOriginalFilename());  
		  }
		  ProfesseursRep.save(prof);
		  
		  if(!(file.isEmpty())) {
			  prof.setPhoto(file.getOriginalFilename());
			  file.transferTo(new File(imageDirEl+prof.getIdPerson()));
		  }
		  
		  redirAttrs.addFlashAttribute("success",true);
   	  	    return "redirect:/listeProfesseurs";
      }
	 
	
	/// @RequestMapping(value = "/getPhotoEl", produces = MediaType.IMAGE_JPEG_VALUE)
	/*
	 * @ResponseBody public byte[] getPhoto(Long idPerson) throws Exception { File f
	 * = new File( imageDirEl+ idPerson); return IOUtils.toByteArray(new
	 * FileInputStream(f));
	 * 
	 * }
	 */
	 
	 
	
	 @GetMapping("/deleteProfesseur") 
	  public String delete(Model model, Long idPerson) {
		 ProfesseursRep.deleteById(idPerson); 
	  return "redirect:/listeProfesseurs";
	  
	  }
	 
	 
	 @GetMapping("/editeProf")
		public String edit(Model model, Long idPerson) {
		 Personne professeur=ProfesseursRep.findById(idPerson).get();
	    	model.addAttribute("professeurs", professeur);
	    	
	    	//liste classe
	  	  List<Classe> classe1=classRep.findAll();
	  	  model.addAttribute("listeClasse",classe1);
	  	  
		 return "GestionProfesseur/EditerProfeseur";
	 }
	 
		//generer code automatique
			public String genererCode1() {
				String code;
				String codeMax=ProfesseursRep.maxProf();
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
					code="787891234-10000";
				}
				return code;
			}
			
			
	/* partie presence et absence professeur */	
			
			@RequestMapping("/AjouterPresenceProf")
			  public String profPre(Model mod) {		  
				  mod.addAttribute("professeurs", new Professeurs());
				  
		          /* liste professeur */
				  List<Professeurs> listProfesseurs= ProfesseursRep.findAll();
				  mod.addAttribute("listProfesseurs",listProfesseurs);
				  return "GPresence/AjPresenceProf"; 
				  }
			
	/* Liste presence professeur */
			 @GetMapping("listepresenceProf") 
			  public String listPresenceProf(Model model) {
			  List<Professeurs> listProfesseurs= ProfesseursRep.findAll();
			  model.addAttribute("listProfesseurs",listProfesseurs); 
			  
			  List<PresenceProfesseur> presenceProfesseur= PresenceRepositoryP.findAll();
			  model.addAttribute("presencePr",presenceProfesseur);
			 
			  return "GPresence/ListerPresenceProf";
			 }
			
			
			
		 
		 //save multiple Presence Professeur
	    @RequestMapping(value="/savePresenceP", method=RequestMethod.POST)
	  public String savePresP(@ModelAttribute ModelSavePresenceProf prof) throws ParseException{		 
		  
           ModelSavePresenceProf pfs=new ModelSavePresenceProf();
		  	
		  	pfs.setPresAbsP(prof.getPresAbsP());
		  	pfs.setIdPerson(prof.getIdPerson());		  	 			  	
         
	         String[] pre = pfs.getPresAbsP().split(",");
	         String [] pers=pfs.getIdPerson().split(",");	                 
         
          List<PresenceProfesseur> tempList = new ArrayList<>();
          for(int i = 0 ; i < pre.length; i++) {
          //appel a chaque classe qui derive(reference comme cles etrangere)  
          PresenceProfesseur pel = new PresenceProfesseur();
          Professeurs el=new Professeurs();         
          
          /*converti de type String en Long en passant par la classe ModelSavePresence */
          //convertion
          el.setIdPerson(Long.parseLong(pers[i]));
          pel.setProfesseur(el);
         
          //pas de convertion
          pel.setPresAbsP(pre[i]);
          
        //date
           pel.setDateAppel(new Date());  
        //date
           tempList.add(pel);
          
          }
       
          PresenceRepositoryP.saveAll(tempList);
	      return "redirect:AjouterPresenceProf";
}
	
	  
	 
	 
	  
		
		
}
