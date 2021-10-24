package com.pathlight.hti_school.web;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.pathlight.hti_school.entities.AnneeAcademique;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Doc;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.Moyenne;
import com.pathlight.hti_school.entities.Personne;
import com.pathlight.hti_school.entities.Presence;
import com.pathlight.hti_school.entities.User;
import com.pathlight.hti_school.repository.AnneeAcadRepository;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.ElevesRepository;
import com.pathlight.hti_school.repository.MoyenneRepository;

import com.pathlight.hti_school.service.DocStorageService;




@Controller
public class ControllerEleves {
	@Autowired
	private ClasseRepository classRep;
	@Autowired
	private AnneeAcadRepository AnneeRepo;
	@Autowired
	private ElevesRepository elevesRepo;
	
	
	
	
	
	@Autowired
	private MoyenneRepository moyRepo;
	
	@Autowired 
	private DocStorageService docStorageService;
	
	@Value("${dir.photoEleve}")
	private String imageDirEl;

	
	  @RequestMapping("inscription") public ModelAndView viewNewInscrit(Model model) {
	  List<AnneeAcademique> listeAnnee=AnneeRepo.findAll(); 
	  List<Classe> listeClasse=classRep.findAll();
	  
	  
	  model.addAttribute("listeAnnee",listeAnnee);
	  model.addAttribute("listeClasse",listeClasse); 
	  model.addAttribute("inscrit", new Eleves()); 
	  return new ModelAndView ("Inscription/NewStudent", "genererCode1" ,genererCode1());  
	  }
	  
	
	@PostMapping("/saveEleves") public String saveEleves(@Valid Eleves ele, BindingResult b,
			@RequestParam(name="photos")MultipartFile file, RedirectAttributes redirAttrs,Model model) throws Exception {
		
		if (b.hasErrors()) {
			return "inscription/NewStudent";
		}
		
		/*
		 * if (b.hasErrors()) { redirAttrs.addFlashAttribute("error", "The error ");
		 * return "inscription"; }
		 * 
		 * if (service.isUserPresent(ele.getEmailM())) { model.addAttribute("exist",
		 * true); return "Inscription/NewStudent"; }
		 */
		
		
		 
		 if(!(file.isEmpty())) {
			  ele.setPhoto(file.getOriginalFilename());  
		  }
		 //date 
		 ele.setDateInsc(new Date());
		 //date
		 elevesRepo.save(ele);
		  
		  if(!(file.isEmpty())) {
			  ele.setPhoto(file.getOriginalFilename());
			  file.transferTo(new File(imageDirEl+ele.getIdPerson()));
		  }
		 
		  redirAttrs.addFlashAttribute("success",true);
			return "redirect:listeEl";
		
	  
	}
	
	
	
	@PostMapping("/updateEleves") public String updateEleves(@Valid Eleves ele, BindingResult bindingResult,
			@RequestParam(name="photos")MultipartFile file, RedirectAttributes red) throws Exception {
		 
		 if(!(file.isEmpty())) {
			  ele.setPhoto(file.getOriginalFilename());  
		  }
		 //date 
		 ele.setDateInsc(new Date());
		 //date
		 elevesRepo.save(ele);
		  
		  if(!(file.isEmpty())) {
			  ele.setPhoto(file.getOriginalFilename());
			  file.transferTo(new File(imageDirEl+ele.getIdPerson()));
		  }
		 
		
			return "redirect:listeEl";
		
	  
	}
	
	
	
	
	@RequestMapping(value = "/getPhotoEl", produces = MediaType.IMAGE_JPEG_VALUE)
	  @ResponseBody public byte[] getPhoto(Long idPerson) throws Exception { 
		  File f = new File( imageDirEl+ idPerson); 
	return IOUtils.toByteArray(new FileInputStream(f));
	  
	  }
	
	
	
	
	 
	@GetMapping("listInscrit")
	public String viewListInscrit(Model model) {
		List<Eleves> listEleves= elevesRepo.findAll();
		  model.addAttribute("listEleves",listEleves); 
		return "Inscription/ListerInscrit";
	}
	
	//generer code automatique
	
	  public String genererCode1(){ 
	String code; String 
	codeMax=elevesRepo.max();
	  if(codeMax !=null) { 
	String rnno=codeMax; 
	int co=rnno.length(); String
	  text=rnno.substring(0, 10);
	String num=rnno.substring(10, co);
	  int n=Integer.parseInt(num); 
	  n++; 
	  String snum=Integer.toString(n); 
	  String
	  ftx=text+snum; code=ftx; 
	  } else {
		  code="679078002-10000";
		  }
	  
	  return code; 
	  }
	 
	

	    @GetMapping("/editeEleves")
		public String edit(Model model, Long idPerson) {
	    	Eleves eleves=elevesRepo.findById(idPerson).get();
	    	model.addAttribute("inscrit", eleves);
	     
	    	List<AnneeAcademique> listeAnnee=AnneeRepo.findAll(); 
	   	  List<Classe> listeClasse=classRep.findAll();
	   	  
	   
	   	  
	   	  model.addAttribute("listeAnnee",listeAnnee);
	   	  model.addAttribute("listeClasse",listeClasse);  
	    	return "Inscription/EditerInscrit";
			
		}
	 
	
		
	  
	  @GetMapping("/deleteEleves") 
	  public String delete(Model model, Long idPerson) {
	elevesRepo.deleteById(idPerson); 
	  return "redirect:listInscrit";
	  
	  }
	  
	  /* partie frontend nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn*/
	  @GetMapping("/onlineEnrolled")
	  public ModelAndView inscFNew(Long idEcole, Model model) {
		 
		  List<Classe> listeClasse = classRep.findAll();
		  model.addAttribute("listeClasse", listeClasse);
		  
		  List<AnneeAcademique> listeAnnee = AnneeRepo.findAll();
		  model.addAttribute("listeAnnee", listeAnnee);
		  
		  model.addAttribute("eleves", new Eleves());		  
		  
		  return  new ModelAndView ("InscriptionEnLigne/InscriptionForm", "genererCode1" ,genererCode1());
		  
		  }
	  
		
		@PostMapping("/saveInscriEnLigne") public String saveEleves1(@Valid Eleves elev,
		  BindingResult bindingResult,		  
		  @RequestParam(name="photos")MultipartFile file) throws Exception {	  		  
		  
		  if(!(file.isEmpty())) { elev.setPhoto(file.getOriginalFilename()); 
		  }	
		//date 
		elev.setDateInsc(new Date());
		//date
		  elevesRepo.save(elev);
		  
		  if(!(file.isEmpty())) {
			  elev.setPhoto(file.getOriginalFilename());
		  file.transferTo(new File(imageDirEl+elev.getIdPerson()));
		  }		  
		  return "redirect:/registerForm"; 
		  
		}
		
		@RequestMapping("/findEcole}")
		public ModelAndView findEcole(Long idEcole, Model model) {
			//ecole par id
			 
		    	
		    	  List<Classe> listeClasse = classRep.findAll();
				  
				  List<AnneeAcademique> listeAnnee = AnneeRepo.findAll();
				  model.addAttribute("listeAnnee", listeAnnee);
				  
				  model.addAttribute("eleves", new Eleves());
				  model.addAttribute("listeClasse", listeClasse);
				  
				  
		    	
		    	model.addAttribute("inscrit", new Eleves());
			 return new ModelAndView ("InscriptionEnLigne/InscriptionForm", "genererCode1" ,genererCode1());
		}
		
		@GetMapping("/CSinscrireF")
		  public String logC(Model model) {  			  
			  return  ("InscriptionEnLigne/CSinscrire");
			  
			  }
		
		
	@GetMapping("/index")
	public String viewHome() {
		 
		return ("FontEnd/index");
	}
	  
		
	  //login frontend
		@GetMapping("/login")
		  public String log(Model model) {  			  
			  return  ("FontEnd/index");
			  
			  }
		
		@GetMapping("/logoutF")
		public String logout(HttpServletRequest request)throws Exception {
			request.logout();
			return "redirect:/loginF"; 
			}
		
		 //registrer frontend
		@GetMapping("/registerForm")
		  public String reg(Model model) { 
			model.addAttribute("user", new User());
			  return  ("InscriptionEnLigne/registrerFrontend");
			  
			  }
		//page dattente
		@GetMapping("/attente")
		public String ate() {		
			 return  "InscriptionEnLigne/Attente"; 
			}
		
		//registrer cours frontend
				@GetMapping("/registerCours")
				  public String cours(Model model) { 
					List<Doc> docs = docStorageService.getFiles();
					model.addAttribute("docs", docs);
					  return  ("InscriptionEnLigne/cours");
					  
					  }
				
	/* fin  nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn*/		
				@GetMapping("/Details")
				public String edite(Model model, Long idPerson) {
			    	Eleves inscription=elevesRepo.findById(idPerson).get();
			    	model.addAttribute("inscription", inscription); 
				  return "Inscription/formDetails"; }
				
				
	
	 /*lister eleves par classe nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn*/	
	@RequestMapping("/listeEl")
	public String ListeEl(Model model) {
		  //classe
		 List<Classe> classe=classRep.findAll();
		  model.addAttribute("listeClasse",classe);	  
		
		return "Inscription/LsitElParClasse";
	}
				 
				 //Retourne par classe
	//liste  par classe  
	  @RequestMapping("findByClasseEl") 
	  public String listEl(Model mod, Long
	  idClasse, Eleves el,Long idPerson) { 
		  //liste eleves par classe 
	  Classe c=new Classe();
	  c.setIdClasse(idClasse);
	  mod.addAttribute("listeEleve", elevesRepo.findByClasse(c)); 
	  
	//classe	  par id 
	  Classe cl=classRep.findById(idClasse).get(); 
	  mod.addAttribute("clsEl",cl);
	  
	  //liste classe
	  List<Classe> classe=classRep.findAll(); 
	  mod.addAttribute("listeClasse",classe); 
	   
	  return "Inscription/LsitElParClasse"; }			
	  /*fin  lister eleves par classe nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn*/
				
	/*lister de formation nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn*/	
	@RequestMapping("/listeDeFormationEl")
	public String viewListe(Model model) {
		  //classe
		 List<Classe> classe=classRep.findAll();
		  model.addAttribute("listeClasse",classe);	  
		
		return "GestionFormation/ListeDeFormation";
	}
				 
				 //Retourne par classe
	//liste  par classe  
	  @RequestMapping("findByClasseForm") 
	  public String listEleve(Model mod, Long
	  idClasse, Eleves el,Long idPerson) { 
		  //liste eleves par classe 
	  Classe c=new Classe();
	  c.setIdClasse(idClasse);
	  mod.addAttribute("listeFormt", elevesRepo.findByClasse(c)); 
	  
	//classe	  par id 
	  Classe cl=classRep.findById(idClasse).get(); 
	  mod.addAttribute("clsF",cl);
	  
	  //liste classe
	  List<Classe> classe=classRep.findAll(); 
	  mod.addAttribute("listeClasse",classe); 
	   
	  return "GestionFormation/ListeDeFormation"; }
	  
	  
	  /*lister de de decision nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn */	
		@RequestMapping("/listeDeCesion")
		public String viewListeD(Model model) {
			  //classe
			 List<Classe> classe=classRep.findAll();
			  model.addAttribute("listeClasse",classe);	  
			
			return "GestionFormation/ListeDeDecision";
		}
					 
					 //Retourne par classe
		//liste  par classe  
		  @RequestMapping("findByClasseDecision") 
		  public String listEleveD(@RequestParam(name="idClasse", defaultValue="")Long idClasse1,Model mod, Long
		  idClasse, Eleves el,Long idPerson) { 
			  //liste eleves par classe 
		  Classe c=new Classe();
		  c.setIdClasse(idClasse);
		  mod.addAttribute("listeDec", elevesRepo.findByClasse(c)); 
		  
		//classe	  par id 
		  Classe cl=classRep.findById(idClasse).get(); 
		  mod.addAttribute("clsD",cl);
		  
		  //liste classe
		  List<Classe> classe=classRep.findAll(); 
		  mod.addAttribute("listeClasse",classe); 
		  
		  
		   //liste moyenne
		      List<Moyenne> listMoyenne=moyRepo.searchMoyByCodeEleves(idClasse1);
			  mod.addAttribute("MoP",listMoyenne);
		   
		   
		  return "GestionFormation/ListeDeDecision"; }
				 

	
	 
}
