package com.pathlight.hti_school.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Doc;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.DocRepository;



@Service
public class DocStorageService {
  @Autowired
  private DocRepository docRepository;  
  
  
  public Doc saveFile(MultipartFile file) {
	  String docname = file.getOriginalFilename();
	  String coeficient ;
	  try {

		String coefficient="n";
		
		Doc doc = new Doc(docname,coefficient,file.getContentType(),file.getBytes());
		     //date 
			 doc.setDatePublication(new Date()); 
			//date
			 doc.setClasse(new Classe());
			 doc.setCoefficient(new Doc());
		  return docRepository.save(doc);
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  return null;
  }
  

public Optional<Doc> getFile(Integer fileId) {
	  return docRepository.findById(fileId);
  }
  public List<Doc> getFiles(){
	  return docRepository.findAll();
  }
  
}
