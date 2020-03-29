package com.techjs.yourbookstore.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techjs.yourbookstore.config.DaoConfig;
import com.techjs.yourbookstore.dao.BookDao;
import com.techjs.yourbookstore.dao.BookDaoImpl;
import com.techjs.yourbookstore.dao.UserDao;
import com.techjs.yourbookstore.model.Book;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
		SessionFactory factory = context.getBean(SessionFactory.class);
		Session session = factory.openSession();
		Query<Book> query= session.createQuery("from Book b where b.category.title = :cat");
		query.setParameter("cat", "Programming");
		List<Book> result = query.list();
		result.forEach((s) -> {
			System.out.println(s);
		});
		session.close();
		factory.close();
	}
}
