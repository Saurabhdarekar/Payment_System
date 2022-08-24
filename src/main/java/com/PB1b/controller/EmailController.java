package com.PB1b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	@Autowired
    private JavaMailSender javaMailSender;
    // Sending a simple Email
    
	void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("saurabhdarekar294@gmail.com");

        msg.setSubject("Testing from - Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }
	@GetMapping("/message")
	public void message(){
		sendEmail();
		System.out.println("I m invoked");
	}	
}
