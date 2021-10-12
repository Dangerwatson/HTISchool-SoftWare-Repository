package com.pathlight.hti_school.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pathlight.hti_school.entities.AddTypeBourse;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.GestBourse;
import com.pathlight.hti_school.entities.Matiere;

import com.pathlight.hti_school.repository.BourseRepository;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.DepartementRepository;
import com.pathlight.hti_school.repository.ElevesRepository;

import com.pathlight.hti_school.security.CustomUserDetails;

@Controller
public class BourseController {

	@Autowired
	private BourseRepository bourseRepo;

	@Autowired
	private ElevesRepository elevesRepo;

	
	@Autowired
	private ClasseRepository classRep;

	@Autowired
	private DepartementRepository repoDept;

	@RequestMapping("gestion_bourse")
	private String type_bourse(Model mod) {

		return "GestionBourse/type_bourse_form";
	}

	
 

	@RequestMapping("get_bourse")
	private String bourse(Model mod, Long idEcole) {

		mod.addAttribute("listeClasse", classRep.findAll());
		mod.addAttribute("bourse", new GestBourse());
		return "GestionBourse/bourse_form";
	}

	@RequestMapping("findElevesByClasse")
	private ModelAndView listEleves(Model mod,Long idEcole,Long idClasse,Long idEcole1, Authentication authentication, ModelMap moda) {
		 CustomUserDetails userdetails= (CustomUserDetails) authentication.getPrincipal();
	     
		
		
	//liste eleves par classe
	Classe c=new Classe();
	c.setIdClasse(idClasse);
	mod.addAttribute("listEleves", elevesRepo.findByClasse(c));


	//listDept
			moda.put("listDept", repoDept.findAll());


	mod.addAttribute("listeClasse", classRep.findAll());
	mod.addAttribute("bourse",new GestBourse());
	return new ModelAndView ("GestionBourse/bourse_form", "genererCode1", genererCode1());
	}
	
	@RequestMapping(value = "/save_boursier")
	public String saveBourse(GestBourse bourse) {
		bourseRepo.save(bourse);
		
	return "redirect:/get_bourse";
	}
	
	// generer code automatique
	public String genererCode1() {
		String code;
		String codeMax = bourseRepo.maxCodeBourse();
		if (codeMax != null) {
			String rnno = codeMax;
			int co = rnno.length();
			String text = rnno.substring(0, 10);
			String num = rnno.substring(10, co);
			int n = Integer.parseInt(num);
			n++;
			String snum = Integer.toString(n);
			String ftx = text + snum;
			code = ftx;
		} else {
			code = "787890233-10000";
		}
		return code;
	}

}
