package com.techjs.yourbookstore.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.techjs.yourbookstore.model.User;

@Component
@SessionScope
public class SessionUserAuthentication {
	private User user;
	private UserRole userRole;
	private boolean authenticated;
	
	public SessionUserAuthentication() {
	}
	
	public User getUser() {
		return user;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void login(User user, UserRole role) {
		this.user = user;
		this.userRole = role;
		this.authenticated = true;
	}
	
	public void logout() {
		this.user = null;
		this.userRole = UserRole.GUEST;
		this.authenticated = false;
	}
}
