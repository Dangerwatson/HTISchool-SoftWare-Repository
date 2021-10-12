package com.pathlight.hti_school.web;

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

import com.pathlight.hti_school.entities.Doc;
import com.pathlight.hti_school.entities.SendDevoir;
import com.pathlight.hti_school.repository.SendDeRepository;
import com.pathlight.hti_school.service.SendDevoirStorageService;

@Controller
public class SendDevoirController {
	
	@Autowired 
	private SendDevoirStorageService sendDevoirStorageService;
	
	@Autowired
	  private SendDeRepository sendRepository;
	
	
	@PostMapping("/uploadFiles1")
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		for (MultipartFile file: files) {
			
			sendDevoirStorageService.saveFile(file);
			
		}
		return "redirect:/sendDevoir";
	}
	@GetMapping("/downloadFile1/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		SendDevoir send = sendDevoirStorageService.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(send.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+send.getDocName()+"\"")
				.body(new ByteArrayResource(send.getData()));
	}
	
	 @GetMapping("/sendDevoir") 
	  public String sendevoir(Model model){
		  
			return "InscriptionEnLigne/SendDevoir";
		
	  }
	 
	 
	 
}
