package com.pathlight.hti_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pathlight.hti_school.entities.GestBourse;

public interface BourseRepository extends JpaRepository<GestBourse, Long> {
	@Query(value = "SELECT max(codeBourse) FROM GestBourse")
	public String maxCodeBourse();
}
