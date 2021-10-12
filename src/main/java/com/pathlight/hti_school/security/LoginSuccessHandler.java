package com.pathlight.hti_school.security;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;


import com.pathlight.hti_school.entities.User;
import com.pathlight.hti_school.repository.UserRepository;


  @Component public class LoginSuccessHandler extends
  SavedRequestAwareAuthenticationSuccessHandler {
  
  @Autowired private UserRepository userRepo;
  
  @Override public void onAuthenticationSuccess(HttpServletRequest request,
  HttpServletResponse response, Authentication authentication) throws
  ServletException, IOException { CustomUserDetails userdetails=
  (CustomUserDetails) authentication.getPrincipal();
  
  
  
  String redirectURL = request.getContextPath();
  if(userdetails.hasRole("ELEVES"))
  {
  
  redirectURL +="/HTIEleves"; 
  } 
  
		
  else	  if(userdetails.hasRole("PROFESSEUR")) {
		  
		  redirectURL +="/HTIProfesseurs"; }
		 
  else {
  redirectURL = "HTISchool"; }
  
  
  response.sendRedirect(redirectURL); }
  
  
  
  }
 
