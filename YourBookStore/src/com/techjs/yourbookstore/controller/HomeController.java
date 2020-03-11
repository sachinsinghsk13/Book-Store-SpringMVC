package com.techjs.yourbookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.techjs.yourbookstore.dao.UserDao;
import com.techjs.yourbookstore.model.User;

@Controller
public class HomeController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "user-registration";
	}
	
	@GetMapping("/create-account")
	public ModelAndView createAccount(User user) {
		System.out.println(user);
		userDao.insertUser(user);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
}
