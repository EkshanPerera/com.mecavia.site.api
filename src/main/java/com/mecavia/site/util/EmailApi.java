package com.mecavia.site.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailApi {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String toEmail,String subject,String body) throws Exception {
		SimpleMailMessage massage = new SimpleMailMessage();
		massage.setFrom("admin@mecavia.com");
		massage.setTo(toEmail);
		massage.setText(body);
		massage.setSubject(subject);
		mailSender.send(massage);
	}
}
