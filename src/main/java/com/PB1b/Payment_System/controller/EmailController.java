package com.PB1b.Payment_System.controller;

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
    
	void sendEmail(String Email, String Subject, String Text) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(Email);

        msg.setSubject(Subject);
        msg.setText(Text);

        javaMailSender.send(msg);

    }	
}
