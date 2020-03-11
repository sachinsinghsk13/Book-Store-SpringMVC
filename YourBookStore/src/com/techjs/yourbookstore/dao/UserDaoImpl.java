package com.techjs.yourbookstore.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techjs.yourbookstore.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Long insertUser(User user) {
		return (Long) this.sessionFactory.getCurrentSession().save(user);
	}

	
}
