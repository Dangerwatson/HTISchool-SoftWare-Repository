package com.pathlight.hti_school.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pathlight.hti_school.entities.Role;
import com.pathlight.hti_school.entities.User;
import com.pathlight.hti_school.repository.RoleRepository;
import com.pathlight.hti_school.repository.UserRepository;





@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	RoleRepository roleRepo;
	
	@Autowired 
	PasswordEncoder passwordEncoder;
	
	public void registerDefaultUser(User user) {
		Role roleUser = roleRepo.findByName("User");
		user.addRole(roleUser);
		encodePassword(user);
		userRepo.save(user);
	}
	
	public List<User> listAll() {
		return userRepo.findAll();
	}

	public User get(Long id) {
		return userRepo.findById(id).get();
	}
	
	public List<Role> listRoles() {
		return roleRepo.findAll();
	}
	
	public void save(User user) {
		encodePassword(user);		
		userRepo.save(user);
	}
	
	
	
	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);		
	}
	
	//Rset passwod
	 public void updateResetPassword(String token, String email) throws UserNotFoundException {
	        User user = userRepo.findByEmail(email);
	        if (user != null) {
	            user.setResetPassword(token);
	            userRepo.save(user);
	        } else {
	            throw new UserNotFoundException("Could not find any customer with the email " + email);
	        }
	    }
	 
	 public User getByResetPassword(String resetPass) {
	        return userRepo.findByResetPassword(resetPass);
	    }
	 
	 public void updatePassword(User user, String newPassword) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(newPassword);
	        user.setPassword(encodedPassword);
	         
	        user.setResetPassword(null);
	        userRepo.save(user);
	    }

	 public boolean isUserPresent(String email) {
			// TODO Auto-generated method stub
			User u = userRepo.findByEmail(email);
			if(u!=null) {
				return true;
			}
			return false;
		}
	 
	 public void changePassword(User user, String newPassword, PasswordEncoder passwordEncoder) { 
		  String encodedPassword = passwordEncoder.encode(newPassword); 
	  user.setPassword(encodedPassword);
	  
	 user.setPasswordChangedTime(new Date());
	  userRepo.save(user);
	  
	  }
	
}
