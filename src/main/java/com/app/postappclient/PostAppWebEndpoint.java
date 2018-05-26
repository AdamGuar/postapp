package com.app.postappclient;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@ComponentScan("com.app.postappclient")
public class PostAppWebEndpoint {
	
	
	@RequestMapping("/")
	String home(Model model,Principal principal) {
		model.addAttribute("username", principal.getName());
		return "index";
	}
	
	@RequestMapping("/model/{id}")
	String modelController(Model model,Principal principal,@PathVariable String id) {
		model.addAttribute("username", principal.getName());
		model.addAttribute("modelname",id);
		return "model";
	}
	

	public static void main(String[] args) throws Exception {
        SpringApplication.run(PostAppWebEndpoint.class, args);
    }
	
}
