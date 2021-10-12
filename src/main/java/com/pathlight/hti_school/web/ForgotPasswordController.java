package com.pathlight.hti_school.web;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pathlight.hti_school.entities.User;
import com.pathlight.hti_school.security.UserNotFoundException;
import com.pathlight.hti_school.security.UserService;
import com.pathlight.hti_school.security.Utility;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
	@Autowired
	private UserService userService;
	
	
	  @Autowired 
	  private JavaMailSender mailSender;
	 

	
	
	@GetMapping("/forgotPass")
    public String showForgotPasswordF(Model model) {
		model.addAttribute("Title","Forgot Password");
		return "login/forgotPassword";
 
    }
	
	@PostMapping("/forgotPass")
	public String processForgotPassword(HttpServletRequest request, Model model) {
	    String email = request.getParameter("email");
	    String token = RandomString.make(255);
	     
	    try {
	    	userService.updateResetPassword (token, email);
	       String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
	         
	       sendEmail(email, resetPasswordLink);
	        model.addAttribute("message", "We have sent a reset password link to your email. Please check.");	       
	         
	    } catch (UserNotFoundException ex) {
	        model.addAttribute("error", ex.getMessage());
	     } catch (UnsupportedEncodingException | MessagingException e) {
	        model.addAttribute("error", "Error while sending email");
	    }
	    model.addAttribute("Title","Forgot Password");
	    return "login/forgotPassword";
	}

	
	public void sendEmail(String email, String link) throws MessagingException, UnsupportedEncodingException {
	   MimeMessage message = mailSender.createMimeMessage();              
	  MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom("saintoinefrai9@gmail.com", "htiSchool support");
	    helper.setTo(email);
	     
	   String subject = "Here's the link to reset your password";
	     
	  String content = "<p>Hello,</p>"
	          + "<p>You have requested to reset your password.</p>"
	          + "<p>Click the link below to change your password:</p>"
	          + "<p><a href=\"" + link + "\">Change my password</a></p>"
	          + "<br>"
	          + "<p>Ignore this email if you do remember your password, "
	          + "or you have not made the request.</p>";
	     
	   helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	}
	
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
	    User user = userService.getByResetPassword(token);
	    model.addAttribute("token", token);
	     
	    if (user == null) {	    	 
	        model.addAttribute("message", "Invalid Token");
	        return "login/message";
	    }
		
	    return "login/ResetPassword";
	}
	
	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
	    String token = request.getParameter("token");
	    String password = request.getParameter("password");
	     
	    User user = userService.getByResetPassword(token);
	    model.addAttribute("title", "Reset your password");
	     
	    if (user == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "login/message";
	    } else {           
	    	userService.updatePassword(user, password);
	         
	        model.addAttribute("message", "You have successfully changed your password.");
	    }
	     
	    return "login/message";
	}

}
