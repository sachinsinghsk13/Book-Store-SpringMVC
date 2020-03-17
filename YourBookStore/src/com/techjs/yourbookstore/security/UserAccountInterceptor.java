package com.techjs.yourbookstore.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;

import com.techjs.yourbookstore.util.AppConstants;

public class UserAccountInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		SessionUserAuthentication account = (SessionUserAuthentication) session.getAttribute(AppConstants.USER_ACCOUNT_ATTR);
		if (account == null) {
			ApplicationContext context = (ApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
			SessionUserAuthentication s = context.getBean(SessionUserAuthentication.class);
			session.setAttribute(AppConstants.USER_ACCOUNT_ATTR, s);
			
		}
		return true;
	}
}
