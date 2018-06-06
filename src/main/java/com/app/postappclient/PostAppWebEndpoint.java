package com.app.postappclient;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.postappclient.model.MailMessage;

@Controller
@EnableAutoConfiguration
@ComponentScan("com.app.postappclient")
public class PostAppWebEndpoint {

	
	@Autowired
	ApiService service;
	
	// Login form
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/")
	String home(Model model, Principal principal) {
		List<String> modelNames = service.getAllModelIds();
		model.addAttribute("models",modelNames);
		model.addAttribute("username", principal.getName());
		return "index";
	}

	@RequestMapping("/model/{id}")
	String modelController(Model model, Principal principal, @PathVariable String id) {
		model.addAttribute("username", principal.getName());
		model.addAttribute("modelname", id);
		return "model";
	}
	
	
	@RequestMapping("/users")
	String users(Model model, Principal principal) {
		List<String> users = service.getAllUsernames();
		model.addAttribute("users",users);
		model.addAttribute("username", principal.getName());
		return "users";
	}
	
	@RequestMapping("/mailto/{mailtousername}")
	String mailto(Model model, Principal principal,@PathVariable String mailtousername) {
		model.addAttribute("username", principal.getName());
		model.addAttribute("mailtousername",mailtousername);
		return "mailto";
	}
	
	@RequestMapping(value = "/mailto", method =  RequestMethod.POST)
	String mailtoPost(Principal principal,String mailtousername, String subject,String message) {
		MailMessage msg = new MailMessage();
		msg.setTo(Arrays.asList(mailtousername));
		msg.setFrom(principal.getName());
		msg.setSubject(subject);
		msg.setMessage(message);
		service.sendMail(msg);
		return "sentOk";
	}
	

	

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PostAppWebEndpoint.class, args);
	}

}
