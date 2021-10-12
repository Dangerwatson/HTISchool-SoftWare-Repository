package com.pathlight.hti_school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Doc;




public interface DocRepository  extends JpaRepository<Doc,Integer>{

	public List<Doc> findByClasse(Classe clas);
	

}
