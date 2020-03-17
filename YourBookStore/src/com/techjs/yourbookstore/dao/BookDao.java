package com.techjs.yourbookstore.dao;

import java.util.List;

import com.techjs.yourbookstore.model.Book;
import com.techjs.yourbookstore.model.Category;

public interface BookDao {
	Long addBook(Book book);
	Category getCategoryById(Integer id);
	List<Category> getCategories();
	List<Book> getBooks();
}
