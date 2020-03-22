package com.techjs.yourbookstore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techjs.yourbookstore.model.Book;
import com.techjs.yourbookstore.model.Category;
import com.techjs.yourbookstore.model.Comment;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Transactional
	@Override
	public Long addBook(Book book) {
		return (Long) sessionFactory.getCurrentSession().save(book);
		
	}


	@Override
	@Transactional
	public Category getCategoryById(Integer id) {
		return sessionFactory.getCurrentSession().get(Category.class, id);
	}


	@Override
	@Transactional
	public List<Category> getCategories() {
		Session session = sessionFactory.getCurrentSession();
		return session.createNamedQuery("all_categories").list();
	}


	@Override
	public List<Book> getBooks() {
		Session session = sessionFactory.getCurrentSession();
		return session.createNamedQuery("all_books").list();
	}


	@Override
	public Long getBookCount() {
		return (Long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) FROM Book").getSingleResult();
	}


	@Override
	public List<Book> getBooks(Integer max, Integer offset) {
		return sessionFactory.getCurrentSession().createQuery("FROM Book")
				.setFirstResult(offset)
				.setMaxResults(max)
				.getResultList();
	}


	@Override
	public Book getBookById(Long id) {
		return (Book) sessionFactory
				.getCurrentSession()
				.createNamedQuery("get_book_by_name")
				.setParameter("id", id)
				.getSingleResult();
	}


	@Override
	public void addComment(Long id, Comment comment) {
		Book book = (Book) sessionFactory
				.getCurrentSession()
				.createNamedQuery("get_book_by_name")
				.setParameter("id", id)
				.getSingleResult();
		book.getComments().add(comment);
	}
}
