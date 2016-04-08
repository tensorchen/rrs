package cn.com.chenyixiao.rrs.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.chenyixiao.rrs.dao.UserDAO;
import cn.com.chenyixiao.rrs.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	
	public void addUser(User user) {
		if (getUser(user.getId()) == null) {
			getCurrentSession().save(user);
		}
	}

	
	public void updateUser(User user) {
		getCurrentSession().update(user);
	}

	
	public User getUser(Long id) {
		User user = (User) getCurrentSession().get(User.class, id);
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return getCurrentSession().
				createQuery("from User").list();
	}

	public void deleteUser(Long id) {
		User user = getUser(id);
		if (user != null) {
			getCurrentSession().delete(user);
		}
	}

	public void deleteAllUsers() {
		List<User> users = getAllUsers();
		for (User user : users) {
			deleteUser(user.getId());
		}
	}
	
	public Long count() {
		return (Long) getCurrentSession()
				.createQuery("select count(*) from User").uniqueResult();
	}
}
