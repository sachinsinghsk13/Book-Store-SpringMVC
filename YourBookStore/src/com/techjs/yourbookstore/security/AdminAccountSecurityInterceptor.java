package com.techjs.yourbookstore.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.techjs.yourbookstore.util.AppConstants;

public class AdminAccountSecurityInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session != null) {
			SessionUserAuthentication sua = (SessionUserAuthentication) session.getAttribute(AppConstants.USER_ACCOUNT_ATTR);
			if (sua != null && sua.getUserRole() == UserRole.ADMIN)
				return true;
		}
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.sendRedirect(request.getContextPath() + "/error/401");
		return false;
	}
}
