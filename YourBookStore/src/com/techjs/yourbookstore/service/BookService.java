package com.techjs.yourbookstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techjs.yourbookstore.dao.BookDao;
import com.techjs.yourbookstore.exception.PageNotFoundException;
import com.techjs.yourbookstore.model.Book;
import com.techjs.yourbookstore.model.Category;
import com.techjs.yourbookstore.model.Comment;
import com.techjs.yourbookstrore.ui.Pagination;

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
	
	public Long getBookCount() {
		return bookDao.getBookCount();
	}
	public List<Book> allBooks(Pagination pagination) throws PageNotFoundException {
		Long totalBooks = this.getBookCount();
		Integer totalPages = (int) Math.ceil((double) totalBooks / pagination.getItemPerPage());
		if (pagination.getCurrent() > totalPages || pagination.getCurrent() < 1)
			throw new PageNotFoundException();
		pagination.setTotal(totalBooks);
		pagination.setTotolPages(totalPages);
		Integer offset = ((pagination.getCurrent() - 1) * pagination.getItemPerPage());
		return bookDao.getBooks(pagination.getItemPerPage(), offset);
	}
	
	public List<Book> searchBook(Integer max, Integer offset, String query) {
		return bookDao.searchBook(max, offset, query);
	}
	
	public List<Book> searchBook(String query, Pagination pagination) throws PageNotFoundException {
		Long totalBooks = bookDao.getBookSearchCount(query);
		if (totalBooks == 0)
			return new ArrayList<Book>();
		Integer totalPages = (int) Math.ceil((double) totalBooks / pagination.getItemPerPage());
		if (pagination.getCurrent() > totalPages || pagination.getCurrent() < 1)
			throw new PageNotFoundException();
		pagination.setTotal(totalBooks);
		pagination.setTotolPages(totalPages);
		Integer offset = ((pagination.getCurrent() - 1) * pagination.getItemPerPage());
		return bookDao.searchBook(pagination.getItemPerPage(), offset, query);
	}
	
	public List<Book> getBooksByCategory(String category) {
		return bookDao.getBooksByCategory(category);
	}
	
	public List<Book> getBooksByCategory(Pagination pagination, String category) throws PageNotFoundException {
		Long totalBooks = bookDao.getBookCategoryCount(category);
		if (totalBooks == 0)
			return new ArrayList<Book>();
		Integer totalPages = (int) Math.ceil((double) totalBooks / pagination.getItemPerPage());
		if (pagination.getCurrent() > totalPages || pagination.getCurrent() < 1)
			throw new PageNotFoundException();
		pagination.setTotal(totalBooks);
		pagination.setTotolPages(totalPages);
		Integer offset = ((pagination.getCurrent() - 1) * pagination.getItemPerPage());
		return bookDao.getBooksByCategory(pagination.getItemPerPage(), offset, category);
	}
	
	public Book getBookById(Long id) {
		try {
			return bookDao.getBookById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void insertComment(Long id, Comment comment) {
		bookDao.addComment(id, comment);
	}
}
