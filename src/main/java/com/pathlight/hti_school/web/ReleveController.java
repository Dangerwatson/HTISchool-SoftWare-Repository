package com.pathlight.hti_school.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pathlight.hti_school.entities.AnneeAcademique;
import com.pathlight.hti_school.entities.CategoryMatiere;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Eleves;
import com.pathlight.hti_school.entities.Matiere;
import com.pathlight.hti_school.entities.ModelSaveNote;
import com.pathlight.hti_school.entities.Moyenne;
import com.pathlight.hti_school.entities.Personne;
import com.pathlight.hti_school.entities.ReleveNote;
import com.pathlight.hti_school.repository.AnneeAcadRepository;
import com.pathlight.hti_school.repository.CategorieMatiereRepository;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.ElevesRepository;
import com.pathlight.hti_school.repository.MatiereRepository;
import com.pathlight.hti_school.repository.MoyenneRepository;
import com.pathlight.hti_school.repository.NotesRepository;

@Controller
public class ReleveController {

	@Autowired
	private ClasseRepository classRep;

	@Autowired
	private MatiereRepository matiereRep;
	@Autowired
	private ElevesRepository elevesRepo;

	@Autowired
	private AnneeAcadRepository AnneeRepo;

	@Autowired
	private NotesRepository noteRepo;

	@Autowired
	private MoyenneRepository moyRepo;

	// ajouter note
	@RequestMapping("/releve_notes")
	public String viewHome(Model model) {
		// classe
		List<Classe> classe = classRep.findAll();
		model.addAttribute("listeClasse", classe);
		return "GestionExamens/ReleveNote";
	}

	// Retourne par classe
	@RequestMapping("findByClasse")
	public String listEleves(Model mod, Long idClasse, Eleves el) {
		// liste eleves par classe
		Classe c = new Classe();
		c.setIdClasse(idClasse);
		mod.addAttribute("listeEl", elevesRepo.findByClasse(c));
		// liste classe
		List<Classe> classe = classRep.findAll();
		mod.addAttribute("listeClasse", classe);
		// classe par id
		Classe cl = classRep.findById(idClasse).get();
		mod.addAttribute("cls", cl);

		// liste matiere par classe
		Classe c1 = new Classe();
		c1.setIdClasse(idClasse);
		mod.addAttribute("listeMat", matiereRep.findByClasse(c1));

		// Annee Academique
		List<AnneeAcademique> listeAnnee = AnneeRepo.findAll();
		mod.addAttribute("listAnnee", listeAnnee);
		ArrayList<ReleveNote> ar = new ArrayList<ReleveNote>();
		mod.addAttribute("relv", ar.add(new ReleveNote()));

		return "GestionExamens/ReleveNote";
	}

	/*
	 * @RequestMapping("/saveNotes") public String saveNote(ReleveNote re, Model
	 * mod){ noteRepo.save(re);
	 * 
	 * return "redirect:releve_notes"; }
	 */

	// save multiple note
	@RequestMapping(value = "/saveNotes", method = RequestMethod.POST)
	public String saveNote(@ModelAttribute ModelSaveNote re) {

		ModelSaveNote not = new ModelSaveNote();

		not.setNote(re.getNote());
		not.setIdClasse(re.getIdClasse());
		not.setMatiere(re.getMatiere());
		not.setAnneeAcad(re.getAnneeAcad());
		not.setFrequence(re.getFrequence());
		not.setIdPerson(re.getIdPerson());

		String[] note = not.getNote().split(",");
		String[] matiere = not.getMatiere().split(",");
		String[] classe = not.getIdClasse().split(",");
		String[] annee = not.getAnneeAcad().split(",");
		String[] fre = not.getFrequence().split(",");
		String[] pers = not.getIdPerson().split(",");

		List<ReleveNote> tempList = new ArrayList<>();
		for (int i = 0; i < note.length; i++) {

			ReleveNote rel = new ReleveNote();
			// appel a chaque classe qui derive(reference comme cles etrangere)
			Eleves el = new Eleves();
			Classe cl = new Classe();
			Matiere m = new Matiere();
			AnneeAcademique ann = new AnneeAcademique();

			// ModelSaveNote rel=new ModelSaveNote();
			/* converti de type String en Long en passant par la classe ModelSaveNote */
			// Matiere
			m.setIdMatiere(Long.parseLong(matiere[0]));
			rel.setMatiere(m);
			// Classe
			cl.setIdClasse(Long.parseLong(classe[0]));
			rel.setClasse(cl);
			// Annee Acadenique
			ann.setIdAcad(Long.parseLong(annee[0]));
			rel.setAnneeAcad(ann);

			// Personne
			el.setIdPerson(Long.parseLong(pers[i]));
			rel.setPersonne(el);

			// frequence
			rel.setFrequence(fre[0]);
			// Note
			rel.setNote(Float.parseFloat(note[i]));

			tempList.add(rel);
		}
		noteRepo.saveAll(tempList);
		return "redirect:releve_notes";
	}

	// liste note par classe
	@RequestMapping("/listeNote")
	public String viewPre(Model model) {
		// classe
		List<Classe> classe = classRep.findAll();
		model.addAttribute("listeClasse", classe);

		
		// Annee Academique
		List<AnneeAcademique> listeAnnee = AnneeRepo.findAll();
		model.addAttribute("listAnnee", listeAnnee);
		return "GestionExamens/ListeNotes";
	}

	// Retourne par classe
	@RequestMapping("findNoteByClasse")
	public String listEleve(Model mod, Long idClasse, Eleves el, Long idMatiere, Long idRelve, Long idAcad,
			@RequestParam(name = "frequence", defaultValue = "") String frequence, 
			@RequestParam(name = "anneeAC", defaultValue = "") Long anneeAC,
			@RequestParam(name = "classeName", defaultValue = "") Long classeName) {

		// liste eleves par classe
		//Classe c = new Classe();
		//c.setIdClasse(idClasse);
		//mod.addAttribute("note", elevesRepo.findByClasse(c));

		// liste matiere par classe
		//Classe ma = new Classe();
		//ma.setIdClasse(idClasse);
		//mod.addAttribute("mat", matiereRep.findByClasse(ma));

		// liste note par classe
		//Classe notc = new Classe();
		//notc.setIdClasse(idClasse);
		//mod.addAttribute("notC", noteRepo.findByClasse(notc));

		// liste moyenne par classe
		//Classe moyenn = new Classe();
		//moyenn.setIdClasse(idClasse);
		//mod.addAttribute("Moyenn", moyRepo.findByClasse(moyenn));

		// Matiere
		List<Matiere> matiere = matiereRep.findAll();
		mod.addAttribute("listeMatiere", matiere);

		// liste classe
		List<Classe> classe = classRep.findAll();
		mod.addAttribute("listeClasse", classe);
		// classe par id
		//Classe cl = classRep.findById(idClasse).get();
		//mod.addAttribute("clsN", cl);

		// note liste
		List<ReleveNote> no = noteRepo.findAll();
		mod.addAttribute("listeNote", no);

		// Annee Academique
		List<AnneeAcademique> listeAnnee = AnneeRepo.findAll();
		mod.addAttribute("listAnnee", listeAnnee);

		// liste moyenne
		List<Moyenne> listMoyenne = moyRepo.findAll();
		mod.addAttribute("listMoyenne", listMoyenne);
		
		
		mod.addAttribute("list_matiere", noteRepo.listByClasseParam("%" +classeName+"%" ));
		
		mod.addAttribute("list_personne", noteRepo.listElByClasseParam("%" +classeName+"%" ));
		
		mod.addAttribute("listN", noteRepo.listNotes("%" +frequence+"%", anneeAC, classeName ));
         
		return "GestionExamens/ListeNotes";
	}

	// Retourne par classe
	@RequestMapping("findNoteByAnnee")
	public String listNote(Model mod, Long idClasse, Eleves el, Long idMatiere, Long idRelve, Long idAcad, 
			@RequestParam(name = "frequence", defaultValue = "") String frequence, @RequestParam(name = "anneeAC", defaultValue = "") String anneeAC) {
		AnneeAcademique a=new AnneeAcademique();
        a.setIdAcad(idAcad);
    //    mod.addAttribute("listN", noteRepo.listNotes("%" +frequence+"%", "%" +anneeAC+"%" ));
		return "GestionExamens/recuperer_list";	
	}
	

	@RequestMapping("/releveNote")
	// passe le name de l'input en parametre avec RequestParam
	public String releveNoteEleves(@RequestParam(name = "elevesCode", defaultValue = "") String codeEl, Model mod,
			Long idClasse, Long idMoy) {
		// Recuperation du mot clé
		List<ReleveNote> releve = noteRepo.searchNoteByCodeEleves("%" + codeEl + "%");
		mod.addAttribute("releveNotes", releve);

		Eleves classe = noteRepo.searchInfoByCodeEleves("%" + codeEl + "%");
		mod.addAttribute("classe", classe);

		// Pour que la valeur rest afficher dans la zone de texte
		mod.addAttribute("code", codeEl);

		// liste moyenne
		Moyenne listMoyenne = moyRepo.searchNoteByCodeEleves("%" + codeEl + "%");
		mod.addAttribute("Mo", listMoyenne);

		// La liste des modalités
		// List<ModalitePaiement> ModalitePaiement=modaliteRepo.findAll();
		// mod.addAttribute("ModalitePaiement",ModalitePaiement);
		return "GestionExamens/releve_note_print";
	}

	@RequestMapping("genererReleve")
	public String releve(Model mod, Long idPerson, Long idRelve) {
		Eleves built = elevesRepo.findById(idPerson).get();
		mod.addAttribute("built", built);

		// liste note par personne
		// Eleves notcp=new Eleves();
		// notcp.setIdPerson(idPerson);
		// mod.addAttribute("releveNotes", noteRepo.findByPersonne(notcp));

		List<ReleveNote> releve = noteRepo.findAll();
		mod.addAttribute("releveNotes", releve);

		// ReleveNote releve=noteRepo.findById(idRelve).get();
		// mod.addAttribute("releveNotes",releve);

		return "GestionExamens/releve_note_print2";
	}

}
