package com.pathlight.hti_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pathlight.hti_school.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	@Query("SELECT r FROM Role r WHERE r.name = ?1")
	public Role findByName(String name);
	
	
	@Query("SELECT rol, rol.id FROM Role rol WHERE rol.name = 'ADMIN'")
	public Role findByNameADM(Long id);
	
	@Query("SELECT role, role.id FROM Role role WHERE role.name = 'USERS'")
	public Role findByNameUser(Long id);
}
