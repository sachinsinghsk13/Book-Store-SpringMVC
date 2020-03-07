package com.techjs.yourbookstore.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan("com.techjs.yourbookstore.service")
@PropertySource("classpath:java-mail.properties")
public class ServiceConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public JavaMailSender javaMailSender() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
		props.put("mail.smtp.socketFactory.port", env.getProperty("mail.smtp.socketFactory.port"));
		props.put("mail.smtp.socketFactory.class", env.getProperty("mail.smtp.socketFactory.class"));
		props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost(env.getProperty("host"));
		senderImpl.setUsername(env.getProperty("username"));
		senderImpl.setPassword(env.getProperty("password"));
		return senderImpl;
	}
}
