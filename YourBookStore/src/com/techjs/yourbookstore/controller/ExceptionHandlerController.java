package com.techjs.yourbookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.techjs.yourbookstore.exception.BookNotFoundException;
import com.techjs.yourbookstore.exception.PageNotFoundException;
import com.techjs.yourbookstore.model.Category;
import com.techjs.yourbookstore.service.BookService;
import com.techjs.yourbookstrore.ui.AlertMessage;

@Component
@ControllerAdvice
public class ExceptionHandlerController {
	
	@Autowired
	private BookService bookService;
	
	
	@ExceptionHandler(PageNotFoundException.class)
	public ModelAndView pageNotFoundException() {
		ModelAndView mv = new ModelAndView();
		AlertMessage am = new AlertMessage();
		am.setTitle("Page Not Found!");
		am.setDescription("The page you requested is not found.");
		am.setAlertType("alert alert-danger");
		mv.addObject("alert", am);
		mv.setViewName("errorpages/error");
		List<Category> categories = bookService.allCategories();
		mv.addObject("categories", categories);
		return mv;
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ModelAndView bookNotFoundException() {
		ModelAndView mv = new ModelAndView();
		AlertMessage am = new AlertMessage();
		am.setTitle("We're Sorry! Book Not Found.");
		am.setDescription("The Book You're Looking For Is Not Found. May be its removed from the collection");
		am.setAlertType("alert alert-danger");
		mv.addObject("alert", am);
		mv.setViewName("errorpages/error");
		List<Category> categories = bookService.allCategories();
		mv.addObject("categories", categories);
		return mv;
	}
}
