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
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", "465");
		JavaMailSenderImpl impl = new JavaMailSenderImpl();
		impl.setJavaMailProperties(props);
		impl.setHost("smtp.gmail.com");
		impl.setUsername("sachinsinghsk369");
		impl.setPassword("SINGH.YOYO99");
		return impl;
	}
}
