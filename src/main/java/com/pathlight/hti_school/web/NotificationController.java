package com.pathlight.hti_school.web;


import java.util.List;

import javax.management.Notification;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pathlight.hti_school.entities.Classe;
import com.pathlight.hti_school.entities.Notifications;
import com.pathlight.hti_school.repository.NotificationRepository;



@Controller
public class NotificationController {
	
	@Autowired
	private NotificationRepository NotificationRepo;
	
	@GetMapping("/notifications")
	public String notif() { 
		return "Notification/notification"; 
		}
	@GetMapping("/lisnotifications")
	public String notifl(Model model) {
		 List<Notifications> listeNotif=NotificationRepo.findAll();
		  model.addAttribute("listeNotif",listeNotif);
		//  model.addAttribute("listeNotif", new Notifications());
		return "Notification/notification"; 
		}
	
	 
	  @RequestMapping("/saveNotification")
	  public String save(@Valid Notifications nof,Model model){
		  NotificationRepo.save(nof);
	
	 return "redirect:notifications";
	  }

}
