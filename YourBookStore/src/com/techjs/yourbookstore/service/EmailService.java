package com.techjs.yourbookstore.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSenderImpl;
	
	public void sendEmailVerificationOTP(String to, String otp) {
		// Prepare the message
		MimeMessage message = javaMailSenderImpl.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(to);
			helper.setSubject("OTP for Book Store Account Registration");
			helper.setText("<h2>Your OTP for Email  Verification is : <strong>" + otp + "</strong></h2>", true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Create thread for sending message
		EmailSender es = new EmailSender();
		es.setMailSender(javaMailSenderImpl);
		es.setMessage(message);
		Thread thread = new Thread(es, "Mail Sender Thread");
		thread.start();
	}
	
	private static class EmailSender implements Runnable {
		private JavaMailSender mailSender;
		private MimeMessage message;
		
		public JavaMailSender getMailSender() {
			return mailSender;
		}


		public void setMailSender(JavaMailSender javaMailSenderImpl) {
			this.mailSender = javaMailSenderImpl;
		}


		public MimeMessage getMessage() {
			return message;
		}


		public void setMessage(MimeMessage message2) {
			this.message = message2;
		}


		@Override
		public void run() {
			mailSender.send(message);
		}
		
	}
}
