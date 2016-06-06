package cn.com.chenyixiao.rrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.chenyixiao.rrs.dao.UserDAO;
import cn.com.chenyixiao.rrs.entity.User;
import cn.com.chenyixiao.rrs.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}
	
	
	public User getUser(Long id) {
		return userDAO.getUser(id);
	}

	
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	public void deleteUser(Long id) {
		userDAO.deleteUser(id);
	}
	
	public void deleteAllUsers() {
		userDAO.deleteAllUsers();
	}
	
	public Long count() {
		return userDAO.count();
	}
	
	public User getUserByName(String name) {
		return userDAO.getUserByName(name);
	}
}
