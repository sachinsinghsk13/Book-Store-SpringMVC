package com.techjs.yourbookstore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "categories")
@NamedQuery(query = "FROM Category ORDER BY title", name = "all_categories")
@NamedQuery(query = "FROM Book b WHERE b.category.title = :category", name = "get_books_category")
@NamedQuery(query = "SELECT COUNT(*) FROM Book b WHERE b.category.title = :category", name = "book_category_count")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "TITLE", nullable = false, unique = true, length = 20)
	private String title;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
