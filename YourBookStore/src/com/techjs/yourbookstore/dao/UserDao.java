package com.techjs.yourbookstore.dao;

import com.techjs.yourbookstore.model.Admin;
import com.techjs.yourbookstore.model.User;

public interface UserDao {
	public Admin getAdminByIdAndPassword(String adminId, String pass);
	public Long insertUser(User user);
	User getUserByEmailAndPassword(String email, String pass);
}
