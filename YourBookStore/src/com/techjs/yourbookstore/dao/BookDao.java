package com.techjs.yourbookstore.dao;

import java.util.List;

import com.techjs.yourbookstore.model.Book;
import com.techjs.yourbookstore.model.Category;
import com.techjs.yourbookstore.model.Comment;

public interface BookDao {
	Long addBook(Book book);
	Category getCategoryById(Integer id);
	List<Category> getCategories();
	List<Book> getBooks();
	Long getBookCount();
	List<Book> getBooks(Integer max, Integer offset);
	Book getBookById(Long id);
	void addComment(Long id, Comment comment);
	
}
