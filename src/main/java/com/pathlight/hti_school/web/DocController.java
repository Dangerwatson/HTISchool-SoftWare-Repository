package com.pathlight.hti_school.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Doc;

import com.pathlight.hti_school.entities.Matiere;
import com.pathlight.hti_school.repository.ClasseRepository;
import com.pathlight.hti_school.repository.DocRepository;
import com.pathlight.hti_school.service.DocStorageService;



@Controller
public class DocController {

	@Autowired 
	private DocStorageService docStorageService;
	
	@Autowired 
	private DocRepository docRepository;
	
	@Autowired
	private ClasseRepository classRep;
	
	@GetMapping("/fic")
	public String get(Model model,Long idClasse) {
		List<Doc> docs = docStorageService.getFiles();
		model.addAttribute("docs", docs);
		
		
		//Classe clas = new Classe();
		//clas.setIdClasse(idClasse);
		//model.addAttribute("docs", docRepository.findByClasse(clas));
		
		//classe
		  List<Classe> classe1=classRep.findAll();
		  model.addAttribute("listeClasse",classe1);
		  model.addAttribute("classe", new Classe());
		return "doc";
	}
	
	@PostMapping("/uploadFiles")
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		for (MultipartFile file: files) {
			
			docStorageService.saveFile(file);
			
		}
		return "redirect:/fic";
	}
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		Doc doc = docStorageService.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
	//partie delete
	  @GetMapping("/deleteDoc") 
	  public String delete(Model model, Integer id){
		  docRepository.deleteById(id); 
	  return "redirect:/fic";
	  
	  }
	/* partie frontend nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn*/
	  
	  
	//partie devoir
	  @GetMapping("/telechargerDevoir") 
	  public String devoir(Model model, Integer id){
		  
		  Doc dev=docRepository.findById(id).get();
	    	model.addAttribute("dev1", dev);
	    	
	    	List<Doc> docs = docStorageService.getFiles();
			model.addAttribute("docs", docs);
			return "InscriptionEnLigne/TelechargerDevoir";
		
	  }

	/* add cours prof */
	  @PostMapping("/uploadFiles2")
		public String uploadMultipleFiles2(@RequestParam("files") MultipartFile[] files) {
			for (MultipartFile file: files) {
				
				docStorageService.saveFile(file);
				
			}
			return "redirect:/HTIProfesseurs";
		}
	  
	//partie delete
	  @GetMapping("/deleteDocF") 
	  public String deleteF(Model model, Integer id){
		  docRepository.deleteById(id); 
	  return "redirect:/HTIProfesseurs";
	  
	  }
	  
	/* finnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn */
}
