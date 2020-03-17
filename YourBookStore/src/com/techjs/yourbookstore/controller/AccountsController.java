package com.techjs.yourbookstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.techjs.yourbookstore.model.Admin;
import com.techjs.yourbookstore.model.User;
import com.techjs.yourbookstore.security.SessionUserAuthentication;
import com.techjs.yourbookstore.security.UserRole;
import com.techjs.yourbookstore.service.EmailService;
import com.techjs.yourbookstore.service.UserService;
import com.techjs.yourbookstore.util.AppConstants;
import com.techjs.yourbookstore.util.OTPGenerator;
import com.techjs.yourbookstrore.helper.Login;
import com.techjs.yourbookstrore.helper.NewMemberParticipant;

@Controller
public class AccountsController {

	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public ModelAndView registration() {
		ModelAndView mv = new ModelAndView("user-registration");
		return mv;
	}

	@PostMapping("/signup")
	public ModelAndView createAccount(User user, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		NewMemberParticipant participant = new NewMemberParticipant();
		participant.setUser(user);
		participant.setOtp(OTPGenerator.generateOTP());
		HttpSession session = request.getSession();
		session.setAttribute("participant", participant);
		ApplicationContext context = (ApplicationContext) request
				.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		EmailService emailService = context.getBean(EmailService.class);
		emailService.sendEmailVerificationOTP(participant.getUser().getEmail(), participant.getOtp());
		mv.setViewName("otp-verification");
		return mv;
	}

	@PostMapping("/verify")
	public ModelAndView verifyotp(@RequestParam("otp") String otp, HttpServletRequest request,
			@SessionAttribute("participant") NewMemberParticipant participant,
			@SessionAttribute(value = AppConstants.USER_ACCOUNT_ATTR) SessionUserAuthentication userAuthentication) {
		ModelAndView mv = new ModelAndView();

		if (participant == null) {
			mv.setViewName("redirect:/CreateAccount");
		} else if (!otp.equalsIgnoreCase(participant.getOtp())) {
			mv.addObject("invalidOtp", true);
			mv.setViewName("otp-verification");
		} else {
			User user = participant.getUser();
			userService.addNewUser(user);
			userAuthentication.login(user, UserRole.MEMBER);
			mv.setViewName("/home");
		}
		return mv;
	}

	@GetMapping(path = "/verify", params = "resend=true")
	public ModelAndView resendotp(HttpServletRequest request,
			@SessionAttribute("participant") NewMemberParticipant participant) {
		ModelAndView mv = new ModelAndView();
		if (participant == null) {
			mv.setViewName("redirect:/CreateAccount");
		} else {
			participant.setOtp(OTPGenerator.generateOTP());
			ApplicationContext context = (ApplicationContext) request
					.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
			EmailService emailService = context.getBean(EmailService.class);
			emailService.sendEmailVerificationOTP(participant.getUser().getEmail(), participant.getOtp());
			mv.setViewName("otp-verification");
			mv.addObject("resend", "true");
		}
		return mv;
	}

	@GetMapping("/admin-login")
	public ModelAndView adminloginPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin-login-page");
		return mv;
	}

	@PostMapping("/admin-login")
	public ModelAndView adminLogin(Login login,@SessionAttribute(value = AppConstants.USER_ACCOUNT_ATTR) SessionUserAuthentication userAuthentication) {
		ModelAndView mv = new ModelAndView();
		Admin admin = userService.authenticateAdmin(login);
		if (admin == null) {
			mv.addObject("loginError", "Invalid ID or Password");
			mv.setViewName("admin-login-page");
		} else {
			userAuthentication.login(admin, UserRole.ADMIN);
			mv.setViewName("redirect:/home");
		}
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member-login-page");
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView login(Login login, HttpServletRequest request, 
			@SessionAttribute(value = AppConstants.USER_ACCOUNT_ATTR) SessionUserAuthentication userAuthentication) {
		ModelAndView mv = new ModelAndView();
		User user = userService.authenticate(login);
		if (user == null) {
			mv.addObject("loginError", "Invalid Email or Password");
			mv.setViewName("member-login-page");
		} else {
			userAuthentication.login(user, UserRole.MEMBER);
			mv.setViewName("redirect:/home");
		}
		return mv;
	}

	@GetMapping("/logout")
	public ModelAndView logout(@SessionAttribute(value = AppConstants.USER_ACCOUNT_ATTR) SessionUserAuthentication userAuthentication) {
		ModelAndView mv = new ModelAndView();
		userAuthentication.logout();
		mv.setViewName("redirect:/home");
		return mv;
	}
}
