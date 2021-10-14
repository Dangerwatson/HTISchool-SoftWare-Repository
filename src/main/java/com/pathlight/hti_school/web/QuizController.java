package com.pathlight.hti_school.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pathlight.hti_school.entities.Doc;
import com.pathlight.hti_school.entities.Personne;
import com.pathlight.hti_school.entities.Question;
import com.pathlight.hti_school.entities.QuestionForm;
import com.pathlight.hti_school.entities.Result;
import com.pathlight.hti_school.entities.Role;
import com.pathlight.hti_school.repository.QuestionRepo;
import com.pathlight.hti_school.service.QuizService;


@Controller
public class QuizController {
	
	@Autowired
	Result result;
	@Autowired
	QuizService qService;
	
	@Autowired
	QuestionRepo qRepo;
	
	Boolean submitted = false;
	
	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}
	
	/*@GetMapping("/")
	public String home() {
		return "index.html";
	}*/
	
	@PostMapping("/evaluation")
	public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
		if(username.equals("")) {
			ra.addFlashAttribute("warning", "You must enter your name");
			return "redirect:/";
		}
		
		submitted = false;
		result.setUsername(username);
		
		QuestionForm qForm = qService.getQuestions();
		m.addAttribute("qForm", qForm);		
		
		return "Quiz/Evaluation";
	}
	
	@PostMapping("/submit")
	public String submit(@ModelAttribute QuestionForm qForm, Model m) {
		if(!submitted) {
			result.setTotalCorrect(qService.getResult(qForm));
			qService.saveScore(result);
			submitted = true;
		}
		
		return "InscriptionEnLigne/cours";
	}
	
	
	
	@GetMapping("/score")
	public String score(Model m) {
		List<Result> sList = qService.getTopScore();
		m.addAttribute("sList", sList);
		
		return "Quiz/scoreboard";
	}
	
	
	  @RequestMapping("/saveQuestion") 
	  public String save(Question question) { 
		  qRepo.save(question);
		 return "redirect:/HTIProfesseurs"; 
	  }
	  
	 
	  @RequestMapping("/editQuest") 
	  public String edit(Model model, int quesId) { 
		  Question question=qRepo.findById(quesId).get();
	    	model.addAttribute("question", question);
		 return "Quiz/EditerQuestion"; 
	  }
	 
	
		

}
