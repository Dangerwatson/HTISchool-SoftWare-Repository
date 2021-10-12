package com.pathlight.hti_school.web;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pathlight.hti_school.entities.AnneeAcademique;
import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Doc;
import com.pathlight.hti_school.entities.Question;
import com.pathlight.hti_school.entities.Result;
import com.pathlight.hti_school.entities.Role;
import com.pathlight.hti_school.entities.User;
import com.pathlight.hti_school.metier.EcoleMetier;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.DocRepository;
import com.pathlight.hti_school.repository.QuestionRepo;
import com.pathlight.hti_school.repository.RoleRepository;
import com.pathlight.hti_school.repository.UserRepository;
import com.pathlight.hti_school.security.UserService;
import com.pathlight.hti_school.service.DocStorageService;
import com.pathlight.hti_school.service.QuizService;

@Controller
public class AppController {

	@Autowired
	private UserService service;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EcoleMetier ecoleMetier;
	
	@Autowired 
	private DocStorageService docStorageService;
	
	@Autowired
	private ClasseRepository classRep;
	
	@Autowired
	QuizService qService;
	
	@Autowired
	QuestionRepo qRepo;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	//@GetMapping("/login")
	//public String viewLogin() {
		
		//Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		//if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
		//return "login/login2";
	//	}
		
	//	return "login/login2"; 
	//}
	
	/*
	 * @GetMapping("/logout") public String logout() { return "login/login2"; }
	 */
	@GetMapping("/logout")
	public String logout() { 
		return  ("InscriptionEnLigne/loginFrontend"); 
		}
	
	
	
	@GetMapping("/temp2")
	public String viewProfesseur() {
		return "Ecole2/Corp2";
	}
	
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "login/registerForm";
	}
	
	//partie login pour backend
	@PostMapping("/process_register")
	public String processRegister(@Valid User user, Role role, Long id,BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return "login/registerForm";
		}
		if (service.isUserPresent(user.getEmail())) {
			model.addAttribute("exist", true);
			return "login/registerForm";
		}
		//date 
		 user.setPasswordChangedTime(new Date());
		 //date
		//service.registerDefaultUser(user);
		ecoleMetier.AddUser(user,id);
		model.addAttribute("success", true);		
		return "redirect:/login";
	}
	
/* ajouter user a l interieure eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee*/
	
	@GetMapping("/registerUser")
	public String RegistrationUser(Model model) {
		model.addAttribute("user", new User());
		
		List<Role> listRoles = service.listRoles();		
		model.addAttribute("listRoles", listRoles);
		
		//classe
		  List<Classe> classe1=classRep.findAll();
		  model.addAttribute("listeClasse",classe1);
		
		return "Users/registerFormUser";
	}
	
	//partie login pour backend
		@PostMapping("/process_register2")
		public String processRegister2(@Valid User user, Role role, Long id,BindingResult bindingResult,Model model) {
			if (bindingResult.hasErrors()) {
				return "Users/registerFormUser";
			}
			if (service.isUserPresent(user.getEmail())) {
				model.addAttribute("exist", true);
				return "Users/registerFormUser";
			}
			//date 
			 user.setPasswordChangedTime(new Date());
			 //date
			//service.registerDefaultUser(user);
			ecoleMetier.AddUser(user,id);
			model.addAttribute("success", true);		
			return "redirect:/registerUser";
		}
	
	
	//partie login pour frontend
		@PostMapping("/processFrontend")
		public String RegisterFrontend(@Valid User user, Role role, Long id,BindingResult bindingResult,Model model) {
			if (bindingResult.hasErrors()) {
				return "InscriptionEnLigne/registrerFrontend";
			}
			if (service.isUserPresent(user.getEmail())) {
				model.addAttribute("exist", true);
				return "InscriptionEnLigne/registrerFrontend";
			}
			//date 
			 user.setPasswordChangedTime(new Date());
			 //date
			//service.registerDefaultUser(user);
			ecoleMetier.AddUser(user,id);
			model.addAttribute("success", true);		
			return "redirect:/attente";
		}
	
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "Users/listUsers";
	}
	
	
	
	@GetMapping("/modUser")
	public String n(Long id, Model model) { 
		User user = service.get(id);
		List<Role> listRoles = service.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "Users/EditUser"; 
		}
	
	
	
	//liste role
	@GetMapping("/role")
	public String listrole(Model model) {
		List<Role> listRoles = service.listRoles();
		model.addAttribute("listRoles", listRoles);		
		return "Users/ListRole";
	}
	
	
	/* save role */
	  @RequestMapping("/saveRole") 
	  public String save(Role role) { 
		 roleRepo.save(role);
		 return "redirect:/role"; 
	  }
	  
	  @GetMapping("/editerRole")
	  public String edit(Model model, Integer id) {
		  Role role=roleRepo.findById(id).get();
		  model.addAttribute("role",role);
	  
	  return "Users/EditeRole";
	  
	  }
	  
	  @GetMapping("/deleteRole") 
	  public String delete(Integer id){ 
		  roleRepo.deleteById(id); 
		 return "redirect:role";
	  
	  }
	 
	
	  @GetMapping("/deleteUser") 
	  public String deleteuser(Long id){ 
		  userRepo.deleteById(id); 
		 return "redirect:users";
	  
	  }
	 
	
	
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		service.save(user);
		
		return "redirect:/users";
	}
	
	
	//2eme facon
	@GetMapping("/setting1")
	public String par() {	
		
		return "Psetting/setting";
	}
	
	@GetMapping("/re")
	public String pr() {	
		
		return "Psetting/blank";
	}
	
	 @PostMapping("/change-password")
	    public String ChangePassword(@RequestParam("oldPassword")String oldPassword ,@RequestParam("newPassword")String newPassword,Principal principal,HttpSession session) {
		 System.out.println("OLD Password:"+oldPassword);
		 System.out.println("NEW Password:"+newPassword);
		 String UserName= principal.getName();
		 User currentUser= this.userRepo.findByEmail(UserName);		 
		 System.out.println(currentUser.getPassword());
		 
		 if(this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) 
		 {
			 //change the password
			 currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
			 this.userRepo.save(currentUser);
			 //session.setAttribute("message", new Message("your password is successfully change","succes"));
		 }else {
			 //errors
			// session.setAttribute("message", new Message("please enter correct old password"));
			 
		 }
	        return "redirect:/re";
	    }
	 
	 @GetMapping("/notAutorized") 
	 public String viewDirect() { 
		 return "notAutorized"; 
		 }
	
	 
	 @GetMapping("/HTIEleves")
		public String viewEdutiant(Model mod) {		
			return "FontEnd/CorpEtudiant";
		
		}
	 
	 @GetMapping("/HTIProfesseurs")
		public String viewProf(Model model) {
		//classe
		  List<Classe> classe1=classRep.findAll();
		  model.addAttribute("listeClasse",classe1);
		  model.addAttribute("classe", new Classe());
		  
		  //liste document
		  List<Doc> docs = docStorageService.getFiles();
			model.addAttribute("docs", docs);
			
			//liste resulta
			List<Result> sList = qService.getTopScore();
			model.addAttribute("sList", sList);
			
			//liste question
			List<Question> listeq = qRepo.findAll();
			model.addAttribute("listeq", listeq);
			return "FontEnd/CorpProfesseurs";
		
		}
	
	
	
	
	
}

