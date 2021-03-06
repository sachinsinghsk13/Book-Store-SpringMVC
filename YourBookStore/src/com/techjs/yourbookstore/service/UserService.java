package com.techjs.yourbookstore.service;

import com.techjs.yourbookstore.helper.Login;
import com.techjs.yourbookstore.model.Admin;
import com.techjs.yourbookstore.model.User;

public interface UserService {
	boolean isEmailAvailable(String email);
	void addNewUser(User user);
	User authenticate(Login login);
	Admin authenticateAdmin(Login login);
}
