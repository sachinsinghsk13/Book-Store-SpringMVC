package com.techjs.yourbookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techjs.yourbookstore.dao.BookDao;
import com.techjs.yourbookstore.model.Book;
import com.techjs.yourbookstore.model.Category;

@Service
public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
	public void insertBook(Book book, Integer categoryid) {
		Category category = bookDao.getCategoryById(categoryid);
		book.setCategory(category);
		bookDao.addBook(book);
	}
	
	public List<Category> allCategories() {
		return bookDao.getCategories();
	}

	public List<Book> allBooks() {
		return bookDao.getBooks();
	}
}
