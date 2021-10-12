 package com.pathlight.hti_school.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.pathlight.hti_school.entities.User;

@Component
public class PasswordExpirationFilter implements Filter {
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
    		throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestUrl=httpRequest.getRequestURL().toString();
         
        
        if (requestUrl.endsWith("png")||requestUrl.endsWith("jpg")||requestUrl.endsWith("css")||requestUrl.endsWith("js")
        		||requestUrl.endsWith("bootstrap")||requestUrl.endsWith("/change_password")) {
            chain.doFilter(httpRequest, response);
            return;
        }
        System.out.println("PasswordExpirationFilter:"+requestUrl);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = null;
        
        if (authentication != null) {
            principal = authentication.getPrincipal();
        }
        if (principal != null && principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            User user=userDetails.getUser();
            
            if(user.isPasswordExpired()) {
            	 System.out.println("User:"+user.getFirstName()+":password expired");
            	 System.out.println("Last Time password changed:"+user.getPasswordChangedTime());
            	 System.out.println("Current Time:"+ new Date() );
            	 
            	 HttpServletResponse httpResponse = (HttpServletResponse) response;
            	 String redirectURL = httpRequest.getContextPath() + "/change_password";
            	 httpResponse.sendRedirect(redirectURL);
            }else {
            	System.out.println("User:"+user.getFirstName()+":password Not expired");
            	chain.doFilter(request, response);
            }
        }
        else {
        	
            chain.doFilter(request, response);         
          }
         
    }
     
}
