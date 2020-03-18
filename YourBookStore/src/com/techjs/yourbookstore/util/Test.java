package com.techjs.yourbookstore.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.techjs.yourbookstore.config.DaoConfig;
import com.techjs.yourbookstore.dao.BookDao;
import com.techjs.yourbookstore.dao.BookDaoImpl;
import com.techjs.yourbookstore.dao.UserDao;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
		BookDao daoImpl = context.getBean(BookDao.class);
		System.out.println(daoImpl.getBookCount());
		
	}
}
