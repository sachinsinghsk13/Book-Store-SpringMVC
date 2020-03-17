package com.techjs.yourbookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DaoConfig.class, ServiceConfig.class, SecurityConfig.class})
public class AppConfig {
	
}
