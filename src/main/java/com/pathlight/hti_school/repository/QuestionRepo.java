package com.pathlight.hti_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathlight.hti_school.entities.Question;


@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

}