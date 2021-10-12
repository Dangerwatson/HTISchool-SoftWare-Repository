package com.pathlight.hti_school.web;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pathlight.hti_school.entities.User;
import com.pathlight.hti_school.repository.UserRepository;
import com.pathlight.hti_school.security.CustomUserDetails;
import com.pathlight.hti_school.security.CustomUserDetailsService;
import com.pathlight.hti_school.security.UserService;

import org.springframework.ui.Model;

@Controller
public class PasswordController {
 
    @Autowired
    private UserService UserService;
    
     
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
	private UserRepository userRepo;
     
    @GetMapping("/change_password")
    public String showChangePassword(Model model) {
        model.addAttribute("pageTitle", "Change Expired Password");    
        return "login/change_password";
    }
     
    @PostMapping("/change_password")
    public String processChangePassword(HttpServletRequest request,    		
    		HttpServletResponse response,Model model,Principal principal ) throws ServletException {       
        String oldPassword=request.getParameter("oldPassword");
        String newPassword=request.getParameter("newPassword");
        String username=principal.getName();
        User user=this.userRepo.findByEmail(username);
        
        if (oldPassword.equals(newPassword)) {
        	 model.addAttribute("pageTitle", "Change Expired Password");
            model.addAttribute("message", "Your new password must be different than the old one.");
             
            return "login/change_password";
        }
        
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
       	 model.addAttribute("pageTitle", "Change Expired Password");
           model.addAttribute("message", "Your old password is incorrect.");          
           return "login/change_password";
            
       }
        
        else {
        
        UserService.changePassword(user, newPassword,passwordEncoder);
        request.logout();
        model.addAttribute("pageTitle", "Login again");
        model.addAttribute("message", "You have changed your password successfully. "
                + "Please login again.");
    	return "redirect:/login";
    }  
        
    }
     
}
