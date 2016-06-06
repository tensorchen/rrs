package cn.com.chenyixiao.rrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.com.chenyixiao.rrs.entity.User;
import cn.com.chenyixiao.rrs.service.UserService;

@Controller
@RequestMapping(produces="application/json;charset=UTF-8", 
		consumes="application/json;charset=UTF-8")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/user", method = RequestMethod.POST)
	public String addUser(@RequestBody User user) {
		userService.addUser(user);
		String ret = new Gson().toJson(
				userService.getUserByName(user.getName()));
		return ret;
	}
	
	@ResponseBody
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable long id) {
		User user = userService.getUser(id);
		return new Gson().toJson(user);
	}
	
	@ResponseBody
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public String getUserList() {
		List<User> userList = userService.getAllUsers();
		return new Gson().toJson(userList);
	}
	
	@ResponseBody
	@RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
		return new Gson().toJson("success");
	}
	
	private String getUserService(long id) {
		User user = userService.getUser(id);
		return new Gson().toJson(user);
	}
}
