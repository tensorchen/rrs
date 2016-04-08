package cn.com.chenyixiao.rrs.dao;

import java.util.List;

import cn.com.chenyixiao.rrs.entity.User;

public interface UserDAO {
	
	public void addUser(User user);
	public void updateUser(User user);
	public User getUser(Long id);
	public List<User> getAllUsers();
	public void deleteUser(Long id);
	public void deleteAllUsers();
	
	public Long count();
}
