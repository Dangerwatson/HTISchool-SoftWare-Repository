package com.pathlight.hti_school.metier;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.pathlight.hti_school.entities.Classe;

import com.pathlight.hti_school.entities.Role;
import com.pathlight.hti_school.entities.User;

import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.RoleRepository;
import com.pathlight.hti_school.repository.UserRepository;
@Service
@Transactional
public class EcoleMetierImpl implements EcoleMetier {
@Autowired
private ClasseRepository classeRepo;
@Autowired
private UserRepository userRepo;
@Autowired
private RoleRepository roleRepo;
@Autowired 
PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public Role getIdRole(Long id) {
		Role r=roleRepo.findByNameADM(id);
		return r;
	}
	@Override
	public Role getIdRoleUser(Long id) {
		Role r1=roleRepo.findByNameUser(id);
		return r1;
	}
	
	@Override
	public void AddUser(User user, Long id) {
		Long count=userRepo.CountIdUser();
		Role role=getIdRole(id);
		Role role1=getIdRoleUser(id);	
		if(count==0) {
			user.addRole(role);
			encodePassword(user);
			userRepo.save(user);
		}
		else {
			user.addRole(role1);
			encodePassword(user);
			userRepo.save(user);
		}
		
	}
	
	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);		
	}
	@Override
	public void AddEcole(Classe classe) {
		// TODO Auto-generated method stub
		
	}


}
