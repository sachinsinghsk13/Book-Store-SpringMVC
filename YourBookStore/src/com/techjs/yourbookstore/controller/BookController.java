package com.techjs.yourbookstore.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.techjs.yourbookstore.exception.BookNotFoundException;
import com.techjs.yourbookstore.exception.PageNotFoundException;
import com.techjs.yourbookstore.model.Book;
import com.techjs.yourbookstore.model.Category;
import com.techjs.yourbookstore.model.Comment;
import com.techjs.yourbookstore.model.Language;
import com.techjs.yourbookstore.model.User;
import com.techjs.yourbookstore.security.SessionUserAuthentication;
import com.techjs.yourbookstore.service.BookService;
import com.techjs.yourbookstore.util.AppConstants;
import com.techjs.yourbookstrore.ui.Pagination;

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
	public ModelAndView postBook(Book book, @RequestParam("pdf") MultipartFile pdf,
			@RequestParam("thumb") MultipartFile thumb, @RequestParam("categoryid") Integer categoryId,
			@RequestParam("lang") String language) throws IllegalStateException, IOException {

		ModelAndView mv = new ModelAndView();
		book.setPdfFileName(pdf.getOriginalFilename());
		book.setThumbFileName(thumb.getOriginalFilename());
		book.setPdfFileSize(pdf.getSize());
		book.setLanguage(Language.valueOf(language));
		bookService.insertBook(book, categoryId);
		File pdfFile = new File(AppConstants.BOOKS_DIR, book.getPdfFileName());
		File thumbFile = new File(AppConstants.BOOKS_THUMB_DIR, book.getThumbFileName());

		pdf.transferTo(pdfFile);
		thumb.transferTo(thumbFile);

		mv.setViewName("redirect:/allbooks");
		return mv;
	}

	@GetMapping(path = "/thumbs", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE })
	@ResponseBody
	public byte[] bookThumb(@RequestParam("id") String filename) {
		File file = new File(AppConstants.BOOKS_THUMB_DIR + filename);
		if (file.exists()) {

			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				return bis.readAllBytes();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@GetMapping(path = "/books/pdf", produces = { MediaType.APPLICATION_PDF_VALUE })
	@ResponseBody
	public ResponseEntity<?> bookDownlaod(@RequestParam("id") String filename) throws IOException {
		File file = new File(AppConstants.BOOKS_DIR + filename);
		if (file.exists()) {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			byte[] data = bis.readAllBytes();
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename.replace(' ', '_'));
			return new ResponseEntity<>(data, headers, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/allbooks")
	public ModelAndView home(@RequestParam(value = "page", required = false) Integer page) throws PageNotFoundException {
		ModelAndView mv = new ModelAndView();
		if (page == null) {
			page = 1;
		}
		Pagination pagination = new Pagination();
		pagination.setCurrent(page);
		List<Book> books = bookService.allBooks(pagination);
		List<Category> categories = bookService.allCategories();
		mv.addObject("pagination", pagination);
		mv.addObject("books", books);
		mv.addObject("list_title", "All Books");
		mv.addObject("categories", categories);
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/book/{bookid}")
	public ModelAndView viewBook(@PathVariable Long bookid) throws BookNotFoundException {
		ModelAndView mv = new ModelAndView();
		Book book = bookService.getBookById(bookid);
		if (book == null) {
			throw new BookNotFoundException();
		} else {
			mv.addObject("book", book);
			List<Category> categories = bookService.allCategories();
			mv.addObject("categories", categories);
			mv.setViewName("viewbook");
		}

		return mv;
	}

	@PostMapping("/book/{bookid}/comments")
	public String postComment(@PathVariable("bookid") Long bookid, @RequestParam("comment") String comment,
			@SessionAttribute(value = AppConstants.USER_ACCOUNT_ATTR) SessionUserAuthentication userAuthentication) {
		Comment c = new Comment();
		User user = userAuthentication.getUser();
		c.setPostedBy(user);
		c.setPostedOn(new Date());
		c.setContent(comment);
		bookService.insertComment(bookid, c);
		return "redirect:/book/" + bookid;
	}

	@GetMapping("/search")
	public ModelAndView searchBook(@RequestParam("query") String query, @RequestParam(value = "page", required = false) Integer page) throws PageNotFoundException {
		ModelAndView mv = new ModelAndView();
		Pagination pagination = new Pagination();
		if (page == null) {
			page = 1;
		}
		pagination.setCurrent(page);
		List<Book> books = bookService.searchBook(query, pagination);
		if (books.size() > 0)
			mv.addObject("list_title", "Search Results for " + query);
		else
			mv.addObject("list_title", "No Results Found For " + query);
		mv.addObject("books", books);
		mv.addObject("pagination", pagination);
		List<Category> categories = bookService.allCategories();
		mv.addObject("categories", categories);
		mv.addObject("query", query);
		mv.addObject("applySearchPagination",true);
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/category/{category}")
	public ModelAndView viewByCategory(@PathVariable("category") String category, @RequestParam(value = "page", required = false) Integer page) throws PageNotFoundException {
		ModelAndView mv = new ModelAndView();
		Pagination pagination = new Pagination();
		if (page == null) {
			page = 1;
		}
		pagination.setCurrent(page);
		List<Book> books = bookService.getBooksByCategory(pagination, category);
		mv.addObject("list_title", category + " Books");
		mv.addObject("books", books);
		mv.addObject("pagination", pagination);
		List<Category> categories = bookService.allCategories();
		mv.addObject("categories", categories);
		mv.addObject("applyCategoryPagination",true);
		mv.addObject("category", category);
		mv.setViewName("index");
		return mv;
	}
}
