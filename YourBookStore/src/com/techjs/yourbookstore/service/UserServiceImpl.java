package com.techjs.yourbookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techjs.yourbookstore.dao.UserDao;
import com.techjs.yourbookstore.helper.Login;
import com.techjs.yourbookstore.model.Admin;
import com.techjs.yourbookstore.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean isEmailAvailable(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addNewUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public User authenticate(Login login) {
		try {
			return userDao.getUserByEmailAndPassword(login.getEmail(), login.getPassword());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Admin authenticateAdmin(Login login) {
		try {
			return userDao.getAdminByIdAndPassword(login.getAdminid(), login.getPassword());
		} catch (Exception e) {
			return null;
		}
	}
	
}	
