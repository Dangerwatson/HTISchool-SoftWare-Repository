package com.pathlight.hti_school.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Doc;
import com.pathlight.hti_school.entities.SendDevoir;
import com.pathlight.hti_school.repository.SendDeRepository;



@Service
public class SendDevoirStorageService {
	
	@Autowired
	  private SendDeRepository sendRepository;
	
	public SendDevoir saveFile(MultipartFile file) {
		  String docname = file.getOriginalFilename();
		  
		  try {

			
			SendDevoir send = new SendDevoir(docname,file.getContentType(),file.getBytes());
			     //date 
				 send.setDatePublication(new Date()); 
				//date
				 send.setClasse(new Classe());				 
			  return sendRepository.save(send);
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  return null;
	  }
	  

	public Optional<SendDevoir> getFile(Integer fileId) {
		  return sendRepository.findById(fileId);
	  }
	  public List<SendDevoir> getFiles(){
		  return sendRepository.findAll();
	  }

}
