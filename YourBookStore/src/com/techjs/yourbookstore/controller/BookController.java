package com.techjs.yourbookstore.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.techjs.yourbookstore.model.Book;
import com.techjs.yourbookstore.model.Category;
import com.techjs.yourbookstore.model.Language;
import com.techjs.yourbookstore.service.BookService;
import com.techjs.yourbookstore.util.AppConstants;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/admin/add-book")
	public ModelAndView addBook() {
		ModelAndView mv = new ModelAndView();
		List<Category> categories = bookService.allCategories();
		mv.addObject("categories", categories);
		mv.setViewName("adminpages/addbook");
		return mv;
	}

	@PostMapping("/admin/add-book")
	public ModelAndView postBook(Book book,
			@RequestParam("pdf") MultipartFile pdf,
			@RequestParam("thumb") MultipartFile thumb,
			@RequestParam("categoryid") Integer categoryId,
			@RequestParam("lang") String language) throws IllegalStateException, IOException 
	{
		
		ModelAndView mv = new ModelAndView();
		book.setPdfFileName(pdf.getOriginalFilename());
		book.setThumbFileName(thumb.getOriginalFilename());
		book.setPdfFileSize(pdf.getSize());
		book.setLanguage(Language.valueOf(language));
		bookService.insertBook(book, categoryId);
		File pdfFile = new File(AppConstants.BOOKS_DIR,book.getPdfFileName());
		File thumbFile = new File(AppConstants.BOOKS_THUMB_DIR, book.getThumbFileName());
		
		pdf.transferTo(pdfFile);
		thumb.transferTo(thumbFile);
		
		
		mv.setViewName("redirect:/home");
		return mv;
	}
	
	@GetMapping(path="/thumbs", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
	@ResponseBody
	public byte[] bookThumb(@RequestParam("id") String filename) throws IOException {
		File file = new File(AppConstants.BOOKS_THUMB_DIR + filename);
		if (file.exists()) {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			return bis.readAllBytes();
		}
		return null;
	}
	
	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		List<Book> books = bookService.allBooks();
		List<Category> categories = bookService.allCategories();
		categories.sort((a, b) -> a.getTitle().compareTo(b.getTitle()));
		mv.addObject("books", books);
		mv.addObject("categories", categories);
		mv.setViewName("index");
		return mv;
	}
}
