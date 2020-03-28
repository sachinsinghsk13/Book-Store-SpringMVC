package com.techjs.yourbookstore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;


@Entity
@Table(name = "books")
@NamedQuery(name = "all_books", query = "FROM Book")
@NamedQuery(name = "get_book_by_name", query = "FROM Book WHERE id = :id")
@NamedQuery(name = "search_book", query = "FROM Book WHERE title LIKE :query OR author LIKE :query")
@NamedQuery(name = "book_by_category", query = "FROM Book WHERE category = :cat")
@NamedQuery(name = "book_search_count", query = "SELECT COUNT(*) FROM Book WHERE title LIKE :query OR author LIKE :query")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "TITLE", nullable = false, length = 50)
	private String title;
	
	@Column(name = "DESCRIPTION", nullable = false, length = 4096)
	private String description;
	
	@Column(name = "AUTHOR", nullable = false, length = 50)
	private String author;
	
	@Column(name = "YEAR", nullable = true)
	private Integer year;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "LANGUAGE",nullable = true)
	private Language language;
	
	@Column(name = "PAGES", nullable = false)
	private Integer pages;
	
	@Column(name = "ISBN", nullable = true, unique = true, length = 20)
	private String isbn;
	
	@Column(name = "PDF_FILE_SIZE")
	private Long pdfFileSize;
	
	@Column(name = "PDF_FILE_NAME", unique = true, nullable = false)
	private String pdfFileName;
	
	@Column(name = "THUMB_FILE_NAME", unique = true, nullable = true)
	private String thumbFileName;
	
	
	public Book() {
		this.comments = new ArrayList<Comment>();
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Category category;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@OrderBy("postedOn")
	private List<Comment> comments;
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
	public Long getPdfFileSize() {
		return pdfFileSize;
	}
	public void setPdfFileSize(Long pdfFileSize) {
		this.pdfFileSize = pdfFileSize;
	}
	public String getPdfFileName() {
		return pdfFileName;
	}
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
	public String getThumbFileName() {
		return thumbFileName;
	}
	public void setThumbFileName(String thumbFileName) {
		this.thumbFileName = thumbFileName;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", author=" + author + ", year="
				+ year + ", language=" + language + ", pages=" + pages + ", isbn=" + isbn + ", pdfFileSize="
				+ pdfFileSize + ", pdfFileName=" + pdfFileName + ", thumbFileName=" + thumbFileName + "]";
	}
	
}
