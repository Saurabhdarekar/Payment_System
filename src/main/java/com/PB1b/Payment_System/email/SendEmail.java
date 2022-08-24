package com.PB1b.Payment_System.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {
	
	@Autowired
    private JavaMailSender javaMailSender;
		
	public void sendMail(String toEmail, String Subject, String Text) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);

        msg.setSubject(Subject);
        msg.setText(Text);

        javaMailSender.send(msg);

	}
}