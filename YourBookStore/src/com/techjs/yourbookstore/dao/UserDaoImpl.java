package com.techjs.yourbookstore.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techjs.yourbookstore.model.Admin;
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

	@Override
	public Admin getAdminByIdAndPassword(String adminId, String pass) {
		return (Admin) sessionFactory.getCurrentSession()
				.getNamedQuery("authenticate_admin")
				.setParameter("id", adminId)
				.setParameter("pass", pass)
				.getSingleResult();
	}
	
	@Override
	public User getUserByEmailAndPassword(String email, String pass) {
		return (User) sessionFactory.getCurrentSession()
				.getNamedQuery("authenticate_user")
				.setParameter("mail", email)
				.setParameter("pass", pass)
				.getSingleResult();
	}
	
}
