package com.pathlight.hti_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathlight.hti_school.entities.PresenceProfesseur;

public interface PresenceRepositoryProf extends JpaRepository<PresenceProfesseur, Long> {

}
