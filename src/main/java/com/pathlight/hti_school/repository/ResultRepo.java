package com.pathlight.hti_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathlight.hti_school.entities.Result;


@Repository
public interface ResultRepo extends JpaRepository<Result, Integer> {
	
}
