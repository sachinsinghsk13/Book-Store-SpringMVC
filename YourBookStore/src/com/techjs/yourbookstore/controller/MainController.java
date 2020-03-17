package com.techjs.yourbookstore.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.techjs.yourbookstore.dao.BookDao;
import com.techjs.yourbookstore.model.Category;

@Controller
public class MainController {

	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BookDao bookDao;
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/home");
		return mv;
	}
	
	@PostConstruct
	public void init() {
		List<Category> categories = bookDao.getCategories();
		servletContext.setAttribute("categories",categories);
	}
}
