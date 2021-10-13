package com.pathlight.hti_school.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pathlight.hti_school.entities.AnneeAcademique;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.GestBourse;
import com.pathlight.hti_school.entities.LignePaiement;

import com.pathlight.hti_school.entities.Paiement;
import com.pathlight.hti_school.repository.AnneeAcadRepository;
import com.pathlight.hti_school.repository.BourseRepository;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.ElevesRepository;
import com.pathlight.hti_school.repository.LignePaiementRepository;

import com.pathlight.hti_school.repository.PaiementRepository;
import com.pathlight.hti_school.security.CustomUserDetails;

@Controller
public class PaiementController {
	
	@Autowired
	private PaiementRepository paiementRepo;
	
	@Autowired
	private ElevesRepository elevesRepo;
	
	
	
	@Autowired
	private BourseRepository bourseRepo;
	
	@Autowired
	private ClasseRepository classRep;
	
	@Autowired
	private LignePaiementRepository lignePaiementRepo;
	
	@Autowired
	private AnneeAcadRepository AnneeRepo;
	
	@RequestMapping("/paiement")
	public String ListPaiement(Model mod){
		List<Eleves> listEleves= elevesRepo.findAll();
		  mod.addAttribute("listEleves",listEleves);
		  
		  
		  
		  List<Paiement> paiement=paiementRepo.findAll();
		  mod.addAttribute("paiement",paiement);
    return "GestionPaiement/ListePaiement";
	}
	
	@RequestMapping("/fich_paiement_print")
	public String PrintPaiement(Model mod){
		mod.addAttribute("paiementInfo", paiementRepo.maxIdPaie());
    return "GestionPaiement/fiche_paiement_print.html";
	}
	
	  @RequestMapping("/savePaiement") 
	  public String save(Paiement paiement, Paiement paiement1, @RequestParam("eleves") Eleves eleves, LignePaiement lignePaie, LignePaiement lignePaie1, RedirectAttributes redirAttrs) throws Exception{
		Optional<Paiement> eleve=paiementRepo.findByEleves(eleves);
		float bal=paiement.getBalance();
		float qte=lignePaie.getQuantiteVerser();
			  if(eleve.isPresent()) {
			 paiementRepo.save(paiement);  
			  Paiement paie=paiementRepo.findByEleves(eleves).get();
				float oldBal=paiement.getBalance();
				float newBal=oldBal+paie.getBalance();
				paie.setBalance(newBal);
				paiementRepo.save(paie);
				
				 //lignePaiement
				 lignePaie1.setPaiement(paie);
				 lignePaie1.setDatePaie(new Date());
				lignePaiementRepo.save(lignePaie1);
			 return "redirect:/fich_paiement_print";
			 }
		  
		  else {
			  
				 //lignePaiement
				 lignePaie.setPaiement(paiement);
				 lignePaie.setBalance(bal);
				 lignePaie.setEleves(eleves);
				 lignePaie.setQuantiteVerser(qte);
				 lignePaie.setDatePaie(new Date());
				 lignePaiementRepo.save(lignePaie);
			  }
		 
	  return "redirect:/fich_paiement_print"; 
	  }
	  
	  @RequestMapping("/formPaiement") 
	  public String formPaiement(Long idEcole, Model mod) {
		 
	  return "GestionPaiement/PaiementForm"; 
	  }
	  
	  
	  @RequestMapping("/list_paiement_by_classe") 
	  public String listPaiementByClasse(Long idEcole, Model mod) {
		  List<Classe> listeClasse=classRep.findAll();
		  mod.addAttribute("listeClasse",listeClasse); 
	  return "GestionPaiement/list_par_classe"; 
	  }
	  
	  
	  @RequestMapping("/findByCode") 
	  //passe le name de l'input en parametre avec RequestParam
	  public ModelAndView infoEleves(@RequestParam(name="codeElev", defaultValue="")String codeEl, Model mod, Long idEcole, Authentication authentication, RedirectAttributes redirAttrs, Eleves ele) throws Exception{
		  //Recuperation du mot clé
		
		 Eleves el=paiementRepo.searchByCode("%"+codeEl+"%");
		  mod.addAttribute("eleves", el);
		  
		  
		  List<GestBourse> bourse=paiementRepo.searchBourseByCodeEleves("%"+codeEl+"%");
		  mod.addAttribute("bourse", bourse);
		  //Pour que la valeur rest afficher dans la zone de texte
		 mod.addAttribute("codeEleves", codeEl);
		
		 
		 
		 Paiement p=paiementRepo.searchByBalCode("%"+codeEl+"%");
		  mod.addAttribute("paiement", p);
		 
		 // mod.addAttribute("annee", paiementRepo.maxAnnee());
		  
		  
		  mod.addAttribute("annee", AnneeRepo.findByOrderByAnneeACDesc());
		  
		  mod.addAttribute("lignePaiement", paiementRepo.listInfoBalCode("%"+codeEl+"%"));
		  
		  mod.addAttribute("lPaiement", paiementRepo.listInfoBalCode("%"+codeEl+"%"));
		  
		  mod.addAttribute("lPaie", new LignePaiement());
	  return new ModelAndView("GestionPaiement/PaiementForm", "genererCode1" ,genererCode1());  
	  }
	  
	  public String genererCode1(){ 
	String code; String 
	codeMax=paiementRepo.maxCodePaie();
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
	  
	  
	  @RequestMapping("/saveBal") 
	  public String saveBal(Paiement paiement, Paiement paiement1, @RequestParam("eleves") Eleves eleves, LignePaiement lignePaie, LignePaiement lignePaie1) {
		Optional<Paiement> eleve=paiementRepo.findByEleves(eleves);
		float bal=paiement.getBalance();
		float qte=lignePaie.getQuantiteVerser();
		 		  if(eleve.isPresent()){
	
				Paiement paie=paiementRepo.findByEleves(eleves).get();
				float oldBal=lignePaie1.getQuantiteVerser();
				float newBal=paie.getBalance()-oldBal;
				paie.setBalance(newBal);
				paiementRepo.save(paie);
				
				 //lignePaiement
				String codeP=lignePaie.getCodePaiement();
				lignePaie1.setDatePaie(new Date());
				 lignePaie1.setPaiement(paie);
				 lignePaie1.setCodePaiement(codeP);
				 lignePaie1.setModalite("Solde dû");
				lignePaiementRepo.save(lignePaie1);
			 }
		  
		  else {
				 paiementRepo.save(paiement); 
				 
				 //lignePaiement
				 lignePaie.setPaiement(paiement);
				 lignePaie.setBalance(bal);
				 lignePaie.setEleves(eleves);
				 lignePaie.setQuantiteVerser(qte);
				 lignePaie.setDatePaie(new Date());
				 lignePaiementRepo.save(lignePaie);
				 return "redirect:/fich_paiement_print";
			  }
		 
		  return "redirect:/fich_paiement_print";
	  }
	  
	  @RequestMapping("/find_paiement_by_classe") 
	  public String findByClasse(Long idEcole, Model mod, long idClasse) {
		  List<Classe> listeClasse=classRep.findAll();
		  mod.addAttribute("listeClasse",listeClasse);
		  
		  List<AnneeAcademique> listeAnnee=AnneeRepo.findAll();
		  mod.addAttribute("listeAnnee",listeAnnee);
		  
		  Classe c1=new Classe();
		  c1.setIdClasse(idClasse);
		  
		  Long idAcad=paiementRepo.maxAnnee();
		  AnneeAcademique anne=new AnneeAcademique();
		  anne.setIdAcad(idAcad);
		  
		  Classe classe=new Classe();
		  classe.setIdClasse(idClasse);
		  mod.addAttribute("lignePaiement", lignePaiementRepo.findByClasse(classe));
		  
	  return "GestionPaiement/list_par_classe"; 
	  }
	  
	  @RequestMapping("/find_paiement_by_annee") 
	  public String findByAnnee(Long idEcole, Model mod, long idClasse, long idAcad) {
		  List<Classe> listeClasse=classRep.findAll();
		  mod.addAttribute("listeClasse",listeClasse);
		  
		  List<AnneeAcademique> listeAnnee=AnneeRepo.findAll();
		  mod.addAttribute("listeAnnee",listeAnnee);
		  
		  Classe c1=new Classe();
		  c1.setIdClasse(idClasse);
		  
		  
		  AnneeAcademique anne=new AnneeAcademique();
		  anne.setIdAcad(idAcad);
		  mod.addAttribute("annee", paiementRepo.maxAnnee());
		  
		  Classe classe=new Classe();
		  classe.setIdClasse(idClasse);
		  mod.addAttribute("lignePaiement", lignePaiementRepo.findByClasse(classe));
		  
	  return "GestionPaiement/list_par_classe"; 
	  }
	  
}
