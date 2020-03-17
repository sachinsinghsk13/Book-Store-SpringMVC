package com.techjs.yourbookstore.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.techjs.yourbookstore.config.DaoConfig;
import com.techjs.yourbookstore.dao.UserDao;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
		UserDao userDao = context.getBean(UserDao.class);
		System.out.println(userDao.getUserByEmailAndPassword("sachinsingh.sk13@gmail.com", "1234"));
		
	}
}
